package org.pegasus.backendapi.transaction

import org.pegasus.backendapi.category.model.Category
import org.pegasus.backendapi.transaction.model.Transaction
import org.pegasus.backendapi.transaction.model.TransactionDto
import org.pegasus.backendapi.transaction.model.TransactionRequest
import org.pegasus.backendapi.transaction.model.TransactionResponse
import org.pegasus.backendapi.user.model.User

class TransactionMapper {
    companion object {

        fun toDto(transactionRequest: TransactionRequest) = TransactionDto(
            transactionRequest.id,
            transactionRequest.type,
            transactionRequest.amount,
            transactionRequest.date,
            transactionRequest.categoryId,
        )

        fun toEntity(dto: TransactionDto, category: Category, user: User) = Transaction(
            amount = dto.amount,
            type = dto.type,
            category = category,
            user = user
        )

        fun toDto(entity: Transaction) = TransactionDto(
            entity.id,
            entity.type,
            entity.amount,
            entity.date,
            entity.category.id
        )

        fun toResponse(dto: TransactionDto) = TransactionResponse(
            dto.id,
            dto.type,
            dto.amount,
            dto.date,
            dto.categoryId
        )

    }
}
