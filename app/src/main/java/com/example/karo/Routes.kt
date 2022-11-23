package com.example.karo

sealed class Routes(val name: String) {
    object Home : Routes("home")
    object Profile : Routes("profile")
    object Settings : Routes("settings")
    object FeePlans : Routes("fee-plans/{id}")
    object ManageFees : Routes("manage-fees")
    object FeePayment : Routes("fee-payments")
    object Transactions : Routes("transactions")
    object Students : Routes("students")
}