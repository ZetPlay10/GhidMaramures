package ro.cunbm.ghidmaramures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ro.cunbm.ghidmaramures.navigation.AppNavHost
import ro.cunbm.ghidmaramures.ui.theme.GhidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GhidTheme {
                AppNavHost()
            }
        }
    }
}
