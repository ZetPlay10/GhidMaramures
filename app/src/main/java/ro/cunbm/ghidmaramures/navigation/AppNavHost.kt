package ro.cunbm.ghidmaramures.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ro.cunbm.ghidmaramures.data.Category
import ro.cunbm.ghidmaramures.data.FavoritesViewModel
import ro.cunbm.ghidmaramures.data.PlacesRepository
import ro.cunbm.ghidmaramures.ui.screens.AboutScreen
import ro.cunbm.ghidmaramures.ui.screens.FavoritesScreen
import ro.cunbm.ghidmaramures.ui.screens.LoginScreen
import ro.cunbm.ghidmaramures.ui.screens.PlaceDetailScreen
import ro.cunbm.ghidmaramures.ui.screens.PlaceListScreen

/**
 * Singurul loc (împreună cu bara inferioară) unde apare NavController.
 * Ecranele primesc doar date și lambda-uri (state hoisting).
 */
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val favoritesVm: FavoritesViewModel = viewModel()
    val favoriteIds by favoritesVm.favoriteIds.collectAsStateWithLifecycle()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val showBottomBar = currentDestination != null && !currentDestination.hasRoute(Login::class)

    Scaffold(
        bottomBar = {
            if (showBottomBar) AppBottomBar(navController, currentDestination)
        }
    ) { innerPadding ->
        val bottom = innerPadding.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Login,
            modifier = Modifier
                .padding(bottom = bottom)
                .consumeWindowInsets(PaddingValues(bottom = bottom))
        ) {
            composable<Login> {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(CategoriesGraph) {
                            // Curățarea stivei: Back după login închide aplicația
                            popUpTo<Login> { inclusive = true }
                        }
                    }
                )
            }

            navigation<CategoriesGraph>(startDestination = PlaceList) {
                composable<PlaceList> {
                    // rememberSaveable la nivelul destinației: filtrul supraviețuiește
                    // deschiderii detaliului, schimbării tab-urilor (saveState) și rotirii.
                    var filter by rememberSaveable { mutableStateOf<Category?>(null) }
                    PlaceListScreen(
                        places = PlacesRepository.places,
                        selectedCategory = filter,
                        onCategorySelected = { filter = it },
                        onNavigateToDetail = { id -> navController.navigate(PlaceDetail(id)) }
                    )
                }
                composable<PlaceDetail> { entry ->
                    val route = entry.toRoute<PlaceDetail>()
                    val place = PlacesRepository.getById(route.id)
                    PlaceDetailScreen(
                        place = place,
                        isFavorite = route.id in favoriteIds,
                        onToggleFavorite = { favoritesVm.toggle(route.id) },
                        // Aceeași acțiune ca Back-ul sistemului
                        onBack = { navController.popBackStack() }
                    )
                }
            }

            composable<Favorites> {
                val favorites = PlacesRepository.places.filter { it.id in favoriteIds }
                FavoritesScreen(
                    favorites = favorites,
                    onRemove = { id -> favoritesVm.remove(id) }
                )
            }

            composable<About> {
                AboutScreen()
            }
        }
    }
}
