# Log Producer Service

The **Log Producer Service** is a Spring Boot microservice responsible for receiving log events from client applications through the **LogPulse SDK**, validating and enriching the data, and publishing it asynchronously to Apache Kafka for downstream processing.

It acts as the entry point of the LogPulse platform and decouples client applications from the log processing pipeline.

---

## Architecture

```
Client Application
       │
       │ HTTP POST
       ▼
LogPulse SDK
       │
       ▼
Log Producer Service
       │
Validate & Enrich
       │
Publish Event
       ▼
Apache Kafka
       │
       ▼
Log Ingestion Service
```

---

## Features

- Receives log traces through REST APIs
- Validates API Key before accepting logs
- Supports multi-tenant architecture
- Enriches incoming log metadata
- Publishes log events to Kafka
- Asynchronous event-driven communication
- Centralized entry point for SDK requests
- Secure endpoints using JWT & Keycloak
- Global exception handling
- Request validation
- Configurable through application properties

---

## Technology Stack

- Java 21
- Spring Boot
- Spring Web
- Spring Security
- Spring Kafka
- Keycloak
- JWT Authentication
- PostgreSQL
- Docker
- Maven

---

## Request Flow

```
SDK
 │
 │ HTTP POST
 ▼
Controller
 │
 ▼
Authentication
(API Key Validation)
 │
 ▼
Validation
 │
 ▼
Kafka Producer
 │
 ▼
Kafka Topic
(log-trace-events)
```

---

## REST APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/log` | Receive and publish log traces |
| POST | `/api/exception` | Receive exception logs |
| POST | `/api/http-trace` | Receive HTTP request traces |
| GET | `/actuator/health` | Health Check |

> Update the endpoint paths according to your implementation.

---

## Kafka

### Producer Topic

```
log-trace-events
```

### Event Flow

```
SDK
 │
 ▼
Producer Service
 │
 ▼
Kafka
 │
 ▼
Log Ingestion Service
```

---

## Security

- API Key validation for SDK requests
- JWT authentication
- Keycloak integration
- Tenant isolation
- Secure REST APIs

---

## Project Structure

```
src
├── controller
├── service
├── kafka
├── security
├── validation
├── dto
├── entity
├── repository
├── exception
├── configuration
└── util
```

---

## Configuration

Example:

```properties
server.port=8082
server.servlet.context-path=/log-producer-service

spring.kafka.bootstrap-servers=localhost:9092

producer.kafka.topic=log-trace-events

keycloak.server-url=...
```

---

## Running

```bash
mvn clean install

mvn spring-boot:run
```

or

```bash
java -jar log-producer-service.jar
```

---

## Dependencies

- Apache Kafka
- Keycloak
- PostgreSQL
- LogPulse SDK
- Log Ingestion Service

---

## Responsibilities

- Accept SDK requests
- Authenticate incoming requests
- Validate API Keys
- Enrich log metadata
- Publish Kafka events
- Decouple client applications from downstream services

---

## Future Enhancements

- Request rate limiting
- Batch log publishing
- Retry mechanism
- Dead Letter Queue (DLQ)
- Message compression
- Distributed tracing
- Metrics with Micrometer
- Prometheus integration
- Grafana dashboards

---

## License

This project is part of the **LogPulse** centralized application monitoring platform.
