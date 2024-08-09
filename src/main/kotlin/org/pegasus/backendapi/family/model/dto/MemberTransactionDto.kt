package org.pegasus.backendapi.family.model.dto

import org.pegasus.backendapi.transaction.model.TransactionDto
import org.pegasus.backendapi.user.model.UserDto
import org.pegasus.backendapi.utils.Dto

data class MemberTransactionDto(val user: UserDto, val transactionDto: TransactionDto?) : Dto
