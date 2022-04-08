# Spring boot dummy gateway example

## How to build and run with docker step-by-step:
1. mvn clean install
2. Build image in the gateway directory run the command
``` 
docker build -t gateway.jar .
``` 

3. Build image under the "service" directory run the command
``` 
docker build -t service-balance.jar .
``` 
4. run images
``` 
docker-compose up -d
```    
under root folder

how to check balance:
```
curl --request GET \
--url http://localhost:8080/savings/a/balance
```

how to update the balance:
```
curl --request POST \
  --url http://localhost:8080/savings/a/balance \
  --header 'Content-Type: application/json' \
  --data '{
	"amount": 512.18
}'
```

### How to scale up the gateway:
 - Gateway service itself doesn't have state, so we can scale it up successfuly
if we need to provide user information we may use JWT-token in every request
###How to monitor uptime so I can sleep at night?
 - We can use micrometer and prometheus for gathering metrics from our endpoints 
and application healthcheck. With this metrics we can use Grafana or write a Telegram Bot for alerting
 
###How would you test timeout? 
 - I choose wiremock for mocking requests to balance services. It is possible to mock request with delay
 
### Stack used
 - Java
 - Spring Boot
 - OpenFeign
 - PostgreSQL
 - H2 (for testing)
 - Wiremock
 - Lombok
 - Docker
