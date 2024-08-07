package org.pegasus.backendapi.category

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.category.model.CategoryRequest
import org.pegasus.backendapi.category.model.CategoryResponse
import org.pegasus.backendapi.category.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/category")
class CategoryController(val categoryService: CategoryService) {

    private val log = logger()

    @GetMapping
    fun all(): ResponseEntity<List<CategoryResponse>> {
        val categories = categoryService.getAll()
        return if (categories.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(categories.map { category -> CategoryMapper.toResponse(category) })
        }

    }

    @PostMapping
    fun create(@RequestBody categoryRequest: CategoryRequest): ResponseEntity<CategoryResponse> {
        val category = categoryService.create(CategoryMapper.toDto(categoryRequest))

        return ResponseEntity.ok(CategoryMapper.toResponse(category))
    }

    @PostMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody request: CategoryRequest): ResponseEntity<CategoryResponse> {
        val category = categoryService.update(id, CategoryMapper.toDto(request))
        return if (category == null) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(CategoryMapper.toResponse(category))
        }

    }
}
