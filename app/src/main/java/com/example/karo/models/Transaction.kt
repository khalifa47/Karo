package com.example.karo.models

enum class TransactionType{ INVOICE, CREDIT }
enum class TransactionStatus{ COMPLETED, FAILED }

data class Transaction(
    var id: String? = null,
    var amount: String? = null,
    var type: TransactionType? = null,
    var status: TransactionStatus? = null,
    var description: String? = null,
    var date: String? = null
)