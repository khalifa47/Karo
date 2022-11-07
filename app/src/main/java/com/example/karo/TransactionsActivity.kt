package com.example.karo

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.karo.ui.theme.KaroTheme

enum class TransactionType{ INVOICE, CREDIT }
enum class TransactionStatus{ COMPLETED, FAILED }

data class Transaction(
    val id: Int,
    val amount: Double,
    val type: TransactionType,
    val status: TransactionStatus,
    val description: String,
    val date: String
)

@Composable
fun Transactions(onNavigateToPayments: () -> Unit) {
    Column{
        PageTitle(title = "Transactions")
        TransactionList(transactions = SampleData.transactionSample)
        Button(onClick = onNavigateToPayments) {
            Text(text = "Navigate to Payments")
        }
    }
}

@Composable
fun TransactionCard(transaction: Transaction) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)) {
        Column {
            Text(
                text = transaction.description,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = transaction.date,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "ID #" + transaction.id.toString(),
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.caption
            )
        }
        Text(
            text = (if(transaction.type == TransactionType.INVOICE) "-" else "+") + "KES " + "%,.2f".format(transaction.amount),
            color = if(transaction.type == TransactionType.INVOICE) MaterialTheme.colors.error else MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.subtitle2
        )
    }
    Row {
        Divider(startIndent = 5.dp, thickness = 0.5.dp, color = MaterialTheme.colors.onBackground)
    }
}

@Composable
fun TransactionList(transactions: List<Transaction>) {
    LazyColumn {
        items(transactions) { transaction ->
            TransactionCard(transaction)
        }
    }
}

@Preview(showBackground = true, name = "Transaction Light")
@Composable
fun TransactionPreview() {
    KaroTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            TransactionCard(transaction = Transaction(1, 100000.00, TransactionType.CREDIT, TransactionStatus.FAILED, "Fees invoiced for semester 3.2", "October 10th, 2022"))
        }
    }
}

@Preview(showBackground = true, name = "Transaction Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TransactionPreviewDark() {
    KaroTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            TransactionCard(transaction = Transaction(1, 100000.00, TransactionType.CREDIT, TransactionStatus.FAILED, "Fees invoiced for semester 3.2", "October 10th, 2022"))
        }
    }
}

@Preview(showBackground = true, name = "Transaction List Light")
@Composable
fun TransactionListPreview() {
    KaroTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            Column{
                PageTitle(title = "Transaction History")
                TransactionList(transactions = SampleData.transactionSample)
            }
        }
    }
}

@Preview(showBackground = true, name = "Transaction List Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TransactionListPreviewDark() {
    KaroTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            Column{
                PageTitle(title = "Transactions")
                TransactionList(transactions = SampleData.transactionSample)
            }
        }
    }
}