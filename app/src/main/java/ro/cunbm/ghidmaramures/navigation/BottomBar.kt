package ro.cunbm.ghidmaramures.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController

data class TopLevelRoute<T : Any>(val label: String, val route: T, val icon: ImageVector)

val topLevelRoutes = listOf(
    TopLevelRoute("Categorii", CategoriesGraph, Icons.AutoMirrored.Filled.List),
    TopLevelRoute("Favorite", Favorites, Icons.Filled.Favorite),
    TopLevelRoute("Despre", About, Icons.Filled.Info)
)

@Composable
fun AppBottomBar(
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    NavigationBar {
        topLevelRoutes.forEach { tab ->
            // hierarchy => în PlaceDetail (din CategoriesGraph) rămâne marcat "Categorii"
            val selected = currentDestination?.hierarchy
                ?.any { it.hasRoute(tab.route::class) } == true

            NavigationBarItem(
                selected = selected,
                icon = { Icon(tab.icon, contentDescription = tab.label) },
                label = { Text(tab.label) },
                onClick = {
                    navController.navigate(tab.route) {
                        // Login a fost scos din stivă, deci baza stivei este PlaceList.
                        // Salvăm stiva tab-ului curent (ex. un detaliu deschis).
                        popUpTo<PlaceList> { saveState = true }
                        // Apăsare repetată pe același tab => fără copie nouă
                        launchSingleTop = true
                        // Revenire pe tab => se restaurează stiva salvată
                        restoreState = true
                    }
                }
            )
        }
    }
}
