package ro.cunbm.ghidmaramures.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Light = lightColorScheme(
    primary = Color(0xFF6B4E16),
    secondary = Color(0xFF4F6354),
    tertiary = Color(0xFF8C3B2E)
)
private val Dark = darkColorScheme(
    primary = Color(0xFFE2C28A),
    secondary = Color(0xFFB6CCB9),
    tertiary = Color(0xFFFFB4A6)
)

@Composable
fun GhidTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) Dark else Light,
        content = content
    )
}
