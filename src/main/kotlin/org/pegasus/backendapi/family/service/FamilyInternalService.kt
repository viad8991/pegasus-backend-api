package org.pegasus.backendapi.family.service

import org.pegasus.backendapi.transaction.TransactionRepository
import org.pegasus.backendapi.transaction.model.Transaction
import org.pegasus.backendapi.user.model.User
import org.springframework.stereotype.Service

@Service
class FamilyInternalService(val transactionRepository: TransactionRepository) {

    fun getLatestForUsers(users: Set<User>): Set<Transaction> {
        val transactions = transactionRepository.findAllByUsers(users)
        return transactions.sortedBy { transaction -> transaction.created }.toSet()
    }

}
