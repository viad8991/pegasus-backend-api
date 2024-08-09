package org.pegasus.backendapi.family.model.dto

import org.pegasus.backendapi.transaction.model.TransactionDto
import org.pegasus.backendapi.user.model.UserDto
import org.pegasus.backendapi.utils.IDto

data class MemberTransactionDto(
    val user: UserDto,
    val transactionDto: TransactionDto?
) : IDto
