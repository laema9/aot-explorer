package com.example.aot_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.aot_api.navigation.AppNavigation
import com.example.aot_api.ui.theme.AotapiTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AotapiTheme() {
                AppNavigation()
            }
        }
    }
}
