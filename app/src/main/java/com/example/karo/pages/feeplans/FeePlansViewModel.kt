package com.example.karo.pages.feeplans

import androidx.compose.material.DrawerValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.karo.models.FeePlan
import com.example.karo.utils.Response
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

typealias FeePlans = List<FeePlan>
typealias FeePlansResponse = Response<FeePlans>
typealias AddFeePlanResponse = Response<Boolean>

class FeePlansViewModel : ViewModel() {
    var feePlansResponse by mutableStateOf<FeePlansResponse>(Response.Loading)
        private set
    var addFeePlanResponse by mutableStateOf<AddFeePlanResponse>(Response.Success(false))
        private set
    var drawerValue by mutableStateOf(DrawerValue.Closed)
        private set

    fun getFeePlans(studentId: String) = viewModelScope.launch {
        callbackFlow {
            val snapshotListener =
                Firebase.firestore.collection("students/${studentId}/plans")
                    .addSnapshotListener { snapshot, e ->
                        val studentsResponse = if (snapshot != null) {
                            val students = snapshot.toObjects(FeePlan::class.java)

                            Response.Success(students)
                        } else {
                            Response.Failure(e)
                        }

                        trySend(studentsResponse)
                    }
            awaitClose {
                snapshotListener.remove()
            }
        }.collect { response -> feePlansResponse = response }
    }

    fun createFeePlan(studentId: String, feePlan: FeePlan) = viewModelScope.launch {
        addFeePlanResponse = Response.Loading
        addFeePlanResponse = try {
            feePlan.id = Firebase.firestore.collection("students/${studentId}/plans").document().id

            Firebase.firestore.collection("students/${studentId}/plans").document(studentId)
                .set(feePlan)
                .await()

            Response.Failure(Exception("Student ID missing!"))
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}