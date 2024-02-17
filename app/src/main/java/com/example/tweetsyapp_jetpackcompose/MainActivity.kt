package com.example.tweetsyapp_jetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tweetsyapp_jetpackcompose.api.ProductApi
import com.example.tweetsyapp_jetpackcompose.screens.CategoryScreen.CategoryScreen
import com.example.tweetsyapp_jetpackcompose.screens.ProductDetailScreen
import com.example.tweetsyapp_jetpackcompose.ui.theme.TweetsyAppJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var productApi: ProductApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            val response = productApi.getCategories()
            Log.d("MainActivity", "onCreate: ${response.body()}")
        }
        setContent {
            TweetsyAppJetpackComposeTheme {
                //CategoryScreen()
                ProductDetailScreen()
            }
        }
    }
}