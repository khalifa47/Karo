package com.example.karo.pages.profile

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.karo.components.ProgressBar
import com.example.karo.utils.Response.*
import com.google.firebase.auth.FirebaseUser

@Composable
fun Profile(
    viewModel: ProfileViewModel = hiltViewModel(),
    userContent: @Composable (user: FirebaseUser) -> Unit
) {
    when (val userResponse = viewModel.userResponse) {
        is Loading -> ProgressBar()
        is Success -> userContent(userResponse.data)
        is Failure -> print(userResponse.e)
    }
}