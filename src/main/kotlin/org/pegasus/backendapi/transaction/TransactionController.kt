package org.pegasus.backendapi.transaction

import org.pegasus.backendapi.transaction.model.CreateRequest
import org.pegasus.backendapi.transaction.model.TransactionMapper
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
    fun create(@RequestBody request: CreateRequest): ResponseEntity<TransactionResponse> {
        val transactionRequestDto = TransactionMapper.toDto(request)
        val transactionResponseDto = transactionService.create(transactionRequestDto)
        return ResponseEntity.ok(TransactionMapper.toResponse(transactionResponseDto))
    }

}
