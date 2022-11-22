package com.example.karo.pages.profile

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.karo.R
import com.example.karo.components.CustomOutlinedTextField
import com.example.karo.ui.theme.KaroTheme

@Composable
fun ProfileScreen() {
    Scaffold { padding ->
        Profile(
            userContent = {
                println("User   -----------------------------------------${it}")

                Box(Modifier.padding(padding)) {

                }
            }
        )
    }
}

@Composable
fun ProfileForm() {
    var email by remember { mutableStateOf("") }

    val isValidEmail by remember {
        derivedStateOf { Patterns.EMAIL_ADDRESS.matcher(email).matches() }
    }

    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "Profile image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomOutlinedTextField(label = "Email", value = email, onValueChange = { email = it })

            Button(
                onClick = { },
                enabled = isValidEmail,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(.9f)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(),
            ) {
                Text("Save", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileFormPreview() {
    KaroTheme {
        ProfileForm()
    }
}