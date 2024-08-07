package org.pegasus.backendapi.transaction

import org.pegasus.backendapi.transaction.model.TransactionDto
import org.pegasus.backendapi.transaction.model.TransactionRequest
import org.pegasus.backendapi.transaction.model.TransactionResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/transaction")
class TransactionController(val transactionService: TransactionService) {

    @GetMapping
    fun all(): ResponseEntity<List<TransactionResponse>> {
        val transactions = transactionService.list()
        return if (transactions.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(transactions.map { transactionDto -> TransactionMapper.toResponse(transactionDto) })
        }
    }

    @PostMapping
    fun create(@RequestBody transactionRequest: TransactionRequest): ResponseEntity<TransactionResponse> {
        val transactionRequestDto = TransactionMapper.toDto(transactionRequest)
        val transactionResponseDto = transactionService.create(transactionRequestDto)
        return ResponseEntity.ok(TransactionMapper.toResponse(transactionResponseDto))
    }

}