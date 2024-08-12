package org.pegasus.backendapi.user.service

import org.pegasus.backendapi.family.model.entity.Family
import org.pegasus.backendapi.user.UserRepository
import org.pegasus.backendapi.user.model.User
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

open class UserInternalService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username) ?: throw BadCredentialsException("unauthorized request")
    }

    fun currentUser(): User {
        val user: Any? = SecurityContextHolder.getContext().authentication.principal
        return if (user == null || user !is User) {
            throw BadCredentialsException("unauthorized request")
        } else {
            user
        }
    }

    fun updateFamily(user: User, family: Family): User {
        user.family = family
        return userRepository.update(user)
    }

}
