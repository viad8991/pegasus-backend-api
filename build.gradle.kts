import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    war

    checkstyle

    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"

    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
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
    implementation("org.jetbrains.kotlin:kotlin-reflect:_")

    developmentOnly(Spring.boot.devTools)

    implementation(Spring.boot.web)
    implementation(Spring.boot.data.jpa)

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:_")

    implementation("org.liquibase:liquibase-core")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    runtimeOnly("org.postgresql:postgresql")

    // test deps

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
