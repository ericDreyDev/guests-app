package com.example.guest.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guest.ui.screens.GuestListScreen
import com.example.guest.ui.screens.LoginScreen
import com.example.guest.ui.screens.ProfileScreen

@Composable
fun Routes() {
    val navController = rememberNavController()
    NavHost(
        startDestination = "LoginScreen",
        navController = navController
    ) {
        composable("GuestListScreen") {
            GuestListScreen(navController)
        }
        composable("ProfileScreen") {
            ProfileScreen(navController)
        }
        composable("LoginScreen") {
            LoginScreen(navController)
        }
    }
}