package com.example.karo.models

enum class TransactionType{ INVOICE, CREDIT }
enum class TransactionStatus{ COMPLETED, FAILED }

data class Transaction(
    var id: String,
    var amount: Double,
    var type: TransactionType,
    var status: TransactionStatus,
    var description: String,
    var date: String
)