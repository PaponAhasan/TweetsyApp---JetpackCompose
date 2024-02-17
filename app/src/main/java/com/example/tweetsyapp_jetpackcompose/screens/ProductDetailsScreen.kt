package com.example.tweetsyapp_jetpackcompose.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.tweetsyapp_jetpackcompose.R
import com.example.tweetsyapp_jetpackcompose.models.ProductList
import com.example.tweetsyapp_jetpackcompose.utils.Response
import com.example.tweetsyapp_jetpackcompose.viewmodels.ProductListViewModel

@Composable
fun ProductDetailScreen() {
    val productListViewModel: ProductListViewModel = viewModel()
    val productsResponse = productListViewModel.categoryProducts.collectAsState().value
    when (productsResponse) {
        is Response.Loading -> {
            // Handle loading state
        }

        is Response.Success -> {
            productsResponse.data?.let { products ->
                LazyColumn(content = {
                    items(products) { product ->
                        ProductListItem(product)
                    }
                })
            }
        }

        is Response.Error -> {
            val errorMessage = productsResponse.errorMessage // Access the error message
            Log.d("Error", errorMessage.toString())
        }
    }
}

@Composable
fun ProductListItem(product: ProductList) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        border = BorderStroke(1.dp, Color(0xFFCCCCCC)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        content = {
            Row {
                AsyncImage(
                    model = product.image,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    error = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "The product logo",
                    modifier = Modifier.size(100.dp).padding(8.dp),
                    contentScale = ContentScale.Inside
                )
                Column {
                    Text(
                        text = product.title,
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Description: ${product.description}",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Price: $${product.price}",
                        modifier = Modifier.padding(5.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Rating: ${product.rating.rate}/5",
                        modifier = Modifier.padding(5.dp),
                        style = MaterialTheme.typography.bodySmall
                    )

                }
            }
        }
    )
}