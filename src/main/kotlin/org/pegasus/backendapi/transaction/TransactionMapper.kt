package org.pegasus.backendapi.transaction

import org.pegasus.backendapi.category.CategoryMapper
import org.pegasus.backendapi.category.model.Category
import org.pegasus.backendapi.transaction.model.Transaction
import org.pegasus.backendapi.transaction.model.TransactionDto
import org.pegasus.backendapi.transaction.model.CreateRequest
import org.pegasus.backendapi.transaction.model.TransactionResponse
import org.pegasus.backendapi.user.model.User

class TransactionMapper {
    companion object {

        fun toDto(request: CreateRequest) = TransactionDto(
            id = null,
            amount = request.amount,
            category = request.category?.let { CategoryMapper.toDto(it) },
        )

        fun toEntity(dto: TransactionDto, user: User, category: Category?) = Transaction(
            amount = dto.amount,
            category = category,
            user = user
        )

        fun toDto(entity: Transaction) = TransactionDto(
            entity.id,
            entity.amount,
            entity.date,
            entity.category?.let { CategoryMapper.toDto(it) }
        )

        fun toResponse(dto: TransactionDto) = TransactionResponse(
            id = dto.id,
            amount =  dto.amount,
            date =  dto.date,
            category = dto.category?.let { CategoryMapper.toResponse(it) }
        )

    }
}
