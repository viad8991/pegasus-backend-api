import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    war

    checkstyle

    kotlin("jvm")
    kotlin("plugin.spring")

    id("org.springframework.boot")
    id("io.spring.dependency-management")

    id("org.liquibase.gradle")

    id("com.google.cloud.tools.jib")
}

group = "org.pegasus"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    /* Kotlin */
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
    implementation(Kotlin.stdlib)
    implementation("org.jetbrains.kotlin:kotlin-reflect:_")

    /* DevTools */
    developmentOnly(Spring.boot.devTools)

    /* SECURITY */
    implementation(Spring.boot.oauth2Client)
    implementation(Spring.boot.security)
    implementation("io.jsonwebtoken:jjwt-api:_")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:_")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:_")

    /* logging (replace) */
    implementation("io.github.oshai:kotlin-logging-jvm:_")

    /* spring web */
    implementation(Spring.boot.web)
    implementation(Spring.boot.webflux)

    /* ??? */
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:_")

    /* json parse */
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")

    /* БД */
    implementation(Spring.boot.data.jpa)  // implementation(Spring.boot.data.r2dbc) // - Aсинхронная JPA
    liquibaseRuntime("org.liquibase:liquibase-core:_")
    runtimeOnly("org.postgresql:postgresql:_")

    /* Test Depends */
    testImplementation(Kotlin.test)
    testImplementation(Spring.boot.test)
    testImplementation("org.assertj:assertj-db:2.0.2")
    testImplementation("org.assertj:assertj-core:3.26.0")
    testImplementation("com.github.database-rider:rider-core:1.42.0")
    testImplementation("com.github.database-rider:rider-spring:1.42.0")
    testImplementation("io.zonky.test:embedded-database-spring-test:_")
    testImplementation("io.zonky.test:embedded-postgres:_")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
        minHeapSize = "256M"

        jvmArgs("-Dspring.profiles.active=test")
    }

    withType<BootRun> {
        jvmArgs("-Dspring.profiles.active=dev")
    }

    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "21"
        }
    }
}

repositories {
    mavenCentral()
}
