package org.pegasus.backendapi.user

import org.pegasus.backendapi.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE username = :username")
    fun findByUsername(username: String): User?

}
