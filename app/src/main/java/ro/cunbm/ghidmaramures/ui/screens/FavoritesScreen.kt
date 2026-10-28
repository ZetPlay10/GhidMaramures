package ro.cunbm.ghidmaramures.ui.screens

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ro.cunbm.ghidmaramures.data.Place

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    favorites: List<Place>,
    onRemove: (Int) -> Unit
) {
    Scaffold(topBar = { TopAppBar(title = { Text("Favorite") }) }) { padding ->
        if (favorites.isEmpty()) {
            Box(
                Modifier.fillMaxSize().padding(padding).padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Nu ai încă locuri favorite.\nDeschide un obiectiv din Categorii " +
                        "și apasă „Adaugă la favorite”.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(Modifier.fillMaxSize().padding(padding)) {
                items(favorites, key = { it.id }) { place ->
                    ListItem(
                        leadingContent = {
                            Image(
                                painter = painterResource(place.imageRes),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(56.dp).clip(RoundedCornerShape(8.dp))
                            )
                        },
                        headlineContent = { Text(place.name) },
                        supportingContent = { Text("${place.category.label} · ${place.town}") },
                        trailingContent = {
                            IconButton(onClick = { onRemove(place.id) }) {
                                Icon(Icons.Filled.Delete, contentDescription = "Elimină din favorite")
                            }
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
