package com.example.karo.pages.students

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.karo.ui.theme.KaroTheme


@Composable
fun MyScaffold(){
    val scaffoldState : ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {

    }
}

@Composable
fun EditDetails() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        item {
            Text(
                text = "View/Edit Student Details",
                style = MaterialTheme.typography.h5,
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
                        Icon(
                            Icons.Outlined.Search,
                            contentDescription = "",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))

                        Text(
                            "10042 John Trent",
                            fontWeight = FontWeight.W500,
                            color = Color.White
                        )
                    }
                    Row {
                        Icon(
                            Icons.Outlined.Search,
                            contentDescription = "",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Text(
                            "10041 John Doe",
                            fontWeight = FontWeight.W500,
                            color = Color.White
                        )
                    }

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowEditDetails() {
    KaroTheme {
        EditDetails()
    }
}