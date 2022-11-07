package com.example.karo

object SampleData {
    // Sample conversation data
    val transactionSample = listOf(
        Transaction(
            1,
            100000.00,
            TransactionType.CREDIT,
            TransactionStatus.COMPLETED,
            "Fees invoiced for semester 3.2",
            "October 10th, 2022"
        ),
        Transaction(
            2,
            3280000.00,
            TransactionType.INVOICE,
            TransactionStatus.FAILED,
            "Fees invoiced for semester 3.2",
            "October 10th, 2022"
        ),
        Transaction(
            3,
            600000.00,
            TransactionType.CREDIT,
            TransactionStatus.COMPLETED,
            "Fees invoiced for semester 3.2",
            "October 10th, 2022"
        ),
        Transaction(
            4,
            600004.00,
            TransactionType.INVOICE,
            TransactionStatus.COMPLETED,
            "Fees invoiced for semester 3.2",
            "October 10th, 2022"
        )
    )
}