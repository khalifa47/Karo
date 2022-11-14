package com.example.karo.utils

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


object Helpers {
    fun getCollectionReferenceForDoc(collectionPath:String): CollectionReference {
        val currentUser = FirebaseAuth.getInstance().currentUser

        return FirebaseFirestore.getInstance().collection(collectionPath).document(currentUser!!.uid)
            .collection(collectionPath)
    }

    fun showToast(c: Context?, message: String?) {
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show()
    }
}