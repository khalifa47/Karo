package com.example.karo.pages.feeplans

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.karo.models.FeePlan
import com.example.karo.pages.feeplans.components.UpsertPlanModal
import com.example.karo.ui.theme.KaroTheme
import com.example.karo.utils.Helpers
import kotlinx.coroutines.launch

@Composable
fun FeePlansScreen(studentId: String?, viewModel: FeePlansViewModel = hiltViewModel()) {
    val drawerState = rememberDrawerState(viewModel.drawerValue)
    val scope = rememberCoroutineScope()

    var planToEdit by remember { mutableStateOf(FeePlan()) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                { scope.launch { drawerState.open() } },
                backgroundColor = MaterialTheme.colors.primary
            ) { Icon(Icons.Default.Add, "Add a plan") }
        }
    ) { paddingValues ->
        if (studentId !== null) {
            viewModel.getFeePlans(studentId)

            FeePlans({ plans ->
                ModalDrawer({
                    UpsertPlanModal(onSave = { plan ->
                        viewModel.createFeePlan(
                            studentId,
                            plan
                        )
                    }, plan = planToEdit)
                }, drawerState = drawerState) {
                    if (plans.isEmpty()) {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            Alignment.Center
                        ) { Text("No fee plan(s) available.") }
                    } else {
                        Box(Modifier.padding(vertical = 4.dp)) {
                            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                                items(plans) { plan ->
                                    ListItem(plan) {
                                        planToEdit = it
                                        scope.launch { drawerState.open() }
                                    }
                                }
                            }
                        }
                    }
                }
            })
        } else {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text("Error finding student", color = MaterialTheme.colors.error)
            }
        }
    }
}

@Composable
fun ListItem(plan: FeePlan, onEdit: (FeePlan) -> Unit) {
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
            Row(Modifier.height(IntrinsicSize.Min)) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Year ${plan.year}", fontWeight = FontWeight.SemiBold)
                        Text("-", fontWeight = FontWeight.SemiBold)
                        Text("Semester ${plan.semester}", fontWeight = FontWeight.SemiBold)
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Divider(
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(1.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Column {
                        Text("Amount to pay per ${plan.frequency} is:")
                        Text(
                            Helpers.currencyFormat(plan.amount) ?: "N/A",
                            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.ExtraBold)
                        )
                    }
                }

                Divider(
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 30.dp, end = 10.dp)
                        .width(1.dp)
                )

                IconButton(onClick = { onEdit(plan) }) {
                    Icon(Icons.Outlined.Edit, "Edit plan")
                }
            }
        }
    }
}

@Composable
fun RecyclerView(plans: List<String> = List(100) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(plans) { ListItem(FeePlan("1", "Jane Doe", "777777")) {} }
    }
}

@Preview(showBackground = true)
@Composable
fun ListItemPreview() {
    KaroTheme {
        RecyclerView()
    }
}