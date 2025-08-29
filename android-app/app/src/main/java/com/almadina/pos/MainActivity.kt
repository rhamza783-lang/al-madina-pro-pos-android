package com.almadina.pos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.almadina.pos.ui.navigation.AppNavigation
import com.almadina.pos.ui.theme.AlMadinaPOSTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // It's a good practice to set the theme from resources before calling super.onCreate()
        setTheme(R.style.Theme_AlMadinaPOS_NoActionBar)

        super.onCreate(savedInstanceState)
        
        setContent {
            // The Jetpack Compose theme then takes over for all the in-app UI.
            AlMadinaPOSTheme {
                AppNavigation()
            }
        }
    }
}
