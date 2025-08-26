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
        super.onCreate(savedInstanceState)
        setContent {
            AlMadinaPOSTheme {
                AppNavigation()
            }
        }
    }
}
