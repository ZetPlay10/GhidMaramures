package ro.cunbm.ghidmaramures.data

import androidx.annotation.DrawableRes

enum class Category(val label: String) {
    SPORT("Sport"),
    MUZEE("Muzee"),
    RESTAURANTE("Restaurante")
}

data class Place(
    val id: Int,
    val name: String,
    val category: Category,
    val town: String,
    val description: String,
    @DrawableRes val imageRes: Int
)
