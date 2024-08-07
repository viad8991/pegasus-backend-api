package org.pegasus.backendapi.family.controller

import org.pegasus.backendapi.family.FamilyMapper
import org.pegasus.backendapi.family.family.FamilyService
import org.pegasus.backendapi.family.model.FamilyResponse
import org.springframework.http.ResponseEntity
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

}
