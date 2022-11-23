package com.example.karo.pages.feepayment

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.karo.*
import com.example.karo.pages.feepayment.components.TopUpModal
import com.example.karo.ui.theme.KaroTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeePaymentPage(viewModel: FeePaymentViewModel = hiltViewModel()) {
    val bottomDrawerState = rememberBottomDrawerState(viewModel.bottomDrawerValue)
    val scope = rememberCoroutineScope()

    viewModel.getWallet()

    Wallet({ wallet ->
        BottomDrawer(
            drawerState = bottomDrawerState,
            drawerContent = {
                TopUpModal(onTopUp = { wallet ->
                    viewModel.topUpWallet(wallet)
                    scope.launch { bottomDrawerState.close() }
                }, wallet = wallet)
            }
        ) {
            if (wallet.id == null){
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(all = 8.dp),
                    Alignment.Center
                ) { Text("No wallet available.") }
            } else {
                Column(Modifier.fillMaxHeight()) {
                    if (wallet.amount != null) {
                        wallet.amount?.let { WalletInfo(balance = it.toDouble(), scope = scope, bottomDrawerState = bottomDrawerState) }
                    } else {
                        WalletInfo(balance = 0.00, scope = scope, bottomDrawerState = bottomDrawerState)
                    }
                    FeePaymentForm()
                }

            }
        }
    })
}


@Composable
fun FeePaymentForm() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        FeePaymentField(label = "Payment Description")
        FeePaymentField(label = "Amount")
        ConfirmButton(label = "Confirm Payment", action = { doNothing() })
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WalletInfo(balance: Double, scope: CoroutineScope, bottomDrawerState: BottomDrawerState) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 30.dp, start = 20.dp, end = 20.dp)
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Wallet Balance", fontSize = 20.sp)
            Text(text = "KES ${"%,.2f".format(balance)}", fontSize = 30.sp, color = MaterialTheme.colors.primary)
        }
        Button({ scope.launch { bottomDrawerState.open() }}) {
            Text("Top Up")
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

//@Preview(name = "Wallet Info Preview Light", showBackground = true)
//@Composable
//fun WalletInfoPreviewLight() {
//    KaroTheme {
//        Surface(
//            color = MaterialTheme.colors.background
//        ) {
//            WalletInfo(balance = 300000.00)
//        }
//    }
//}
//
//@Preview(name = "Wallet Info Preview Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun WalletInfoPreviewDark() {
//    KaroTheme {
//        Surface(
//            color = MaterialTheme.colors.background
//        ) {
//            WalletInfo(balance = 300000.00)
//        }
//    }
//}

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