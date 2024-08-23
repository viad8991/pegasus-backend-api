package org.pegasus.backendapi.family.service

import org.pegasus.backendapi.family.FamilyRepository
import java.util.*

class FamilyInternalService(private val familyRepository: FamilyRepository) {

    fun findById(id: UUID) = familyRepository.findById(id)

}
