package org.pegasus.backendapi.family.model

import org.pegasus.backendapi.family.model.dto.MemberTransactionDto
import org.pegasus.backendapi.family.model.response.MemberResponse
import org.pegasus.backendapi.transaction.model.TransactionMapper
import org.pegasus.backendapi.user.UserMapper
import org.pegasus.backendapi.utils.IMapper

class MemberTransactionMapper {

    companion object : IMapper<Nothing, MemberTransactionDto, Nothing, MemberResponse> {
        override fun toDto(entity: Nothing): MemberTransactionDto {
            throw UnsupportedOperationException("request->dto is not implemented")
        }

        override fun toResponse(dto: MemberTransactionDto) = MemberResponse(
            UserMapper.toResponse(dto.user),
            dto.transactionDto?.let { TransactionMapper.toResponse(it) }
        )
    }

}
