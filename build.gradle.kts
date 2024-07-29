import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    war

    checkstyle

    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("plugin.allopen")
    kotlin("plugin.serialization") version "2.0.0"

    id("org.springframework.boot")
    id("io.spring.dependency-management")

    id("org.liquibase.gradle")
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
    implementation(Kotlin.stdlib)
    implementation("org.jetbrains.kotlin:kotlin-reflect:_")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:_")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")

    /* DevTools */
    developmentOnly(Spring.boot.devTools)

    /* SECURITY */
    implementation(Spring.boot.security)
    implementation(Spring.boot.oauth2Client)
    runtimeOnly("io.jsonwebtoken:jjwt-impl:_")
    implementation("io.jsonwebtoken:jjwt-api:_")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:_")

    /* spring web */
    implementation(Spring.boot.web)
    implementation(Spring.boot.webflux)

    /* ??? */
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:_")

    /* json parse */
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")

    /* БД */
    implementation(Spring.boot.data.jpa)  // implementation(Spring.boot.data.r2dbc) // - Aсинхронная JPA
    implementation("org.liquibase:liquibase-core:_")
    runtimeOnly("org.postgresql:postgresql:_")

    /* Test Depends */
    testImplementation(Kotlin.test)
    testImplementation(Spring.boot.test)
    testImplementation(Testing.assertj.db)
    testImplementation(Testing.assertj.core)
    testImplementation("io.zonky.test:embedded-postgres:_")
    testImplementation("com.github.database-rider:rider-core:_")
    testImplementation("com.github.database-rider:rider-spring:_")
    testImplementation("io.zonky.test:embedded-database-spring-test:_")

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
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
        }
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.transaction.Transactional")
    annotation("jakarta.persistence.MappedSuperclass")
}

repositories {
    mavenCentral()
}
