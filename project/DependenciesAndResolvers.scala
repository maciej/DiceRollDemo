import sbt._

object Dependencies {
  val metricsVersion = "3.0.1"

  /* Monitoring */
  val metricsCore = "com.codahale.metrics" % "metrics-core" % metricsVersion
  val metricsGraphite = "com.codahale.metrics" % "metrics-graphite" % metricsVersion
  // Project page: https://github.com/erikvanoosten/metrics-scala
  val metricsScala = "nl.grons" %% "metrics-scala" % "3.0.3"

  val metrics = Seq(metricsCore, metricsGraphite, metricsScala)

}
