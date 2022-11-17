package com.example.karo.pages.feeplans

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.karo.models.FeePlan
import com.example.karo.pages.feeplans.components.CreatePlanModal
import com.example.karo.ui.theme.KaroTheme
import kotlinx.coroutines.launch

@Composable
fun FeePlansScreen(studentId: String?, viewModel: FeePlansViewModel = hiltViewModel()) {
    val drawerState = rememberDrawerState(viewModel.drawerValue)
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                { scope.launch { drawerState.open() } },
                backgroundColor = MaterialTheme.colors.primary
            ) { Icon(Icons.Default.Add, "Add a plan") }
        }
    ) {
        println(studentId)

        if (studentId !== null) {
            viewModel.getFeePlans(studentId)

            FeePlans({ plans ->
                if (plans.isEmpty()) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        Text("No fee plan(s) available.")
                    }
                } else {
                    ModalDrawer({ CreatePlanModal() }, drawerState = drawerState) {
                        Box(Modifier.padding(vertical = 4.dp)) {
                            LazyColumn(modifier = Modifier.padding(it)) {
                                items(plans) { plan -> ListItem(plan) }
                            }
                        }
                    }
                }
            })
        } else {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Text("Error finding student", color = MaterialTheme.colors.error)
            }
        }
    }
}

@Composable
fun ListItem(plan: FeePlan) {
    Surface(
        color = MaterialTheme.colors.primary, modifier = Modifier
            .padding(4.dp, 2.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row {
                Column(modifier = Modifier.weight(1f)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        Text("Year ${plan.year}")
                        Text("Semester ${plan.semester}")
                    }
                    Text(
                        plan.amount ?: "N/A",
                        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold)
                    )
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Outlined.Edit, "Edit plan")
                }
            }
        }
    }
}

@Composable
fun RecyclerView(plans: List<String> = List(100) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(plans) { ListItem(FeePlan("1", "Jane Doe", "777777")) }
    }
}

@Preview(showBackground = true)
@Composable
fun ListItemPreview() {
    KaroTheme {
        RecyclerView()
    }
}