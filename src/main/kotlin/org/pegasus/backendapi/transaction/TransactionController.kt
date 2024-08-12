package org.pegasus.backendapi.transaction

import org.pegasus.backendapi.transaction.model.CreateRequest
import org.pegasus.backendapi.transaction.model.TransactionMapper
import org.pegasus.backendapi.transaction.model.TransactionResponse
import org.pegasus.backendapi.transaction.service.TransactionService
import org.springframework.http.ResponseEntity

class TransactionController(private val transactionService: TransactionService) {

    fun all(): List<TransactionResponse> {
        val transactions = transactionService.list()
        return transactions.map { transactionDto -> TransactionMapper.toResponse(transactionDto) }
    }

    fun create(request: CreateRequest): TransactionResponse {
        val transactionRequestDto = TransactionMapper.toDto(request)
        val transactionResponseDto = transactionService.create(transactionRequestDto)
        return TransactionMapper.toResponse(transactionResponseDto)
    }

}
