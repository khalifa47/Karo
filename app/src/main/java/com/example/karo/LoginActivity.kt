package com.example.karo

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karo.ui.theme.KaroTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun login() {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setContent {
            KaroTheme {
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                val focusManager = LocalFocusManager.current

                val isValidEmail by derivedStateOf {
                    Patterns.EMAIL_ADDRESS.matcher(email).matches()
                }
                val isValidPassword by derivedStateOf {
                    password.length > 7
                }

                var isPasswordVisible by remember {
                    mutableStateOf(false)
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        "Welcome Back...",
                        fontFamily = FontFamily.Companion.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 32.sp,
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    OutlinedTextField(
                        value = email, onValueChange = { email = it },
                        label = { Text("Email address") },
                        placeholder = { Text("marie.ndathi@khalifa.nabz") },
                        singleLine = true,
                        isError = !isValidEmail,
                        trailingIcon = {
                            if (email.isNotBlank()) {
                                IconButton(onClick = { email = "" }) {
                                    Icon(Icons.Filled.Clear, contentDescription = "Clear email")
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(
                                FocusDirection.Down
                            )
                        })
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = password, onValueChange = { password = it },
                        label = { Text("Password") },
                        placeholder = { Text("passwwaaddd") },
                        singleLine = true,
                        isError = !isValidPassword,
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            if (email.isNotBlank()) {
                                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                    Icon(
                                        if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = "Toggle password visibility"
                                    )
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                        })
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton({ login() }) {
                            Text(
                                "Forgot Password?",
                                color = MaterialTheme.colors.primary,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                        }
                    }

                    Button(
                        onClick = {},
                        enabled = isValidEmail && isValidPassword,
                        modifier = Modifier
                            .padding(16.dp),
                        colors = ButtonDefaults.buttonColors(),
                    ) {
                        Text("Sign In", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}