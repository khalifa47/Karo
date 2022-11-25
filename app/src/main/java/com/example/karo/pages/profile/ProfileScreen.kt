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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.karo.R
import com.example.karo.components.CustomOutlinedTextField
import com.example.karo.utils.Helpers

@Composable
fun ProfileScreen(onLogout: () -> Unit, viewModel: ProfileViewModel = hiltViewModel()) {
    val context = LocalContext.current

    Scaffold { padding ->
        Profile(
            userContent = { user ->
                var name by remember { mutableStateOf(user.displayName.toString()) }
                var email by remember { mutableStateOf(user.email.toString()) }
                var password by remember { mutableStateOf("") }

                val isValidEmail by remember {
                    derivedStateOf { Patterns.EMAIL_ADDRESS.matcher(email).matches() }
                }

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
                                imeAction = ImeAction.Next
                            ),
                        )
                        CustomOutlinedTextField(
                            label = "New Password",
                            value = password,
                            isPasswordField = true,
                            onValueChange = { password = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                        )

                        Button(
                            onClick = {
                                viewModel.updateUser(name, email, password)

                                Helpers.showToast(context, "Profile updated successfully!")
                            },
                            enabled = isValidEmail,
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth(.9f)
                                .align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(),
                        ) { Text("Save", fontWeight = FontWeight.Bold) }

                        Button(
                            onClick = {
                                viewModel.deleteAccount()

                                onLogout()
                            },
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth(.9f)
                                .align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults.outlinedButtonColors(
                                MaterialTheme.colors.error,
                                MaterialTheme.colors.onError
                            ),
                        ) { Text("Delete Account", fontWeight = FontWeight.Bold) }
                    }
                }
            }
        )
    }
}