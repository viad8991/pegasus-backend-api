package org.pegasus.backendapi.transaction

import org.pegasus.backendapi.transaction.model.Transaction
import org.pegasus.backendapi.transaction.model.TransactionRequest
import org.pegasus.backendapi.transaction.model.TransactionResponse
import org.pegasus.backendapi.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/transaction")
class TransactionController(val transactionService: TransactionService, val userService: UserService) {

    @GetMapping
    fun getAllTransactions(): List<Transaction> = transactionService.getAllTransactions()

    @PostMapping
    fun createTransaction(@RequestBody transactionRequest: TransactionRequest): ResponseEntity<TransactionResponse> {
        val user = userService.currentUser()

        val transactionRequestDto = TransactionMapper.toDto(transactionRequest)
        val transactionResponseDto = transactionService.create(transactionRequestDto, user)

        return ResponseEntity.ok(TransactionMapper.toResponse(transactionResponseDto))
    }

}