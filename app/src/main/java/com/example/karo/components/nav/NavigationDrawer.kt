package com.example.karo.components.nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karo.R

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "Profile image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )
            Text(text = "Karo Admin", fontSize = 16.sp)
            Text(text = "@karoline", fontSize = 12.sp)

            Spacer(modifier = Modifier.height(7.dp))

            Row {
                Row {
                    Text("1707 ", fontWeight = FontWeight.Bold)
                    Text("Students")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Row {
                    Text("307 ", fontWeight = FontWeight.Bold)
                    Text("Transactions")
                }
            }

            Spacer(modifier = Modifier.height(7.dp))

            Divider(thickness = 0.5.dp)
        }
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(imageVector = item.icon, contentDescription = item.contentDescription)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.title, style = itemTextStyle, modifier = Modifier.weight(1f))
            }

            if(item.drawer) {
                Divider(thickness = 0.5.dp, color = MaterialTheme.colors.onBackground, modifier = Modifier.padding(vertical = 20.dp))
            }
        }
    }
}

@Preview
@Composable
fun DrawerHeaderPreview() {
    DrawerHeader()
}