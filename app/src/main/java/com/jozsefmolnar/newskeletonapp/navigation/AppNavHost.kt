package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jozsefmolnar.newskeletonapp.ui.model.DetailsViewModel
import com.jozsefmolnar.newskeletonapp.ui.model.HomeViewModel

@Composable
fun AppNavHost() {
    val navController = androidx.navigation.compose.rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route,
    ) {
        composable(route = Screen.MainScreen.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = viewModel,
                onNewsItemClicked = { navController.navigate(Screen.DetailsScreen.withArgs(it.id!!)) },
            )
        }

        composable(route = Screen.DetailsScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { entry ->
            val viewModel = hiltViewModel<DetailsViewModel>()
            viewModel.setArticleId(entry.arguments?.getInt("id")!!)

            DetailsScreen(
                viewModel = viewModel,
                navigateUp = navController::navigateUp
            )
        }
    }
}
