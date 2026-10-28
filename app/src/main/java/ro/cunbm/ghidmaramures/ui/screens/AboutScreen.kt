package ro.cunbm.ghidmaramures.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    Scaffold(topBar = { TopAppBar(title = { Text("Despre") }) }) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Ghid Maramureș", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            Text("Versiunea 1.0")
            Text("Autor: Hojda Paul-Andrei")
            Spacer(Modifier.height(16.dp))
            Text(
                "Ghid turistic pentru Maramureș: muzee, locuri pentru sport și restaurante. " +
                    "Temă la Calcul Mobil (Laboratorul 05) — navigare cu Jetpack Compose " +
                    "Navigation și rute type-safe.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
