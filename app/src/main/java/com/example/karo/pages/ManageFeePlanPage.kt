package com.example.karo.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.karo.Routes
import com.example.karo.components.MainViewModel
import com.example.karo.models.PaymentPlan
import com.example.karo.utils.Helpers
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference

@Composable
fun ManageFeePlanPage(id: String, viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Routes.Home)

    val focusManager = LocalFocusManager.current

    var isLoading by remember { mutableStateOf(false) }
    var isUpdate by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val docId = ""

    var year by remember { mutableStateOf("") }
    var semester by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var frequency by remember { mutableStateOf("") }

    if (docId.isNotEmpty()) isUpdate = true

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

            SelectInput(
                value = semester,
                "Semester",
                listOf("1", "2"),
                onValueChange = { semester = it }
            )

            TextInput(
                label = "Amount",
                value = amount,
                onValueChange = { amount = it },
                keyboardType = KeyboardType.Number,
                modifier = Modifier.fillMaxWidth(),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(
                        FocusDirection.Down
                    )
                }),
                trailingIcon = {
                    if (amount.isNotBlank()) {
                        IconButton(onClick = { amount = "" }) {
                            Icon(Icons.Filled.Clear, contentDescription = "Clear year")
                        }
                    }
                }
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

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(30.dp))
            } else {
                Button(
                    onClick = {
                        isLoading = true

                        val docRef: DocumentReference = if (isUpdate) {
                            Helpers.getCollectionReferenceForDoc("students").document(docId)
                        } else {
                            Helpers.getCollectionReferenceForDoc("students").document()
                        }

                        val plan = PaymentPlan(year, semester, Timestamp.now())

                        docRef.set(plan).addOnCompleteListener {
                            isLoading = false

                            if (it.isSuccessful) {
                                val msgStatus = if (isUpdate) "created" else "updated"
                                Helpers.showToast(context, "Note $msgStatus successfully!")
                            } else {
                                val msgStatus = if (isUpdate) "create" else "update"
                                Helpers.showToast(context, "Failed to $msgStatus note!")
                            }
                        }
                    },
//                    enabled = isValidEmail && isValidPassword,
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
}

@Composable
fun SelectInput(
    value: String,
    label: String,
    options: List<String>,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var fieldSize by remember { mutableStateOf(Size.Zero) }
    var selectedText by remember { mutableStateOf(value) }

    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    TextInput(
        value = selectedText,
        label = label,
        onValueChange = {
            selectedText = it

            onValueChange(it)
        },
        trailingIcon = {
            Icon(icon, "contentDescription", Modifier.clickable { expanded = !expanded })
        },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { expanded = it.isFocused }
            .onGloballyPositioned { coordinates ->
                // This value is used to assign to the DropDown the same width
                fieldSize = coordinates.size.toSize()
            },
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .width(with(LocalDensity.current) { fieldSize.width.toDp() })
    ) {
        options.forEach { label ->
            DropdownMenuItem(onClick = {
                selectedText = label
                onValueChange(label)
                expanded = false
            }) { Text(label) }
        }
    }
}

@Composable
fun TextInput(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value, onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        trailingIcon = trailingIcon,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        keyboardActions = keyboardActions,
        modifier = modifier
    )
}