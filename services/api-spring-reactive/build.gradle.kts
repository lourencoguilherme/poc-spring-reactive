import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = "1.7.21" // also update plugin versions
val kotlinSerializationVersion = "1.5.0"
val micrometerTracing = "1.1.1"

plugins {
    id("org.springframework.boot") version "3.0.1"
    id("org.graalvm.buildtools.native") version "0.9.17"
    id("io.spring.dependency-management") version "1.1.0"

    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
    kotlin("plugin.serialization") version "1.7.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    maven { url = uri("https://repo.spring.io/release") }
    maven { url = uri("https://repo.spring.io/milestone") }
    maven("https://repo.spring.io/snapshot")
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("io.r2dbc:r2dbc-h2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("com.google.code.gson:gson:2.10.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.security:spring-security-test")

    // Mocks
    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation("com.ninja-squad:springmockk:4.0.2")

    // force proxy version
    implementation("io.r2dbc:r2dbc-proxy:1.1.0.RELEASE")
    // R2DBC micrometer auto tracing
    implementation("org.springframework.experimental:r2dbc-micrometer-spring-boot:1.0.2-SNAPSHOT")
    // Added Micrometer Tracing dependencies
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    implementation(platform("io.micrometer:micrometer-tracing-bom:$micrometerTracing"))
    implementation("io.micrometer:micrometer-tracing")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    implementation("io.opentelemetry:opentelemetry-exporter-zipkin")

    // Enable Elasticsearch logging on JVM with Logback
    runtimeOnly("org.slf4j:log4j-over-slf4j:2.0.5")
    runtimeOnly("ch.qos.logback:logback-classic")

    runtimeOnly("ch.qos.logback:logback-classic")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
