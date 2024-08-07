package org.pegasus.backendapi.family

import org.pegasus.backendapi.family.model.Family
import org.pegasus.backendapi.family.model.FamilyDto
import org.pegasus.backendapi.family.model.FamilyResponse
import org.pegasus.backendapi.user.UserMapper

class FamilyMapper {
    companion object {

        fun toDto(entity: Family) = FamilyDto(
            entity.id,
            entity.members.map { user -> UserMapper.toDto(user) },
            entity.created,
            entity.update,
        )

        fun toResponse(dto: FamilyDto) = FamilyResponse(
            dto.id,
            dto.update,
            dto.created,
        )

    }
}
