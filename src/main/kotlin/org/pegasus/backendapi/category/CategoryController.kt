package org.pegasus.backendapi.category

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.category.model.CategoryMapper
import org.pegasus.backendapi.category.model.CategoryResponse
import org.pegasus.backendapi.category.model.TransactionType
import org.pegasus.backendapi.category.model.request.CategoryCreateRequest
import org.pegasus.backendapi.category.service.CategoryService
import java.util.*

class CategoryController(private val categoryService: CategoryService) {

    private val log = logger()

    fun fetchByType(type: TransactionType? = null): List<CategoryResponse> {
        log.info { "new request to fetch with param $type" }

        val categories = categoryService.getByType(type)
        return categories.map { CategoryMapper.toResponse(it) }
    }

    fun create(request: CategoryCreateRequest): CategoryResponse {
        log.info { "new request to create with param $request" }

        val category = categoryService.create(CategoryMapper.toDto(request))
        return CategoryMapper.toResponse(category)
    }

    fun update(id: UUID, request: CategoryCreateRequest): CategoryResponse? {
        log.info { "new request to update with param $id $request" }

        val category = categoryService.update(id, CategoryMapper.toDto(request))
        return category?.let { CategoryMapper.toResponse(it) }
    }

}
