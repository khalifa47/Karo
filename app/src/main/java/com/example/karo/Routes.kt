package com.example.karo

sealed class Routes(val name: String) {
    object Splash : Routes("splash")

    object Home : Routes("home")
    object Profile : Routes("profile")
    object Settings : Routes("settings")
    object ManageFees : Routes("manage_fees")
    object FeePayment : Routes("fee_payment")
    object Transactions : Routes("transactions")
    object Students : Routes("students")
}