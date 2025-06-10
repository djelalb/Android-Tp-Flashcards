package com.example.tp_flashcard

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp_flashcard.ui.home.HomeScreen
import com.example.tp_flashcard.viewmodel.HomeViewModel
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.tp_flashcard.ui.home.HomeScreen
import com.example.tp_flashcard.ui.flashcard.FlashcardScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tp_flashcard.viewmodel.FlashcardViewModel

private const val ROUTE_HOME = "home"
private const val ROUTE_FLASHCARDS = "flashcards/{categoryId}"
private const val ARG_CATEGORY_ID = "categoryId"

@Composable
fun FlashcardNavHost(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ROUTE_HOME,
        modifier = modifier
    ) {
        composable(ROUTE_HOME) {
            HomeScreen(
                homeViewModel = homeViewModel,
                onCategoryClick = { category ->
                    navController.navigate("flashcards/${category.id}")
                }
            )
        }

        composable(
            route = ROUTE_FLASHCARDS,
            arguments = listOf(
                navArgument(ARG_CATEGORY_ID) { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments
                ?.getLong(ARG_CATEGORY_ID) ?: return@composable

            val flashcardViewModel: FlashcardViewModel = viewModel()

            FlashcardScreen(
                categoryId = categoryId,
                flashcardViewModel = flashcardViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}