package com.example.karo.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.karo.*
import com.example.karo.components.MainViewModel

@Composable
fun FeePaymentPage(viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Routes.Home)

    Column {
        PageTitle(title = "Fee Payment")
        FeePaymentForm()
    }
}