package com.example.karo

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun doNothing(){
    println("Hello")
}

@Composable
fun ConfirmButton(label: String, action: () -> Unit) {
    Spacer(Modifier.height(6.dp))
    Button(onClick = action ) {
        Icon(
            Icons.Outlined.Check,
            contentDescription = "Confirm Button"
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(label)
    }
}