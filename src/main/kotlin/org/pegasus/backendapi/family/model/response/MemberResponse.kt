package org.pegasus.backendapi.family.model.response

import org.pegasus.backendapi.transaction.model.TransactionResponse
import org.pegasus.backendapi.user.model.UserResponse
import org.pegasus.backendapi.utils.Response

data class MemberResponse(val user: UserResponse, val transaction: TransactionResponse?) : Response
