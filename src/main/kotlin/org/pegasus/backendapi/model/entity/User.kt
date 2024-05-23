package org.pegasus.backendapi.model.entity

import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant
import java.util.*

@Entity
@Table(name = "users")
class User(

    private val username: String,

    private val password: String,

    val email: String,

    @Enumerated(EnumType.STRING)
    val role: Role

) : UserDetails {

    @Id
    val id: UUID = UUID.randomUUID()

    val created: Instant = Instant.now()

    @UpdateTimestamp
    val update: Instant = Instant.now()

    @Version
    val version: Long = 0

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableSetOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
