package org.pegasus.backendapi.category.model

import org.pegasus.backendapi.category.model.request.CategoryCreateRequest
import org.pegasus.backendapi.category.model.request.CategoryRequest
import org.pegasus.backendapi.utils.IMapper

class CategoryMapper {

    companion object : IMapper<CategoryRequest, CategoryDto, Category, CategoryResponse> {

        override fun toEntity(dto: CategoryDto) = Category(
            name = dto.name,
            type = dto.type,
            description = dto.description
        )

        override fun toDto(entity: Category) = CategoryDto(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            created = entity.created,
            update = entity.update,
            type = entity.type,
        )

        override fun toResponse(dto: CategoryDto) = CategoryResponse(
            id = dto.id,
            name = dto.name,
            type = dto.type,
            description = dto.description,
            created = dto.created,
            update = dto.update,
        )

        override fun toDto(request: CategoryRequest): CategoryDto = CategoryDto(
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
