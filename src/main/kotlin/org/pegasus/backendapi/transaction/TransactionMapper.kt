package org.pegasus.backendapi.transaction

import org.pegasus.backendapi.category.CategoryMapper
import org.pegasus.backendapi.category.model.Category
import org.pegasus.backendapi.transaction.model.Transaction
import org.pegasus.backendapi.transaction.model.TransactionDto
import org.pegasus.backendapi.transaction.model.TransactionRequest
import org.pegasus.backendapi.transaction.model.TransactionResponse
import org.pegasus.backendapi.user.model.User

class TransactionMapper {
    companion object {

        fun toDto(request: TransactionRequest) = TransactionDto(
            id = null,
            type = request.type,
            amount = request.amount,
            category = request.category?.let { CategoryMapper.toDto(it) },
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
            CategoryMapper.toDto(entity.category)
        )

        fun toResponse(dto: TransactionDto) = TransactionResponse(
            id = dto.id,
            type =  dto.type,
            amount =  dto.amount,
            date =  dto.date,
            category = dto.category?.let { CategoryMapper.toResponse(it) }
        )

    }
}
