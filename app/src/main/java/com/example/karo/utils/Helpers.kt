package com.example.karo.utils

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.util.*


object Helpers {
    fun getCollectionReferenceForDoc(collectionPath: String): CollectionReference {
        val currentUser = FirebaseAuth.getInstance().currentUser

        return FirebaseFirestore.getInstance().collection(collectionPath)
            .document(currentUser!!.uid)
            .collection(collectionPath)
    }

    fun showToast(c: Context?, message: String?) {
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show()
    }

    fun currencyFormat(number: String?): String? {
        val formatter = NumberFormat.getCurrencyInstance(Locale("en", "KE"))
        formatter.maximumFractionDigits = 2

        return formatter.format(number?.toInt())
    }
}