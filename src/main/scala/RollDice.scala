import com.codahale.metrics.graphite.{GraphiteReporter, Graphite}
import com.codahale.metrics.{MetricFilter, MetricRegistry}
import java.net.InetSocketAddress
import java.util.concurrent.TimeUnit
import nl.grons.metrics.scala.InstrumentedBuilder
import scala.util.Random

class RollDice extends Metrics {
  val rand = new Random()

  val diceGauge = metrics.gauge("dice") {
    rand.nextInt(6) + 1
  }
}

object RollDiceRunner extends App {
  new RollDice()
  Reporter.start()

  while (true) {
    Thread.sleep(1000)
  }
}


object Reporter {
  def start() {
    val graphite = new Graphite(new InetSocketAddress("localhost", 2003))
    val graphiteReporter = GraphiteReporter.forRegistry(MetricsRegistryHolder.metricRegistry)
      .prefixedWith("dice-roller")
      .convertRatesTo(TimeUnit.SECONDS)
      .convertDurationsTo(TimeUnit.MILLISECONDS)
      .filter(MetricFilter.ALL)
      .build(graphite)

    graphiteReporter.start(5, TimeUnit.SECONDS)
  }
}

trait Metrics extends InstrumentedBuilder {
  val metricRegistry = MetricsRegistryHolder.metricRegistry
}

object MetricsRegistryHolder {
  lazy val metricRegistry = new MetricRegistry()
}
