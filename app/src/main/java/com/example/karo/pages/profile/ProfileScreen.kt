package com.example.karo.pages.profile

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.karo.R
import com.example.karo.components.CustomOutlinedTextField
import com.example.karo.ui.theme.KaroTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileScreen() {
    Scaffold { padding ->
        Profile(
            userContent = { user ->
                var email by remember { mutableStateOf(user.email.toString()) }
                var name by remember { mutableStateOf(user.displayName.toString()) }

                val isValidEmail by remember {
                    derivedStateOf { Patterns.EMAIL_ADDRESS.matcher(email).matches() }
                }

                println("User   -----------------------------------------${name}")

                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(padding)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.profile),
                            contentDescription = "Profile image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(200.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        CustomOutlinedTextField(
                            label = "Name",
                            value = name,
                            onValueChange = { name = it },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                        )
                        CustomOutlinedTextField(
                            label = "Email",
                            value = email,
                            onValueChange = { email = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Done
                            ),
                        )

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
        )
    }
}