package org.pegasus.backendapi.category

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.category.model.CategoryDto
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    private val log = logger()

    fun getAll(): Set<CategoryDto> {
        return categoryRepository.findAll().map { category -> CategoryMapper.toDto(category) }.toSet()
    }

}
