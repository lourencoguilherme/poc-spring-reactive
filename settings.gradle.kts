rootProject.name = "poc-reactive"
include("services")
include("services:api-spring-reactive")
findProject(":services:api-spring-reactive")?.name = "api-spring-reactive"
include("services:api-spring-factory")
findProject(":services:api-spring-factory")?.name = "api-spring-factory"
include("services:spring-boot-kotlin-reactive-example")
findProject(":services:spring-boot-kotlin-reactive-example")?.name = "spring-boot-kotlin-reactive-example"
