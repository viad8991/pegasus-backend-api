import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    war

    checkstyle

    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"

    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"

    id("com.google.cloud.tools.jib") version "3.4.2"
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
    implementation(Kotlin.stdlib)
    implementation("org.jetbrains.kotlin:kotlin-reflect:_")

    developmentOnly(Spring.boot.devTools)

    /* SECURITY */
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt:0.12.5")


    implementation("io.github.oshai:kotlin-logging-jvm:_")

    implementation(Spring.boot.web)
    implementation(Spring.boot.webflux)

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:_")

    implementation("org.liquibase:liquibase-core")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

//    implementation("io.r2dbc:r2dbc-h2")
    // runtimeOnly("com.h2database:h2:2.2.224")

    /* БД */
    implementation(Spring.boot.data.jpa)
    //implementation(Spring.boot.data.r2dbc) // - Aсинхронная JPA
    runtimeOnly("org.postgresql:postgresql")

    /* Test Depends */
    testImplementation(Kotlin.test)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
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
