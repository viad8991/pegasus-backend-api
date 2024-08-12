package org.pegasus.backendapi.category.service

import org.pegasus.backendapi.category.CategoryRepository
import org.pegasus.backendapi.category.exception.CategoryNotFoundException
import org.pegasus.backendapi.category.model.Category
import java.util.*

open class CategoryInternalService(private val categoryRepository: CategoryRepository) {

    fun findById(id: UUID): Category {
        return categoryRepository.findById(id) ?: throw CategoryNotFoundException(id)
    }

}
