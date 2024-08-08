package org.pegasus.backendapi.category

import org.pegasus.backendapi.category.model.Category
import org.pegasus.backendapi.category.model.TransactionType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository : JpaRepository<Category, UUID> {

    @Query("SELECT c FROM Category c WHERE c.type = :type")
    fun findByType(type: TransactionType): Set<Category>

}
