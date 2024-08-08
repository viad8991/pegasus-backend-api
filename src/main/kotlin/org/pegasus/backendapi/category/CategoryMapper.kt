package org.pegasus.backendapi.category

import org.pegasus.backendapi.category.model.Category
import org.pegasus.backendapi.category.model.CategoryDto
import org.pegasus.backendapi.category.model.CategoryResponse
import org.pegasus.backendapi.category.model.request.CategoryCreateRequest
import org.pegasus.backendapi.category.model.request.CategoryRequest

class CategoryMapper {

    companion object {

        fun toEntity(dto: CategoryDto) = Category(
            name = dto.name,
            type = dto.type,
            description = dto.description
        )

        fun toDto(entity: Category) = CategoryDto(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            created = entity.created,
            update = entity.update,
            type = entity.type,
        )

        fun toResponse(dto: CategoryDto) = CategoryResponse(
            id = dto.id,
            name = dto.name,
            type = dto.type,
            description = dto.description,
            created = dto.created,
            update = dto.update,
        )

        fun toDto(request: CategoryRequest): CategoryDto = CategoryDto(
            id = request.id,
            name = request.name,
            description = request.description,
            type = request.type
        )

        fun toDto(request: CategoryCreateRequest): CategoryDto = CategoryDto(
            name = request.name,
            description = request.description,
            type = request.type
        )

    }

}
