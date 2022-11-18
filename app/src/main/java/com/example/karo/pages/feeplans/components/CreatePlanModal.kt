package com.example.karo.pages.feeplans.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karo.R
import com.example.karo.components.CustomOutlinedTextField
import com.example.karo.models.FeePlan
import com.example.karo.pages.SelectInput

@Composable
fun CreatePlanModal(onCreate: (plan: FeePlan) -> Unit) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var year by rememberSaveable { mutableStateOf("") }
    var semester by rememberSaveable { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }
    var frequency by rememberSaveable { mutableStateOf("") }

    var isValidYear by rememberSaveable { mutableStateOf(true) }
    var isValidSemester by rememberSaveable { mutableStateOf(true) }
    var isValidAmount by rememberSaveable { mutableStateOf(true) }
    var isValidFrequency by rememberSaveable { mutableStateOf(true) }

    val invalidYearMsg = "Year is required."
    val invalidSemesterMsg = "Semester is required."
    val invalidAmountMsg = "Amount is required."
    val invalidFrequencyMsg = "Frequency is required."

    fun validate(year: String, semester: String, amount: String, frequency: String): Boolean {
        isValidYear = year.isNotBlank()
        isValidSemester = semester.isNotBlank()
        isValidAmount = semester.isNotBlank()
        isValidFrequency = frequency.isNotBlank()

        return isValidYear && isValidSemester && isValidAmount && isValidFrequency
    }

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

            SelectInput(
                value = year,
                "Year",
                listOf("1", "2", "3", "4"),
                onValueChange = { year = it }
            )

            SelectInput(
                value = semester,
                "Semester",
                listOf("1", "2"),
                onValueChange = { semester = it }
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

            SelectInput(
                value = frequency,
                "Should Pay Every",
                listOf("Week", "Month", "Quarter", "Semester", "Year"),
                onValueChange = { frequency = it }
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

            TextButton(
                { onCreate(FeePlan(null, year, semester, amount, frequency)) },
//              enabled = isValidEmail && isValidPassword,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(),
            ) {
                Text("Update", fontWeight = FontWeight.Bold)
            }
        }
    }
}