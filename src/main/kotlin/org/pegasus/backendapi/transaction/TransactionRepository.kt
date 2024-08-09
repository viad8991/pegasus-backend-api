package org.pegasus.backendapi.transaction

import org.pegasus.backendapi.transaction.model.Transaction
import org.pegasus.backendapi.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository : JpaRepository<Transaction, UUID> {

    @Query("SELECT t FROM Transaction t WHERE t.user = :user")
    fun findAllByUser(user: User): Set<Transaction>


    @Query("SELECT t FROM Transaction t WHERE t.user IN :users")
    fun findAllByUsers(users: Set<User>): Set<Transaction>

}
