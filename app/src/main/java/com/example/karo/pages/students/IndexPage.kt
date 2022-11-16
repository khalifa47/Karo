package com.example.karo.pages.students

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karo.R
import com.example.karo.Routes
import com.example.karo.components.MainViewModel
import com.example.karo.ui.theme.KaroTheme

@Composable
fun StudentsIndex(viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Routes.Home)

    RecyclerView()
}

@Composable
fun ListItem(name: String, admNo: String) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 20.dp else 0.dp,
        spring(
            Spring.DampingRatioMediumBouncy,
            Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.primary, modifier = Modifier
            .padding(4.dp, 2.dp)
            .clip(
                RoundedCornerShape(10.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row {
                Column(modifier = Modifier.weight(1f)) {
                    Text(name)
                    Text(
                        admNo,
                        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold)
                    )
                }

                OutlinedButton(onClick = { expanded = !expanded }) {
                    Text("${if (expanded) "Show" else "Hide"} actions")
                }
            }

            if (expanded) {
                Column(modifier = Modifier.padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Outlined.Edit, "Edit student")
                            }
                            Text("Edit students", fontSize = 10.sp)
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    ImageVector.vectorResource(id = R.drawable.ic_money),
                                    "Manage fee plans"
                                )
                            }
                            Text("Manage fee plans", fontSize = 10.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RecyclerView(names: List<String> = List(100) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(names) { name -> ListItem("Jane Doe", name) }
    }
}

@Preview(showBackground = true)
@Composable
fun ListItemPreview() {
    KaroTheme {
        RecyclerView()
    }
}