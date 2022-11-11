package com.example.karo


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karo.ui.theme.KaroTheme

@Composable
fun AddStudent(
    value: String,
    onValueChange:(String) -> Unit,
    label: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    showError: Boolean = false,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {onValueChange(it)} ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            label = {Text(label)},
            isError = showError,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,


        )
    }


}

@Composable
fun ShowForm(){
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var admin_no by rememberSaveable{ mutableStateOf("")}
    val fname by rememberSaveable{ mutableStateOf("")}
    var lname by rememberSaveable{ mutableStateOf("")}
    var dob by rememberSaveable{ mutableStateOf("")}
    var address by rememberSaveable{ mutableStateOf("")}
    var year by rememberSaveable{ mutableStateOf("")}
    var gender by rememberSaveable{ mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Add Student",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(vertical = 20.dp),
            color = Color.Blue
        )
        AddStudent(
            value = admin_no,
            onValueChange = {admin_no = it},
            label = "Admission Number",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down)}
            )
        )
        AddStudent(
            value = fname,
            onValueChange = {admin_no = it},
            label = "First Name",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down)}
            )
        )
        AddStudent(
            value = lname,
            onValueChange = {admin_no = it},
            label = "Last Name",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down)}
                )
        )
        AddStudent(
            value = dob,
            onValueChange = {admin_no = it},
            label = "Date of birth",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down)}
            )
        )
        AddStudent(
            value = address,
            onValueChange = {admin_no = it},
            label = "Address",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down)}
            )
        )
        AddStudent(
            value = year,
            onValueChange = {admin_no = it},
            label = "Year",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down)}
            )
        )
        AddStudent(
            value = gender,
            onValueChange = {admin_no = it},
            label = "Gender",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down)}
            )
        )

        Button(
            onClick = {},
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(0.7f),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White )
        ){
            Text(text = "Register", fontSize = 20.sp)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KaroTheme {
        ShowForm()
    }
}