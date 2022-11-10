package com.example.karo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.karo.components.MainViewModel
import com.example.karo.pages.auth.LoginPage
import com.example.karo.ui.theme.KaroTheme

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KaroTheme {
                val viewModel: MainViewModel = viewModel()

                LoginPage(viewModel)
            }
        }
    }
}