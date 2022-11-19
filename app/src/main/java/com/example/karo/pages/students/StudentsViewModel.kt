package com.example.karo.pages.students

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.karo.models.Student
import com.example.karo.utils.Response
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

typealias Students = List<Student>
typealias StudentsResponse = Response<Students>

class StudentsViewModel : ViewModel() {
    var studentsResponse by mutableStateOf<StudentsResponse>(Response.Loading)
        private set
    var openDialog by mutableStateOf(false)
        private set

    init {
        getStudents()
    }

    private fun getStudents() = viewModelScope.launch {
        callbackFlow {
            val snapshotListener =
                Firebase.firestore.collection("students").addSnapshotListener { snapshot, e ->
                    val studentsResponse = if (snapshot != null) {
                        val students = snapshot.toObjects(Student::class.java)

                        Response.Success(students)
                    } else {
                        Response.Failure(e)
                    }

                    trySend(studentsResponse)
                }
            awaitClose {
                snapshotListener.remove()
            }
        }.collect { response -> studentsResponse = response }
    }

    fun openDialog() {
        openDialog = true
    }
}