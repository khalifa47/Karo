package com.example.karo.utils

import android.content.Context
import android.widget.Toast

object Helpers {
    fun showToast(c: Context?, message: String?) {
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show()
    }
}