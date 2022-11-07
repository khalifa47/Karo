package com.example.karo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

@Composable
fun PageTitle(title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 2.dp, end = 2.dp)
    ) {
        IconButton(modifier = Modifier.
        then(Modifier.size(24.dp)),
            onClick = { }) {
            Icon(
                Icons.Outlined.ArrowBack,
                "Back Button"
            )
        }

        Text(text = title)

        IconButton(modifier = Modifier.
        then(Modifier.size(24.dp)),
            onClick = { }) {
            Icon(
                Icons.Outlined.Settings,
                "Theme Button"
            )
        }
    }
}