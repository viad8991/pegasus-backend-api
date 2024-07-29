package org.pegasus.backendapi.transaction

import org.pegasus.backendapi.transaction.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository : JpaRepository<Transaction, UUID>
