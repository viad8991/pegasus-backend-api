package org.pegasus.backendapi.family

import org.pegasus.backendapi.family.model.Family
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FamilyRepository : JpaRepository<Family, UUID>
