package com.example.karo.pages.feepayment.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.karo.R
import com.example.karo.components.CustomOutlinedTextField
import com.example.karo.models.Wallet

@Composable
fun TopUpModal(onTopUp: (wallet: Wallet) -> Unit, wallet: Wallet) {
    val focusManager = LocalFocusManager.current

    var amount by rememberSaveable { mutableStateOf(wallet.amount.toString()) }
    val isValidAmount by remember { derivedStateOf { amount.isNotBlank() } }
    val invalidAmountMsg = "Amount is required."

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(20.dp)
    ) {
        Column {
            CustomOutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = "Amount",
                showError = !isValidAmount,
                errorMessage = invalidAmountMsg,
                leadingIconImageVector = ImageVector.vectorResource(id = R.drawable.ic_money),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            Button(
                onClick = { onTopUp(Wallet(wallet.id, amount)) },
                enabled = isValidAmount,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(.9f)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(),
            ) {
                Text("Top Up Wallet", fontWeight = FontWeight.Bold)
            }
        }
    }
}