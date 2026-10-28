package ro.cunbm.ghidmaramures.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

/**
 * Stare partajată între tab-uri: id-urile locurilor favorite.
 * SavedStateHandle => supraviețuiește rotirii și opririi procesului.
 */
class FavoritesViewModel(private val handle: SavedStateHandle) : ViewModel() {

    val favoriteIds: StateFlow<IntArray> = handle.getStateFlow(KEY, intArrayOf())

    fun toggle(id: Int) {
        val current = favoriteIds.value
        handle[KEY] = if (id in current) current.filter { it != id }.toIntArray()
                      else current + id
    }

    fun remove(id: Int) {
        handle[KEY] = favoriteIds.value.filter { it != id }.toIntArray()
    }

    private companion object {
        const val KEY = "favorite_ids"
    }
}
