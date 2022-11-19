package com.example.karo

sealed class Routes(val name: String) {
    object Home : Routes("home")
    object Profile : Routes("profile")
    object Settings : Routes("settings")
    object FeePlans : Routes("fee-plans/{id}")
    object ManageFees : Routes("manage-fees")
    object FeePayment : Routes("fee-payment/{id}")
    object Transactions : Routes("transactions/{id}")
    object Students : Routes("students")
}