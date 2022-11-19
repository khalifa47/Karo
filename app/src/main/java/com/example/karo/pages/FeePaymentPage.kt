package com.example.karo.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karo.*
import com.example.karo.components.MainViewModel
import com.example.karo.ui.theme.KaroTheme

@Composable
fun FeePaymentPage(viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Routes.Home)
    FeePaymentForm()
}

@Composable
fun FeePaymentForm() {
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        WalletInfo(balance = 300000.00)
        FeePaymentField(label = "Payment Description")
        FeePaymentField(label = "Amount")
        ConfirmButton(label = "Confirm Payment", action = { doNothing() })
    }
}

@Composable
fun WalletInfo(balance: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 30.dp)
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Wallet Balance", fontSize = 20.sp)
            Text(text = "KES ${"%,.2f".format(balance)}", fontSize = 30.sp, color = MaterialTheme.colors.primary)
        }
    }

    Divider(thickness = 0.5.dp, color = MaterialTheme.colors.onBackground, modifier = Modifier.padding(bottom = 20.dp))
}

@Composable
fun FeePaymentField(label: String) {
    var text by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 4.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(text = label) }
        )
    }
}

@Preview(name = "Wallet Info Preview Light", showBackground = true)
@Composable
fun WalletInfoPreviewLight() {
    KaroTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            WalletInfo(balance = 300000.00)
        }
    }
}

@Preview(name = "Wallet Info Preview Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WalletInfoPreviewDark() {
    KaroTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            WalletInfo(balance = 300000.00)
        }
    }
}

@Preview(name = "Payment Field Preview Light", showBackground = true)
@Composable
fun FeePaymentFieldPreviewLight() {
    KaroTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            FeePaymentField(label = "Payment Description")
        }
    }
}

@Preview(name = "Payment Field Preview Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FeePaymentFieldPreviewDark() {
    KaroTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            FeePaymentField(label = "Payment Description")
        }
    }
}

@Preview(name = "Fee Payment Light", showBackground = true)
@Composable
fun FeePaymentPreview() {
    KaroTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            FeePaymentForm()
        }
    }
}

@Preview(name = "Fee Payment Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FeePaymentPreviewDark() {
    KaroTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            FeePaymentForm()
        }
    }
}