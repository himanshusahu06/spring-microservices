# Currency Exchange Service

Port: 8000, 8001, 8002

### Usage
```bash
java -Dserver.port=8009 -jar currency-exchange-service-2.2.2.RELEASE.jar
```

### Endpoint

```bash
curl http://localhost:8000/currency-exchange/from/USD/to/INR
```

```json
{
  "id": 1,
  "from": "USD",
  "to": "INR",
  "conversionFactor": 67,
  "port": 8000
}
```