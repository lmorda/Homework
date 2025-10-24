package com.lmorda.homework

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lmorda.homework.MainContract.State
import com.lmorda.homework.MainContract.State.CheckedInitialNavigation
import com.lmorda.homework.ui.contacts.ContactsScreenRoute
import com.lmorda.homework.ui.details.DetailsScreenRoute
import com.lmorda.homework.ui.explore.ExploreScreenRoute
import com.lmorda.homework.ui.login.LoginScreenRoute
import com.lmorda.homework.ui.selectAccount.SelectAccountScreenRoute
import com.lmorda.homework.ui.shared.HomeworkProgressIndicator
import com.lmorda.homework.ui.shared.HomeworkSnackbar

const val routeLogin = "login"
const val routeSelectAccount = "selectAccount"
const val routeExplore = "explore"
const val routeDetailsBase = "details"
const val argDetailsId = "id"
const val routeDetailsFull = "$routeDetailsBase/{$argDetailsId}"
const val routeContacts = "contacts"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun HomeworkNavHost(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val viewModel = hiltViewModel<MainViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state) {
        is CheckedInitialNavigation -> {
            Scaffold(
                snackbarHost = { HomeworkSnackbar(snackbarHostState) }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = (state as CheckedInitialNavigation).route,
                ) {
                    composable(route = routeLogin) {
                        LoginScreenRoute(
                            viewModel = hiltViewModel(),
                            onNavigateToAccountSelect = {
                                navController.navigate(routeSelectAccount)
                            },
                            snackbarHostState = snackbarHostState,
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
                            snackbarHostState = snackbarHostState,
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
        }
        else -> HomeworkProgressIndicator()
    }
}
