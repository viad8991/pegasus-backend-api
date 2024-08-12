package org.pegasus.backendapi.family.controller

import org.pegasus.backendapi.family.model.FamilyMapper
import org.pegasus.backendapi.family.model.MemberTransactionMapper
import org.pegasus.backendapi.family.model.response.FamilyResponse
import org.pegasus.backendapi.family.model.response.MemberResponse
import org.pegasus.backendapi.family.service.FamilyService

class FamilyHandler(private val familyService: FamilyService) {

    fun create(): FamilyResponse {
        val familyDto = familyService.create()
        return FamilyMapper.toResponse(familyDto)
    }

    fun members(): Set<MemberResponse> {
        val members = familyService.getMembers()
        return members.map { member -> MemberTransactionMapper.toResponse(member) }.toSet()
    }

    fun transactions(): Set<MemberResponse> {
        val transaction = familyService.getTransactions()
        return transaction.map { member -> MemberTransactionMapper.toResponse(member) }.toSet()
    }

}
