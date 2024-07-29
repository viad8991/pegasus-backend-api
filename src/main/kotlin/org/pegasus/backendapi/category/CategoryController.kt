package org.pegasus.backendapi.category

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.category.model.CategoryResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/category")
class CategoryController(val categoryService: CategoryService) {

    private val log = logger()

    @GetMapping
    fun getAll(): ResponseEntity<List<CategoryResponse>> {
        val categories = categoryService.getAll()
        return if (categories.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(categories.map { category -> CategoryMapper.toResponse(category) })
        }

    }

}
