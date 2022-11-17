package com.example.karo.pages.feeplans.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.karo.pages.SelectInput

@Composable
fun CreatePlanModal() {
    var year by remember { mutableStateOf("") }
    var semester by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var frequency by remember { mutableStateOf("") }
    
    Box(modifier = Modifier
        .padding(20.dp)
        .fillMaxHeight(), contentAlignment = Alignment.Center) {
        Column {
            Card(
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("123456: John Doe", color = MaterialTheme.colors.onPrimary)
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            SelectInput(
                value = year,
                "Year",
                listOf("1", "2", "3", "4"),
                onValueChange = { year = it }
            )
        }
    }
}