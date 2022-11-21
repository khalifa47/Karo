package com.example.karo.pages.transactions

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.karo.components.ProgressBar
import com.example.karo.utils.Response

@Composable
fun Transactions(
    transactionsContent: @Composable (plans: Transactions) -> Unit,
    viewModel: TransactionsViewModel = hiltViewModel()
) {
    when (val transactionsResponse = viewModel.transactionsResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> transactionsContent(transactionsResponse.data)
        is Response.Failure -> print(transactionsResponse.e)
    }
}