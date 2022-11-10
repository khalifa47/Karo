package com.example.karo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/*@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.Settings.name
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.FeePayment.name) {
            FeePayment(onNavigateToTransactions = { navController.navigate(Routes.Transactions.name) })
        }
        composable(Routes.Transactions.name) {
            Transactions(onNavigateToPayments = { navController.navigate(Routes.FeePayment.name) })
        }
    }
}*/
