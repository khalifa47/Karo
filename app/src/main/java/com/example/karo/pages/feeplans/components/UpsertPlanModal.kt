package com.example.karo.pages.feeplans.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import androidx.compose.ui.unit.sp
import com.example.karo.R
import com.example.karo.components.CustomOutlinedSelectField
import com.example.karo.components.CustomOutlinedTextField
import com.example.karo.models.FeePlan

@Composable
fun UpsertPlanModal(onSave: (plan: FeePlan) -> Unit, plan: FeePlan) {
    println("PLAN: ======================================================= $plan")

    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var year by rememberSaveable { mutableStateOf("") }
    var semester by rememberSaveable { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }
    var frequency by rememberSaveable { mutableStateOf("") }

    if (plan.year?.isNotEmpty() == true) year = plan.year.toString()
    if (plan.semester?.isNotEmpty() == true) semester = plan.semester.toString()
    if (plan.amount?.isNotEmpty() == true) amount = plan.amount.toString()
    if (plan.frequency?.isNotEmpty() == true) frequency = plan.frequency.toString()

    val isValidYear by remember { derivedStateOf { year.isNotBlank() } }
    val isValidSemester by remember { derivedStateOf { semester.isNotBlank() } }
    val isValidAmount by remember { derivedStateOf { amount.isNotBlank() } }
    val isValidFrequency by remember { derivedStateOf { frequency.isNotBlank() } }

    val invalidYearMsg = "Year is required."
    val invalidSemesterMsg = "Semester is required."
    val invalidAmountMsg = "Amount is required."
    val invalidFrequencyMsg = "Frequency is required."

    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxHeight()
            .verticalScroll(scrollState), contentAlignment = Alignment.Center
    ) {
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

            CustomOutlinedSelectField(
                label = "Year",
                value = year,
                onValueChange = { year = it },
                options = listOf("1", "2", "3", "4"),
                showError = !isValidYear,
                errorMessage = invalidYearMsg,
            )

            CustomOutlinedSelectField(
                label = "Semester",
                value = semester,
                onValueChange = { semester = it },
                options = listOf("1", "2"),
                showError = !isValidSemester,
                errorMessage = invalidSemesterMsg,
            )

            CustomOutlinedSelectField(
                value = frequency,
                label = "Should Pay Every",
                onValueChange = { frequency = it },
                options = listOf("Week", "Month", "Quarter", "Semester", "Year"),
                showError = !isValidFrequency,
                errorMessage = invalidFrequencyMsg,
            )

            CustomOutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = "Amount",
                showError = !isValidAmount,
                errorMessage = invalidAmountMsg,
                leadingIconImageVector = ImageVector.vectorResource(id = R.drawable.ic_money),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            Spacer(modifier = Modifier.height(50.dp))

            Card(
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        "Summary:",
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Student should pay KSH " +
                                "${amount.ifBlank { "___" }} per " +
                                "${frequency.ifBlank { "___" }} for his year " +
                                "${year.ifBlank { "___" }} semester " +
                                "${semester.ifBlank { "___" }}.",
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = { onSave(FeePlan(null, year, semester, amount, frequency)) },
                enabled = isValidYear && isValidSemester && isValidAmount && isValidFrequency,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(.9f)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(),
            ) {
                Text("Save", fontWeight = FontWeight.Bold)
            }
        }
    }
}