package com.mx.android.herocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mx.android.common.base.ShareViewModel
import com.mx.android.dashboard.screen.DashboardScreen
import com.mx.android.ui.theme.HeroComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private  val viewModel: ShareViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val darkMode = applicationContext.resources.configuration.uiMode
        val bundle = Bundle()
        bundle.putInt("darkMode",  darkMode)

        viewModel.setUp(bundle)

        setContent {
            val darkTheme by viewModel.isDarkTheme.collectAsState()
            HeroComposeTheme(darkTheme) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DashboardScreen()
                }
            }
        }
    }
}