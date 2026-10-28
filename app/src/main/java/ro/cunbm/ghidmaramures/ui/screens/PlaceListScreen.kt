package ro.cunbm.ghidmaramures.ui.screens

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ro.cunbm.ghidmaramures.data.Category
import ro.cunbm.ghidmaramures.data.Place

/**
 * Nu primește NavController. Filtrul vine din exterior (state hoisting),
 * iar navigarea iese ca eveniment prin onNavigateToDetail.
 * selectedCategory == null înseamnă „Toate”.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceListScreen(
    places: List<Place>,
    selectedCategory: Category?,
    onCategorySelected: (Category?) -> Unit,
    onNavigateToDetail: (Int) -> Unit
) {
    val visible = if (selectedCategory == null) places
                  else places.filter { it.category == selectedCategory }

    Scaffold(topBar = { TopAppBar(title = { Text("Obiective turistice") }) }) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { onCategorySelected(null) },
                    label = { Text("Toate") }
                )
                Category.entries.forEach { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { onCategorySelected(category) },
                        label = { Text(category.label) }
                    )
                }
            }
            LazyColumn(Modifier.fillMaxSize()) {
                items(visible, key = { it.id }) { place ->
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
                        modifier = Modifier.clickable { onNavigateToDetail(place.id) }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
