package com.example.karo.pages.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.karo.utils.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

typealias UserResponse = Response<FirebaseUser>
typealias DeleteUserResponse = Response<Boolean>

class ProfileViewModel : ViewModel() {
    var userResponse by mutableStateOf<UserResponse>(Response.Loading)
        private set
    var deleteUserResponse by mutableStateOf<DeleteUserResponse>(Response.Success(false))
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
            awaitClose {
                FirebaseAuth.getInstance().removeAuthStateListener {}
            }
        }.collect { response -> userResponse = response }
    }

    fun updateUser(name: String, email: String, password: String) {
        val user = Firebase.auth.currentUser

        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }

        userResponse = try {
            user!!.updateProfile(profileUpdates).addOnCompleteListener {
                if (it.isCanceled) Response.Failure(it.exception)
            }

            user.updateEmail(email).addOnCompleteListener {
                if (it.isCanceled) Response.Failure(it.exception)
            }

            user.updatePassword(password).addOnCompleteListener {
                if (it.isCanceled) Response.Failure(it.exception)
            }

            Response.Success(FirebaseAuth.getInstance().currentUser!!)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    fun deleteAccount() {
        val user = Firebase.auth.currentUser!!

        deleteUserResponse = try {
            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Response.Success(true)
                    }
                }

            Response.Failure(Exception("Unable to delete user!"))
        }catch(e:Exception) {
            Response.Failure(e)
        }
    }
}