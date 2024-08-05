package org.pegasus.backendapi.category

import org.pegasus.backendapi.category.model.Category
import org.pegasus.backendapi.category.model.CategoryDto
import org.pegasus.backendapi.category.model.CategoryRequest
import org.pegasus.backendapi.category.model.CategoryResponse

class CategoryMapper {

    companion object {

        fun toEntity(dto: CategoryDto) = Category(
            name = dto.name,
            description = dto.description
        )

        fun toDto(entity: Category) = CategoryDto(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            created = entity.created,
            update = entity.update
        )

        fun toResponse(dto: CategoryDto) = CategoryResponse(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            created = dto.created,
            update = dto.update,
        )

        fun toDto(request: CategoryRequest): CategoryDto = CategoryDto(
            id = null,
            name = request.name,
            description = request.description
        )

    }

}
