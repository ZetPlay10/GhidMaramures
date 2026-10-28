package ro.cunbm.ghidmaramures.ui.screens

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ro.cunbm.ghidmaramures.data.Place

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailScreen(
    place: Place?,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(place?.name ?: "Detaliu", maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Înapoi")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            if (place == null) {
                Text("Locul nu a fost găsit.")
                return@Column
            }
            Image(
                painter = painterResource(place.imageRes),
                contentDescription = place.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(Modifier.height(16.dp))
            Text(place.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            AssistChip(onClick = {}, label = { Text(place.category.label) })
            Spacer(Modifier.height(8.dp))
            Text("Localitate: ${place.town}", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(16.dp))
            Text(place.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(24.dp))

            val icon = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
            val text = if (isFavorite) "Elimină din favorite" else "Adaugă la favorite"
            if (isFavorite) {
                OutlinedButton(onClick = onToggleFavorite, modifier = Modifier.fillMaxWidth()) {
                    Icon(icon, contentDescription = null, Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp)); Text(text)
                }
            } else {
                Button(onClick = onToggleFavorite, modifier = Modifier.fillMaxWidth()) {
                    Icon(icon, contentDescription = null, Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp)); Text(text)
                }
            }
        }
    }
}
