package com.example.karo.pages.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.karo.utils.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

typealias UserResponse = Response<FirebaseUser>

class ProfileViewModel : ViewModel() {
    var userResponse by mutableStateOf<UserResponse>(Response.Loading)
        private set

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        callbackFlow {
            FirebaseAuth.getInstance().addAuthStateListener {
                val userResponse = if (it.currentUser != null) {
                    val user = it.currentUser!!

                    Response.Success(user)
                } else {
                    Response.Failure(Exception("Unable to retrieve user."))
                }

                trySend(userResponse)
            }
        }.collect { response -> userResponse = response }
    }
}