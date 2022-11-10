package com.example.karo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.karo.R
import com.example.karo.components.nav.DrawerBody
import com.example.karo.components.nav.DrawerHeader
import com.example.karo.components.nav.MenuItem
import com.example.karo.components.nav.NavigationHost
import kotlinx.coroutines.launch

@Composable
fun AppScaffold() {
    val scope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "home",
                        title = "Home",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = "manage_fees",
                        title = "Manage Fees",
                        contentDescription = "Manage fees",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_money)
                    ),
                    MenuItem(
                        id = "fee_payment",
                        title = "Fee Payment",
                        contentDescription = "Pay fees",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_money)
                    ),
                    MenuItem(
                        id = "transactions",
                        title = "Transactions",
                        contentDescription = "View transactions",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_sync)
                    ),
                    MenuItem(
                        id = "students",
                        title = "Students",
                        contentDescription = "Go to students screen",
                        icon = Icons.Default.Settings,
                        drawer = true
                    ),
                    MenuItem(
                        id = "profile",
                        title = "Profile",
                        contentDescription = "View profile",
                        icon = Icons.Default.Person
                    ),
                    MenuItem(
                        id = "share",
                        title = "Share",
                        contentDescription = "Share",
                        icon = Icons.Default.Share,
                        drawer = true
                    ),
                    MenuItem(
                        id = "settings",
                        title = "Settings",
                        contentDescription = "View settings",
                        icon = Icons.Default.Settings
                    ),
                    MenuItem(
                        id = "logout",
                        title = "Sign Out",
                        contentDescription = "Sign Out",
                        icon = Icons.Default.ExitToApp
                    ),
                ),
                onItemClick = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate(it.id) {
                        launchSingleTop = true
                    }

                    println("Clicked on ${it.title}")
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            NavigationHost(navController = navController, viewModel = viewModel)
        }
    }
}