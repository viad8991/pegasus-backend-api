package org.pegasus.backendapi.family.family

import org.pegasus.backendapi.family.FamilyAlreadyException
import org.pegasus.backendapi.family.FamilyMapper
import org.pegasus.backendapi.family.FamilyRepository
import org.pegasus.backendapi.family.model.Family
import org.pegasus.backendapi.family.model.FamilyDto
import org.pegasus.backendapi.user.service.UserInternalService
import org.springframework.stereotype.Service

@Service
class FamilyService(
    val familyRepository: FamilyRepository,
    val userService: UserInternalService
) {

    fun create(): FamilyDto {
        val user = userService.currentUser()

        if (user.family != null) {
            throw FamilyAlreadyException(username = user.username)
        }

        val newFamily = Family()
        val family = familyRepository.save(newFamily)

        userService.updateFamily(user, family)

        return FamilyMapper.toDto(family)
    }

}
