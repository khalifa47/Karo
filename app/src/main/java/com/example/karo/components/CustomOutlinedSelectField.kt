package com.example.karo.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomOutlinedSelectField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    options: List<String>,
    showError: Boolean = false,
    errorMessage: String = "",
) {
    var selectedItem by remember { mutableStateOf(value) }

    if (value.isNotEmpty()) selectedItem = value

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        CustomOutlinedTextField(
            value = selectedItem,
            onValueChange = { },
            readOnly = true,
            label = label,
            showError = showError,
            errorMessage = errorMessage,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    "Select option",
                )
            },
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach {
                DropdownMenuItem({
                    onValueChange(it)

                    selectedItem = it

                    expanded = false
                }) { Text(it) }
            }
        }
    }
}