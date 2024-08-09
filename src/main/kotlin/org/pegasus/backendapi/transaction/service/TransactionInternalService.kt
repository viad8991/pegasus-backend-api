package org.pegasus.backendapi.transaction.service

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.transaction.TransactionRepository
import org.pegasus.backendapi.transaction.model.Transaction
import org.pegasus.backendapi.user.model.User

class TransactionInternalService(private val transactionRepository: TransactionRepository) {

    private val log = logger()

    fun getLatestForUsers(users: Set<User>): Set<Transaction> {
        val transactions = transactionRepository.findAllByUsers(users)
        return transactions.sortedBy { transaction -> transaction.created }.toSet()
    }

}
