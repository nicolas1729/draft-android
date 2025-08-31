package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.feature.authentication.presentation.ui.LoginScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestinations.LOGIN
    ) {
        composable(NavigationDestinations.LOGIN) {
            LoginScreen(
                onNavigateToProfil = {
                    navController.navigate(NavigationDestinations.PROFILE)
                }
            )
        }


    }
}