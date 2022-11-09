package com.example.karo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.karo.components.AppScaffold
import com.example.karo.ui.theme.KaroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KaroTheme {
                AppScaffold()
            }
        }
    }
}