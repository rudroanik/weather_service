# Weather Information Service

## Description
A backend-only REST API that provides current weather information for a given city using WeatherAPI.

---

## API Endpoint

### GET /api/weather

#### Query Parameters
| Name | Type | Required |
|----|----|----|
| city | String | Yes |

#### URL
http://localhost:8080/api/weather?city=Dhaka
#### Example Response
```json
{
  "latitude": 23.81,
  "longitude": 90.41,
  "temperatureCelsius": 32.5,
  "windKmh": 12.3,
  "humidity": 65
}
