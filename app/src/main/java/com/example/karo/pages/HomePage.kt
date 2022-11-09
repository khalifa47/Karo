package com.example.karo.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.karo.R
import com.example.karo.Routes
import com.example.karo.components.MainViewModel

data class CardAction(
    val title: String,
    val image: Int,
    val backgroundColorResourceId: Int = R.color.white
)

@Composable
fun HomePage(viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Routes.Home)

    Box(modifier = Modifier.padding(20.dp)) {
        Column {
            Text("Good Morning", fontSize = 24.sp)
            Text("Ghost Rider", color = Color.Blue)

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
                backgroundColor = colorResource(R.color.blue),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Text("Total Fees Paid")
                    Text("Fee Balance")
                    Text("Last payment was made on:")
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    listOf(
                        CardAction("Transactions", R.drawable.transactions, R.color.blue),
                        CardAction("Fee Plans", R.drawable.fee_plans),
                        CardAction("Payments", R.drawable.payment_info),
                        CardAction("Students", R.drawable.students, R.color.blue),
                    )
                ) { item ->
                    Card(
                        elevation = 4.dp,
                        modifier = Modifier.padding(4.dp),
                        shape = RoundedCornerShape(10.dp),
                        backgroundColor = colorResource(item.backgroundColorResourceId),
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 60.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(item.image),
                                contentDescription = "Profile image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape)
                            )
                            Text(item.title)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePagePreview() {
    HomePage(viewModel = viewModel())
}