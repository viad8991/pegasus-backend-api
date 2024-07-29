package org.pegasus.backendapi.security

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "security")
data class SecuritySettings(
    val salt: String,
    val expiration: Long = 1000 * 60 * 60 * 10
)
