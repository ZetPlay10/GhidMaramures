package ro.cunbm.ghidmaramures.navigation

import kotlinx.serialization.Serializable

// Rute type-safe. Nicio rută nu este definită ca șir de caractere.

@Serializable object Login

// Graf imbricat pentru tab-ul Categorii: lista + detaliul
@Serializable object CategoriesGraph
@Serializable object PlaceList
@Serializable data class PlaceDetail(val id: Int)

@Serializable object Favorites
@Serializable object About
