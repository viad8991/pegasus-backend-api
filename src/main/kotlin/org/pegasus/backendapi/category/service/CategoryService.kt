package org.pegasus.backendapi.category.service

import jakarta.transaction.Transactional
import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.category.CategoryMapper
import org.pegasus.backendapi.category.CategoryRepository
import org.pegasus.backendapi.category.model.CategoryDto
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    private val log = logger()

    fun getAll(): Set<CategoryDto> {
        return categoryRepository.findAll().map { category -> CategoryMapper.toDto(category) }.toSet()
    }

    fun create(dto: CategoryDto): CategoryDto {
        val category = CategoryMapper.toEntity(dto)
        categoryRepository.save(category)
        return CategoryMapper.toDto(category)
    }

    @Transactional
    fun update(id: UUID, dto: CategoryDto): CategoryDto? {
        val findCategory = categoryRepository.findById(id)
        var result: CategoryDto? = null

        findCategory.ifPresent { category ->
            category.name = dto.name
            category.description = dto.description

            categoryRepository.save(category)

            result = CategoryMapper.toDto(category)
        }

        return result
    }

}
