# Spring cloud config server

## Retrieving Configuration
```bash
curl http://localhost:8888/{application_name}/{environment}
```

where property file with environment are defined in following file in [cloud config server](https://github.com/himanshusahu06/spring-cloud-config-repository)
 
```bash
{application_name}-{environment}.properties
```

property file with default environment
```bash
{application_name}.properties
```

for example
 * `limits-service.properties` maps to `limits-service/default` URL
* `limits-service-qa.properties` maps to `limits-service/qa` URL

#### Examples
Request for `limits-service` application with `default` cloud configuration
```bash
curl http://localhost:8888/limits-service/default
```

Response
```json
{
  "name": "limits-service",
  "profiles": [
    "default"
  ],
  "label": null,
  "version": "074040665d2ae41d76e9048deaa345ebc5dda342",
  "state": null,
  "propertySources": [
    {
      "name": "file:///Users/himanshu.sahu/nucode/spring-cloud-config-repository/limits-service.properties",
      "source": {
        "limits-service.minimum": "8",
        "limits-service.maximum": "8000"
      }
    }
  ]
}
```

Request for `limits-service` application with `qa` cloud configuration
```bash
http://localhost:8888/limits-service/qa
```

Response
```bash
{
  "name": "limits-service",
  "profiles": [
    "qa"
  ],
  "label": null,
  "version": "074040665d2ae41d76e9048deaa345ebc5dda342",
  "state": null,
  "propertySources": [
    {
      "name": "file:///Users/himanshu.sahu/nucode/spring-cloud-config-repository/limits-service-qa.properties",
      "source": {
        "limits-service.minimum": "8"
      }
    },
    {
      "name": "file:///Users/himanshu.sahu/nucode/spring-cloud-config-repository/limits-service.properties",
      "source": {
        "limits-service.minimum": "8",
        "limits-service.maximum": "8000"
      }
    }
  ]
}
```
