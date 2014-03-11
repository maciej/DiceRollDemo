name 'graphite_server'
description 'The Graphite server'

run_list [
             'recipe[graphite]'
         ]

default_attributes(
    graphite: {
        web: {
            admin_email: 'maciejb@softwaremill.com'
        },
        web_server: 'uwsgi',
        create_user: true,
        storage_schemas: [
            {
                name: 'carbon',
                pattern: '^carbon\.',
                retentions: '60:90d'
            },
            {
                name: 'default',
                pattern: '.*',
                retentions: '30s:21d,15m:1y'
            }
        ],
        timezone: 'UTC',
        listen_port: 3030,
        uwsgi: {
            listen_http: true,
            workers: 4
        },

        encrypted_data_bag: {
            name: 'graphite'
        }

    }
)
