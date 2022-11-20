package com.example.karo.pages.feepayment

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.karo.components.ProgressBar
import com.example.karo.models.Wallet
import com.example.karo.utils.Response

@Composable
fun Wallet(
    walletContent: @Composable (wallet: Wallet) -> Unit,
    viewModel: FeePaymentViewModel = hiltViewModel()
) {
    when (val walletResponse = viewModel.walletResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> walletResponse.data?.let { walletContent(it) }
        is Response.Failure -> print(walletResponse.e)
    }
}