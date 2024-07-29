package org.pegasus.backendapi.transaction

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.transaction.model.TransactionDto
import org.pegasus.backendapi.transaction.model.Transaction
import org.pegasus.backendapi.user.model.User
import org.pegasus.backendapi.category.CategoryRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(
    val transactionRepository: TransactionRepository,
    val categoryRepository: CategoryRepository
) {

    private val log = logger()

    fun getAllTransactions(): List<Transaction> = transactionRepository.findAll()

    fun create(transactionDto: TransactionDto, user: User): TransactionDto {
        if (transactionDto.id != null) {
            throw RuntimeException("exist")
        }
        val category = categoryRepository.findById(transactionDto.categoryId).orElseThrow {
            throw RuntimeException("category error")
        }

        val transaction = Transaction(
            type = transactionDto.type,
            amount = transactionDto.amount,
            category = category,
            user = user
        )

        log.info { "transaction $transaction" }

        transactionRepository.save(transaction)

        return TransactionMapper.toDto(transaction)
    }

}
