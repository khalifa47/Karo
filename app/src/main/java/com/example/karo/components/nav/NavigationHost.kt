package com.example.karo.components.nav

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.karo.pages.profile.ProfileScreen
import com.example.karo.pages.students.StudentsScreen
import com.example.karo.pages.transactions.TransactionsPage

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationHost(navController: NavController, viewModel: MainViewModel) {
    NavHost(
        navController as NavHostController,
        startDestination = Routes.Home.name
    ) {
        composable(Routes.Home.name) {
            HomePage({ route -> navController.navigate(route) }, viewModel)
        }
        composable(Routes.FeePlans.name) { FeePlansScreen(it.arguments?.getString("id")) }
        composable(Routes.FeePayment.name) { FeePaymentPage({ route -> navController.navigate(route) }) }
        composable(Routes.Transactions.name) { TransactionsPage() }
        composable(Routes.Students.name) { StudentsScreen { route -> navController.navigate(route) } }
        composable(Routes.Profile.name) { ProfileScreen() }
        composable(Routes.Settings.name) { SettingsPage(viewModel = viewModel) }
    }
}