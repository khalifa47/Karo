package com.example.karo.pages.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.karo.PageTitle
import com.example.karo.Routes
import com.example.karo.components.MainViewModel

@Composable
fun LoginPage(viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Routes.Home)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PageTitle(title = "Login")
        Text(text = "Login.", style = MaterialTheme.typography.bodyLarge)
    }
}