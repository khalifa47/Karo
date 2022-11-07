package com.example.karo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object Routes {
    const val FeePayment = "fee_payment"
    const val Transactions = "transactions"
}

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.Transactions
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.FeePayment){
            FeePayment(onNavigateToTransactions = { navController.navigate(Routes.Transactions) })
        }
        composable(Routes.Transactions){
            Transactions(onNavigateToPayments = { navController.navigate(Routes.FeePayment) })
        }
    }
}