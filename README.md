 A complete backend application for a **Job Portal System** built using **Spring Boot Microservices Architecture**.

This project demonstrates real-world backend development concepts including:

- Microservices
- API Gateway
- Service Discovery
- Centralized Configuration
- Distributed Tracing
- Dockerized Deployment
- Fault Tolerance


This project consists of the following services:

| Service | Description |
|---|---|
| Job Service | Manages job-related operations |
| Company Service | Handles company details |
| Review Service | Handles company reviews & ratings |
| API Gateway | Central entry point for all APIs |
| Eureka Server | Service discovery and registration |
| Config Server | Centralized configuration management |



Tech Stack -

## Backend
- Java 21
- Spring Boot
- Spring Cloud
- Spring Data JPA
- Hibernate

## Microservices Tools
- Eureka Server
- Spring Cloud Gateway
- Config Server
- Resilience4j

## Database & Messaging
- PostgreSQL
- RabbitMQ

## DevOps & Monitoring
- Docker
- Docker Compose
- Zipkin



| Service | URL |
|---|---|
| Eureka Dashboard | http://localhost:8761 |
| API Gateway | http://localhost:8085 |
| Zipkin Dashboard | http://localhost:9411 |
| PgAdmin | http://localhost:8081 |
| RabbitMQ Dashboard | http://localhost:15672 |

---

#API Endpoints

## Job APIs

```http
GET /jobs
POST /jobs
GET /jobs/{id}
DELETE /jobs/{id}
```

## Company APIs

```http
GET /companies
POST /companies
GET /companies/{id}
```

## Review APIs

```http
GET /reviews
POST /reviews
GET /reviews?companyId={id}
```



Features - 

Microservices Architecture  
API Gateway Routing  
Eureka Service Discovery  
Centralized Configuration  
Distributed Tracing with Zipkin  
Dockerized Deployment  
PostgreSQL Integration  
RabbitMQ Messaging  
Fault Tolerance using Resilience4j  
RESTful APIs  

