package com.example.aot_api.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aot_api.ui.CharacterDetailScreen
import com.example.aot_api.ui.CharacterListScreen
import com.example.aot_api.viewmodel.CharacterViewModel

@Composable
fun AppNavigation() {

    //nav controller
    val navController = rememberNavController()

    //viewmodel
    val characterViewModel: CharacterViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.CHARACTER_LIST
    ) {
        //liste perso
        composable(Routes.CHARACTER_LIST) {
            CharacterListScreen(
                viewModel = characterViewModel,
                onCharacterClick = { characterId ->
                    navController.navigate(Routes.characterDetail(characterId))
                }
            )
        }

        //details perso
        composable(
            route = Routes.CHARACTER_DETAIL,
            arguments = listOf(
                navArgument("characterId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: return@composable

            CharacterDetailScreen(
                characterId = characterId,
                viewModel = characterViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
