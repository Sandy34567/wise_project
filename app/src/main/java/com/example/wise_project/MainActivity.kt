package com.example.wise_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wise_project.ViewModel.SharedViewModel
import com.example.wise_project.screens.CategoryScreen
import com.example.wise_project.screens.DrinkDetailsScreen
import com.example.wise_project.ui.theme.Wise_projectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Wise_projectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        val sharedViewModel = hiltViewModel<SharedViewModel>()

        NavHost(
            navController = navController,
            startDestination = "categoryScreen"
        ) {
            composable("categoryScreen") {
                CategoryScreen(navController,sharedViewModel)
            }
            composable("detailScreen") { backStackEntry ->

                DrinkDetailsScreen(sharedViewModel, navController, )
            }
        }
    }
}


