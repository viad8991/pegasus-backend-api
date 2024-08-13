package org.pegasus.backendapi.family.model

import org.pegasus.backendapi.family.model.dto.FamilyDto
import org.pegasus.backendapi.family.model.entity.Family
import org.pegasus.backendapi.family.model.response.FamilyResponse
import org.pegasus.backendapi.user.UserMapper
import org.pegasus.backendapi.utils.IMapper

class FamilyMapper {
    companion object : IMapper<Nothing, FamilyDto, Family, FamilyResponse> {

        override fun toDto(entity: Family) = FamilyDto(
            id = entity.id,
            members = entity.members.map { user -> UserMapper.toDto(user) },
            created = entity.created,
            update = entity.update,
        )

        override fun toResponse(dto: FamilyDto) = FamilyResponse(
            id = dto.id,
            update = dto.update,
            created = dto.created,
        )

    }
}
