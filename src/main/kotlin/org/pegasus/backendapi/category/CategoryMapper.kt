package org.pegasus.backendapi.category

import org.pegasus.backendapi.category.model.Category
import org.pegasus.backendapi.category.model.CategoryDto
import org.pegasus.backendapi.category.model.CategoryResponse

class CategoryMapper {

    companion object {

        fun toEntity(dto: CategoryDto) = Category(
            name = dto.name,
            description = dto.description
        )

        fun toDto(entity: Category) = CategoryDto(
            entity.id, entity.name, entity.description
        )

        fun toResponse(dto: CategoryDto) = CategoryResponse(
            dto.id, dto.name, dto.description
        )

    }

}
