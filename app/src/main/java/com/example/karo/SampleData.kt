package com.example.karo

import com.example.karo.models.Transaction
import com.example.karo.models.TransactionStatus
import com.example.karo.models.TransactionType

object SampleData {
    // Sample conversation data
    val transactionSample = listOf(
        Transaction(
            "1",
            "100000",
            TransactionType.CREDIT,
            TransactionStatus.COMPLETED,
            "Fees invoiced for semester 3.2",
            "October 10th, 2022"
        ),
        Transaction(
            "2",
            "3280000",
            TransactionType.INVOICE,
            TransactionStatus.FAILED,
            "Fees invoiced for semester 3.2",
            "October 10th, 2022"
        ),
        Transaction(
            "3",
            "600000",
            TransactionType.CREDIT,
            TransactionStatus.COMPLETED,
            "Fees invoiced for semester 3.2",
            "October 10th, 2022"
        ),
        Transaction(
            "4",
            "600004",
            TransactionType.INVOICE,
            TransactionStatus.COMPLETED,
            "Fees invoiced for semester 3.2",
            "October 10th, 2022"
        )
    )
}