package org.pegasus.backendapi.transaction

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.category.model.Category
import org.pegasus.backendapi.transaction.model.Transaction
import org.pegasus.backendapi.transaction.model.TransactionDto
import org.pegasus.backendapi.transaction.model.TransactionMapper
import org.pegasus.backendapi.user.model.User

@Transactional
class TransactionRepository(private val entityManager: EntityManager) {

    private val log = logger()

    fun findAllByUser(user: User): Set<Transaction> {
        return entityManager.createQuery("SELECT t FROM Transaction t WHERE t.user = :user", Transaction::class.java)
            .setParameter("user", user)
            .resultList
            .toSet()
    }

    fun findAllByUsers(users: Set<User>): Set<Transaction> {
        return entityManager.createQuery("SELECT t FROM Transaction t WHERE t.user IN :users", Transaction::class.java)
            .setParameter("users", users)
            .resultList
            .toSet()
    }

    fun create(dto: TransactionDto, user: User, category: Category): Transaction {
        val transaction = TransactionMapper.toEntity(dto, user, category)

        log.info { "start create transaction $transaction" }

        entityManager.persist(transaction)
        return transaction
    }

}
