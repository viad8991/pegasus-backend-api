package org.pegasus.backendapi.family.model

import org.pegasus.backendapi.family.model.dto.FamilyDto
import org.pegasus.backendapi.family.model.entity.Family
import org.pegasus.backendapi.family.model.response.FamilyResponse
import org.pegasus.backendapi.user.UserMapper
import org.pegasus.backendapi.utils.IMapper

class FamilyMapper {
    companion object : IMapper<Nothing, FamilyDto, Family, FamilyResponse> {

        override fun toDto(entity: Family) = FamilyDto(
            entity.id,
            entity.members.map { user -> UserMapper.toDto(user) },
            entity.created,
            entity.update,
        )

        override fun toResponse(dto: FamilyDto) = FamilyResponse(
            dto.id,
            dto.update,
            dto.created,
        )

    }
}
