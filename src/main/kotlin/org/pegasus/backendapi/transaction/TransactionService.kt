package org.pegasus.backendapi.transaction

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.category.exception.CategoryNotFoundException
import org.pegasus.backendapi.category.service.CategoryInternalService
import org.pegasus.backendapi.transaction.model.TransactionDto
import org.pegasus.backendapi.user.service.UserInternalService
import org.springframework.stereotype.Service

@Service
class TransactionService(
    val transactionRepository: TransactionRepository,
    val categoryService: CategoryInternalService,
    val userService: UserInternalService
) {

    private val log = logger()

    fun list(): List<TransactionDto> {
        val user = userService.currentUser()

        val transactions = transactionRepository.findAllByUser(user)
        return transactions.map { transaction -> TransactionMapper.toDto(transaction) }
    }

    fun create(transactionDto: TransactionDto): TransactionDto {
        log.info { "new transaction $transactionDto" }
        val categoryId = transactionDto.category?.id ?: throw CategoryNotFoundException()

        val user = userService.currentUser()
        val category = categoryService.findById(categoryId)
        val transaction = TransactionMapper.toEntity(transactionDto, category, user)

        transactionRepository.save(transaction)
        log.info { "Creating transaction for user ${user.id}: $transaction" }
        return TransactionMapper.toDto(transaction)
    }

}
