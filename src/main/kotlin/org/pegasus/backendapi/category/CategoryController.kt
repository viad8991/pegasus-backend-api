package org.pegasus.backendapi.category

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.category.model.CategoryResponse
import org.pegasus.backendapi.category.model.TransactionType
import org.pegasus.backendapi.category.model.request.CategoryCreateRequest
import org.pegasus.backendapi.category.model.request.CategoryRequest
import org.pegasus.backendapi.category.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/category")
class CategoryController(val categoryService: CategoryService) {

    private val log = logger()

    @GetMapping
    fun fetchByType(
        @RequestParam(name = "type", required = false)
        type: TransactionType? = null
    ): ResponseEntity<List<CategoryResponse>> {
        val categories = categoryService.getByType(type)
        return if (categories.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(categories.map { category -> CategoryMapper.toResponse(category) })
        }
    }

    @PostMapping
    fun create(@RequestBody categoryRequest: CategoryCreateRequest): ResponseEntity<CategoryResponse> {
        val category = categoryService.create(CategoryMapper.toDto(categoryRequest))
        return ResponseEntity.ok(CategoryMapper.toResponse(category))
    }

    @PostMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody request: CategoryCreateRequest): ResponseEntity<CategoryResponse> {
        val category = categoryService.update(id, CategoryMapper.toDto(request))
        return if (category == null) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(CategoryMapper.toResponse(category))
        }

    }
}
