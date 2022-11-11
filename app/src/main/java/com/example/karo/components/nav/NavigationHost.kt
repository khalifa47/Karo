package com.example.karo.components.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.karo.Routes
import com.example.karo.SplashScreen
import com.example.karo.components.MainViewModel
import com.example.karo.pages.*

@Composable
fun NavigationHost(navController: NavController, viewModel: MainViewModel) {
    NavHost(
        navController as NavHostController,
        startDestination = Routes.Home.name
    ) {
        composable(Routes.Home.name) {
            HomePage(
                { route -> navController.navigate(route) },
                viewModel = viewModel
            )
        }
        composable(Routes.ManageFees.name) { ManageFeePlanPage(viewModel = viewModel) }
        composable(Routes.FeePayment.name) { FeePaymentPage(viewModel = viewModel) }
        composable(Routes.Transactions.name) { TransactionsPage(viewModel = viewModel) }
        composable(Routes.Students.name) { StudentsPage(viewModel = viewModel) }
        composable(Routes.Profile.name) { ProfilePage(viewModel = viewModel) }
        composable(Routes.Settings.name) { SettingsPage(viewModel = viewModel) }
    }
}