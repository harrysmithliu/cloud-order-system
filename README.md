# Cloud Order System

Cloud Order System is a Spring Cloud microservices demo for an order management backend. It shows how to organize a multi-module Java system with service discovery, API gateway routing, centralized configuration, shared web utilities, JWT-based authentication support, and Docker Compose orchestration.

## Tech Stack

- Java 17
- Spring Boot 3.3.4
- Spring Cloud 2023.0.3
- Spring Cloud Gateway
- Netflix Eureka
- Spring Cloud Config
- OpenFeign
- MyBatis
- MySQL 8
- Redis 7
- Docker and Docker Compose
- Maven multi-module build

## Modules

| Module | Purpose |
| --- | --- |
| `gateway-service` | API gateway that routes external requests to backend services. |
| `registry-service` | Eureka service registry for service discovery. |
| `config-service` | Spring Cloud Config service. |
| `user-service` | User authentication and user data service. |
| `product-service` | Product service module. |
| `order-service` | Order query service with pagination and filters. |
| `common-lib` | Shared DTOs, error models, and pagination utilities. |
| `common-web-core` | Shared web configuration and global exception handling. |
| `common-web-security` | JWT and password security utilities. |
| `common-web-starter` | Auto-configuration and shared web logging support. |

## Architecture

```text
Client
  |
  v
gateway-service :8080
  |
  +--> user-service    :8082
  +--> order-service   :8081
  +--> product-service :8083

registry-service :8761
config-service   :8888
mysql            :3306
redis            :6379
```

Services register with Eureka and can communicate through service discovery. The gateway currently routes authentication requests under `/api/auth/**` to `user-service`.

## Getting Started

### Prerequisites

- JDK 17
- Maven 3.9+ or the included Maven wrapper
- Docker and Docker Compose

### Build

```bash
./mvnw clean package
```

### Run with Docker Compose

```bash
docker compose up --build
```

Main service ports:

| Service | Port |
| --- | --- |
| Gateway | `8080` |
| Order service | `8081` |
| User service | `8082` |
| Product service | `8083` |
| Eureka registry | `8761` |
| Config service | `8888` |
| MySQL | `3306` |
| Redis | `6379` |

### Run Services Locally

Start infrastructure first:

```bash
docker compose up mysql redis
```

Then start services from your IDE or with Maven using the `dev` Spring profile. A typical startup order is:

1. `config-service`
2. `registry-service`
3. `gateway-service`
4. `user-service`, `product-service`, and `order-service`

## API Overview

### Authentication

```http
POST /api/auth/login
```

Authenticates a user and returns a JWT token with user profile information.

### Orders

```http
GET /api/orders
```

Supports optional filters:

- `orderNo`
- `status`
- `productCode`
- `keyword`
- `createdAfter`
- `createdBefore`
- `page`
- `size`

## Project Notes

- The repository is organized as a Maven parent project with independent service modules.
- Common response, exception, JWT, password, and logging code is shared through reusable internal modules.
- Docker Compose provides a local environment for the application services and dependencies.
- Some business modules are still demo-oriented and may require database schema/data setup before full end-to-end usage.

## License

This project is intended as a demo project. Add a license before using it in production or distributing it publicly.
