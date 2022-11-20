package com.example.karo.components.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.karo.Routes
import com.example.karo.components.MainViewModel
import com.example.karo.pages.*
import com.example.karo.pages.feepayment.FeePaymentPage
import com.example.karo.pages.feeplans.FeePlansScreen
import com.example.karo.pages.students.StudentsScreen
import com.example.karo.pages.transactions.TransactionsPage

@Composable
fun NavigationHost(navController: NavController, viewModel: MainViewModel) {
    NavHost(
        navController as NavHostController,
        startDestination = Routes.Students.name
    ) {
        composable(Routes.Home.name) {
            HomePage({ route -> navController.navigate(route) }, viewModel)
        }
        composable(Routes.FeePlans.name) { FeePlansScreen(it.arguments?.getString("id")) }
        composable(Routes.ManageFees.name) {
            ManageFeePlanPage(
                it.arguments?.getString("id") ?: "1",
                viewModel = viewModel
            )
        }
        composable(Routes.FeePayment.name) { FeePaymentPage(it.arguments?.getString("id")) }
        composable(Routes.Transactions.name) { TransactionsPage(it.arguments?.getString("id")) }
        composable(Routes.Students.name) { StudentsScreen({ route -> navController.navigate(route) }) }
        composable(Routes.Profile.name) { ProfilePage(viewModel = viewModel) }
        composable(Routes.Settings.name) { SettingsPage(viewModel = viewModel) }
    }
}