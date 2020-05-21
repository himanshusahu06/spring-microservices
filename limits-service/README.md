# Limit Service

#### Request
```bash
http://localhost:8080/limits
```

#### Response
```bash
{
  "minimum": 15,
  "maximum": 8000
}
```

## Refreshing application's configuration manually using actuator endpoint

During runtime, if any config source of spring cloud config server (in out case, properties file in github repository)
changes while limits service is running,changes will not take effect unless manually we fire actuator endpoint to sync
the application's configuration from the cloud config server.

#### Request
```bash
http://localhost:8081/actuator/refresh
```

#### Response
```json
[
    "config.client.version",
    "limits-service.minimum"
]
```
response body will be empty `[]` if  config has not changed since last time you've hit the refresh endpoint or since application startup.

without firing actuator refresh endpoint, local config will never sync with cloud configuration.

## Refreshing application's configuration automatically using spring cloud bus
