package com.example.karo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/*
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.Settings.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.FeePayment.route){
            FeePayment(onNavigateToTransactions = { navController.navigate(Routes.Transactions.route) })
        }
        composable(Routes.Transactions.route){
            Transactions(onNavigateToPayments = { navController.navigate(Routes.FeePayment.route) })
        }
    }
}*/
