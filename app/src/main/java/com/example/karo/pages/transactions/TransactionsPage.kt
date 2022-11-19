package com.example.karo.pages.transactions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.karo.SampleData
import com.example.karo.models.Transaction
import com.example.karo.models.TransactionType

@Composable
fun TransactionsPage(studentId: String?, viewModel: TransactionsViewModel = hiltViewModel()) {
    if (studentId !== null) {
        viewModel.getTransactions(studentId)
        Transactions({transactions -> 
            if(transactions.isEmpty()){
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(all = 8.dp),
                    Alignment.Center
                ) { Text("No transaction(s) available.") }
            } else {
                TransactionList(transactions = transactions)
            }
        })
    } else {
        TransactionList(transactions = SampleData.transactionSample)
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
                text = "ID #" + transaction.id,
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