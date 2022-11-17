package com.example.karo.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.karo.Routes
import com.example.karo.components.MainViewModel

@Composable
fun StudentsPage(viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Routes.Home)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        item {
            androidx.compose.material.Text(
                text = "Student Details",
                style = androidx.compose.material.MaterialTheme.typography.h5,
                modifier = Modifier.padding(vertical = 20.dp),
                color = Color.Blue
            )
        }
        item {
            Card(
                elevation = 4.dp,
                backgroundColor = Color.Blue
            ) {
                Column(
                    modifier = Modifier.padding(30.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        androidx.compose.material.Text(
                            "Add Student",
                            fontWeight = FontWeight.W500,
                            color = Color.White
                        )

                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))

                        Icon(
                            Icons.Outlined.AddCircle,
                            contentDescription = "",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    }
                }
            }
        }
        item {
            Card(elevation = 4.dp,) {
                Column(modifier = Modifier.padding(30.dp)) {
                    Row {
                        androidx.compose.material.Text(
                            "View/Edit      ",
                            fontWeight = FontWeight.W500,
                            color = Color.Black
                        )


                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))

                        Icon(
                            Icons.Outlined.Edit,
                            contentDescription = "",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    }
                    androidx.compose.material.Text(
                        "Student Details",
                        fontWeight = FontWeight.W500,
                        color = Color.Black
                    )

                }

            }
        }
    }
}