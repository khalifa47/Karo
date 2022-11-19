package com.example.karo.pages.transactions

import androidx.compose.material.DrawerValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.karo.models.Transaction
import com.example.karo.utils.Response
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

typealias Transactions = List<Transaction>
typealias TransactionsResponse = Response<Transactions>
typealias AddTransactionResponse = Response<Boolean>

class TransactionsViewModel : ViewModel() {
    var transactionsResponse by mutableStateOf<TransactionsResponse>(Response.Loading)
        private set
    var addTransactionResponse by mutableStateOf<AddTransactionResponse>(Response.Success(false))
        private set
    var drawerValue by mutableStateOf(DrawerValue.Closed)
        private set

    fun getTransactions(studentId: String) = viewModelScope.launch {
        callbackFlow {
            val snapshotListener =
                Firebase.firestore.collection("students/${studentId}/transactions")
                    .addSnapshotListener { snapshot, e ->
                        val studentsResponse = if (snapshot != null) {
                            val students = snapshot.toObjects(Transaction::class.java)

                            Response.Success(students)
                        } else {
                            Response.Failure(e)
                        }

                        trySend(studentsResponse)
                    }
            awaitClose {
                snapshotListener.remove()
            }
        }.collect { response -> transactionsResponse = response }
    }

    fun createTransaction(studentId: String, transaction: Transaction) = viewModelScope.launch {
        addTransactionResponse = Response.Loading
        addTransactionResponse = try {
            transaction.id = Firebase.firestore.collection("students/${studentId}/transactions").document().id

            Firebase.firestore.collection("students/${studentId}/transactions").document(transaction.id)
                .set(transaction)
                .await()

            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}