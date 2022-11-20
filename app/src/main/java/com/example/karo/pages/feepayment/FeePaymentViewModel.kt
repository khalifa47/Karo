package com.example.karo.pages.feepayment

import androidx.compose.material.BottomDrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.karo.models.Wallet
import com.example.karo.utils.Response
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

typealias WalletResponse = Response<Wallet?>
typealias TopUpWallet = Response<Boolean>

class FeePaymentViewModel : ViewModel() {
    var walletResponse by mutableStateOf<WalletResponse>(Response.Loading)
        private set
    var topUpWalletResponse by mutableStateOf<TopUpWallet>(Response.Success(false))
        private set

    @OptIn(ExperimentalMaterialApi::class)
    var bottomDrawerValue by mutableStateOf(BottomDrawerValue.Closed)
        private set

    fun getWallet(studentId: String) = viewModelScope.launch {
        callbackFlow {
            val snapshotListener =
                Firebase.firestore.document("wallets/${studentId}")
                    .addSnapshotListener { snapshot, e ->
                        val studentsResponse = if (snapshot != null) {
                            val students = snapshot.toObject(Wallet::class.java)

                            Response.Success(students)
                        } else {
                            Response.Failure(e)
                        }

                        trySend(studentsResponse)
                    }
            awaitClose {
                snapshotListener.remove()
            }
        }.collect { response -> walletResponse = response }
    }

    fun createWallet(studentId: String, wallet: Wallet) = viewModelScope.launch {
        topUpWalletResponse = Response.Loading
        topUpWalletResponse = try {
            wallet.id = studentId

            Firebase.firestore.collection("wallets").document(wallet.id!!)
                .set(wallet)
                .await()

            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    fun topUpWallet(wallet: Wallet) = viewModelScope.launch {
        topUpWalletResponse = Response.Loading
        topUpWalletResponse = try {
            wallet.id?.let {
                val docRef = Firebase.firestore.collection("wallets").document(it)
                docRef.get().addOnSuccessListener { document ->
                    docRef.update(
                        mapOf(
                            "amount" to (document.data!!["amount"].toString().toDouble() + wallet.amount!!.toDouble()).toString()
                        )
                    )
                }.await()
                Response.Success(true)
            }

            Response.Failure(Exception("Wallet ID not set."))
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}