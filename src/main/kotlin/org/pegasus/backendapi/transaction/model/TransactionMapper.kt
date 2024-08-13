package org.pegasus.backendapi.transaction.model

import org.pegasus.backendapi.category.model.Category
import org.pegasus.backendapi.category.model.CategoryMapper
import org.pegasus.backendapi.user.model.User

class TransactionMapper {
    companion object {

        fun toDto(request: CreateRequest) = TransactionDto(
            id = null,
            amount = request.amount,
            category = request.category?.let { CategoryMapper.toDto(it) },
        )

        fun toEntity(dto: TransactionDto, user: User, category: Category) = Transaction(
            amount = dto.amount,
            category = category,
            user = user
        )

        fun toDto(entity: Transaction) = TransactionDto(
            id = entity.id,
            amount = entity.amount,
            created = entity.created,
            category = CategoryMapper.toDto(entity.category)
        )

        fun toResponse(dto: TransactionDto) = TransactionResponse(
            id = dto.id,
            amount = dto.amount,
            created = dto.created,
            category = dto.category?.let { CategoryMapper.toResponse(it) }
        )

    }
}
