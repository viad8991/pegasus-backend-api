package org.pegasus.backendapi.category.exception

import java.util.*

class CategoryNotFoundException(id: UUID? = null) : RuntimeException("Category not found${id?.let { " for id $it" }}")
