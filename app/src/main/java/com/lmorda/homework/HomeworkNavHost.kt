package com.lmorda.homework

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lmorda.homework.ui.contacts.ContactsScreenRoute
import com.lmorda.homework.ui.details.DetailsScreenRoute
import com.lmorda.homework.ui.explore.ExploreScreenRoute
import com.lmorda.homework.ui.login.LoginScreenRoute
import com.lmorda.homework.ui.selectAccount.SelectAccountScreenRoute

const val routeLogin = "login"
const val routeSelectAccount = "selectAccount"
const val routeExplore = "explore"
const val routeDetailsBase = "details"
const val argDetailsId = "id"
const val routeDetailsFull = "$routeDetailsBase/{$argDetailsId}"
const val routeContacts = "contacts"

@Composable
internal fun HomeworkNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = routeLogin,
    ) {
        composable(route = routeLogin) {
            LoginScreenRoute(
                viewModel = hiltViewModel(),
                onNavigateToAccountSelect = {
                    navController.navigate(routeSelectAccount)
                }
            )
        }
        composable(route = routeSelectAccount) {
            SelectAccountScreenRoute(
                viewModel = hiltViewModel(),
                onBack = {
                    navController.navigateUp()
                },
                onNavigateToExplore = {
                    navController.navigate(routeExplore)
                },
            )
        }
        composable(route = routeContacts) {
            ContactsScreenRoute(
                viewModel = hiltViewModel(),
                onBack = {
                    navController.navigateUp()
                },
            )
        }
        composable(route = routeExplore) {
            ExploreScreenRoute(
                viewModel = hiltViewModel(),
                onNavigateToDetails = { id ->
                    navController.navigate("$routeDetailsBase/$id")
                },
                onNavigateToContacts = {
                    navController.navigate(routeContacts)
                },
            )
        }
        composable(
            route = routeDetailsFull,
            arguments = listOf(
                navArgument(name = argDetailsId) { type = NavType.LongType },
            ),
        ) {
            DetailsScreenRoute(
                viewModel = hiltViewModel(),
                onBack = {
                    navController.navigateUp()
                },
            )
        }
        composable(route = routeContacts) {
            ContactsScreenRoute(
                viewModel = hiltViewModel(),
                onBack = {
                    navController.navigateUp()
                },
            )
        }
    }
}
