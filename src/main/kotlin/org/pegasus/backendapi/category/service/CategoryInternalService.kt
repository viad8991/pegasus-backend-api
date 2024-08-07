package org.pegasus.backendapi.category.service

import org.pegasus.backendapi.category.CategoryRepository
import org.pegasus.backendapi.category.exception.CategoryNotFoundException
import org.pegasus.backendapi.category.model.Category
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrElse

@Service
class CategoryInternalService(val categoryRepository: CategoryRepository) {

    fun findById(id: UUID): Category = categoryRepository.findById(id).getOrElse {
        throw CategoryNotFoundException()
    }

}
