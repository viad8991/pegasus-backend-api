package org.pegasus.backendapi.family.controller

import org.pegasus.backendapi.family.model.FamilyMapper
import org.pegasus.backendapi.family.model.MemberTransactionMapper
import org.pegasus.backendapi.family.model.response.FamilyResponse
import org.pegasus.backendapi.family.model.response.MemberResponse
import org.pegasus.backendapi.family.service.FamilyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/family")
class FamilyController(val familyService: FamilyService) {

    @PostMapping
    fun create(): ResponseEntity<FamilyResponse> {
        val familyDto = familyService.create()
        return ResponseEntity.ok(FamilyMapper.toResponse(familyDto))
    }

    @GetMapping("/members")
    fun getMembers(): ResponseEntity<Set<MemberResponse>> {
        val members = familyService.getMembers()
        val response = members.map { member -> MemberTransactionMapper.toResponse(member) }.toSet()

        return ResponseEntity.ok(response)
    }

    @GetMapping("/transactions")
    fun getTransactions(): ResponseEntity<Set<MemberResponse>> {
        val transaction = familyService.getTransactions()
        val response = transaction.map { member -> MemberTransactionMapper.toResponse(member) }.toSet()

        return ResponseEntity.ok(response)
    }

}
