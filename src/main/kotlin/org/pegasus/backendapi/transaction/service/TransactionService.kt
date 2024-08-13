package org.pegasus.backendapi.transaction.service

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.category.exception.CategoryNotFoundException
import org.pegasus.backendapi.category.service.CategoryInternalService
import org.pegasus.backendapi.transaction.TransactionRepository
import org.pegasus.backendapi.transaction.model.TransactionDto
import org.pegasus.backendapi.transaction.model.TransactionMapper
import org.pegasus.backendapi.user.service.UserInternalService

class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val categoryService: CategoryInternalService,
    private val userService: UserInternalService
) {

    private val log = logger()

    fun list(): List<TransactionDto> {
        val user = userService.currentUser()

        val transactions = transactionRepository.findAllByUser(user)
        return transactions.map { transaction -> TransactionMapper.toDto(transaction) }
    }

    fun create(dto: TransactionDto): TransactionDto {
        log.info { "new transaction $dto" }

        val user = userService.currentUser()

        val categoryId = dto.category?.id ?: throw CategoryNotFoundException()
        val category = categoryService.findById(categoryId)

        val transaction = transactionRepository.create(dto, user, category)
        log.info { "Creating transaction for user ${user.id}: $transaction" }
        return TransactionMapper.toDto(transaction)
    }

}
