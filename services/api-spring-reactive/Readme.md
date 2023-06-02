# Spring Boot Kotlin Reactive Server Example

This repository contains a simple example Spring Boot 3 web server using reactive API's with Kotlin Coroutines.

### Used technologies
- Spring Webflux (reactive REST endpoints)
- Spring Data R2DBC (reactive database access)
- H2 in memory database
- Spring Native GraalVM builds
- Routes defined with Kotlin DSL (`RouteConfig.kt`)
- KotlinX Serialization

### Endpoints
- **GET**  `/api/v1/news` -> List all news entries in database
- **GET**  `/api/v1/news/{id}` -> Get specific news entry by id from database
- **POST** `/api/v1/news` -> Add news entry to database

## Build & Run native image

### Requirements
- GraalVM installed and set as environment variable `JAVA_HOME` (sdkman easiest variant)
- native-image installed (use `gu`) and reachable

This repository contains a simple demo application showing the usage of [Micrometer Tracing](https://micrometer.io/docs/tracing).

It contains the following features:
- [Spring Boot 3 Webflux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
- [Micrometer Tracing](https://micrometer.io/docs/tracing)
- [Zipkin tracing export](https://github.com/open-telemetry/opentelemetry-java/tree/main/exporters/zipkin)
- [R2DBC Database Tracing](https://github.com/spring-projects-experimental/r2dbc-micrometer-spring-boot)
- Reactive logs with tracing data
- Custom observations in reactive streams

![zipkin.png](docs%2Fimages%2Fzipkin.png)

## Requirements
- JVM 1.7+
- Docker

## Run Demo
1. Start Zipkin Tracer via
    - `docker run -p 9411:9411 openzipkin/zipkin`
2. Open Zipkin UI and query for traces
    - http://localhost:9411/zipkin/

### Steps
- Build using `./gradlew :nativeCompile`
- Run using `./build/native/nativeCompile/api-spring-reactive`

### Known Issues
- Native tests not working due to mock dependencies

sdk install java 22.3.2.r17-grl
sdk default java 22.3.2.r17-grl

export GRAALVM_HOME=/Users/guilherme.lourenco/.sdkman/candidates/java/current

docker run -it --rm ghcr.io/graalvm/graalvm-ce:ol9-java17-22.3.2 bash
docker pull ghcr.io/graalvm/graalvm-ce:ol9-java17-22.3.2-b1


docker build . --tag gml/poc:0.0.1-SNAPSHOT --file Dockerfile

docker run -p 8080:8080 gml/poc


docker build . --tag gml/poc:0.0.1-SNAPSHOT --file Dockerfile && docker run -p 8080:8080 gml/poc:0.0.1-SNAPSHOT