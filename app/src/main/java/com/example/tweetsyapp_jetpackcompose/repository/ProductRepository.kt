package com.example.tweetsyapp_jetpackcompose.repository

import android.util.Log
import com.example.tweetsyapp_jetpackcompose.api.ProductApi
import com.example.tweetsyapp_jetpackcompose.models.ProductList
import com.example.tweetsyapp_jetpackcompose.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productApi: ProductApi) {

    private val _products: MutableStateFlow<List<ProductList>> =
        MutableStateFlow(emptyList())
    val products: StateFlow<List<ProductList>>
        get() = _products

    private val _categories: MutableStateFlow<List<String>> =
        MutableStateFlow(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _categoryProducts: MutableStateFlow<List<ProductList>> =
        MutableStateFlow(emptyList())
    val categoryProducts: StateFlow<List<ProductList>>
        get() = _categoryProducts

    suspend fun getProducts() {
        try {
            val response = productApi.getProducts()
            if (response.isSuccessful && response.body() != null) {
                _products.emit(response.body()!!)
            } else {
                Log.d("ProductRepository", "Error")
            }
        } catch (e: Exception) {
            Log.d("ProductRepository", e.message.toString())
        }
    }

    suspend fun getCategories() {
        try {
            val response = productApi.getCategories()
            if (response.isSuccessful && response.body() != null) {
                _categories.emit(response.body()!!)
            } else {
                Log.d("ProductRepository", "Error")
            }
        } catch (e: Exception) {
            Log.d("ProductRepository", e.message.toString())
        }
    }

    suspend fun getProductsByCategory(category: String) {
        try {
            val response = productApi.getProductsByCategory(category)
            if (response.isSuccessful && response.body() != null) {
                _categoryProducts.emit(response.body()!!)
            } else {
                Log.d("ProductRepository", "Error")
            }
        } catch (e: Exception) {
            Log.d("ProductRepository", e.message.toString())
        }
    }
}