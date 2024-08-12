package org.pegasus.backendapi.category.service

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.category.CategoryRepository
import org.pegasus.backendapi.category.model.CategoryDto
import org.pegasus.backendapi.category.model.CategoryMapper
import org.pegasus.backendapi.category.model.TransactionType
import org.pegasus.backendapi.category.model.TransactionType.EXPENSE
import org.pegasus.backendapi.category.model.TransactionType.INCOME
import java.util.*

class CategoryService(private val categoryRepository: CategoryRepository) {

    private val log = logger()

    fun getByType(type: TransactionType?): Set<CategoryDto> {
        log.info { "new req by type with param '$type'" }

        val categories = when (type) {
            INCOME, EXPENSE -> categoryRepository.findByType(type)
            null -> categoryRepository.findAll()
        }
        return categories.map { category -> CategoryMapper.toDto(category) }.toSet()
    }

    fun getAll(): Set<CategoryDto> {
        return categoryRepository.findAll().map { category -> CategoryMapper.toDto(category) }.toSet()
    }

    fun create(dto: CategoryDto): CategoryDto {
        return categoryRepository.create(dto)
    }

    fun update(id: UUID, dto: CategoryDto): CategoryDto? {
        val newCategory = categoryRepository.update(id, dto)
        return newCategory?.let { CategoryMapper.toDto(it) }
    }

}
