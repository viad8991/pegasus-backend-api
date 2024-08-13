package org.pegasus.backendapi.category

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.category.model.Category
import org.pegasus.backendapi.category.model.CategoryDto
import org.pegasus.backendapi.category.model.CategoryMapper
import org.pegasus.backendapi.category.model.TransactionType
import java.util.*

@Transactional
class CategoryRepository(private val entityManager: EntityManager) {

    private val log = logger()

    fun create(dto: CategoryDto): CategoryDto {
        val category: Category = CategoryMapper.toEntity(dto)
        entityManager.persist(category)
        return CategoryMapper.toDto(category)
    }

    fun update(id: UUID, dto: CategoryDto): Category? {
        return findById(id)?.also { category ->
            category.name = dto.name
            category.type = dto.type
            category.description = dto.description
        }
    }

    fun findById(id: UUID): Category? {
        return try {
            entityManager.find(Category::class.java, id)
        } catch (ex: EntityNotFoundException) {
            log.warn(ex) { "nor found to id $id" }
            null
        }
    }

    fun findAll(): Set<Category> {
        return entityManager.createQuery("SELECT c FROM Category c", Category::class.java)
            .resultList
            .toSet()
    }

    fun findByType(type: TransactionType): Set<Category> {
        return entityManager.createQuery("SELECT c FROM Category c WHERE c.type = :type", Category::class.java)
            .setParameter("type", type)
            .resultList
            .toSet()
    }

}
