package com.example.tweetsyapp_jetpackcompose.repository

import com.example.tweetsyapp_jetpackcompose.api.ProductApi
import com.example.tweetsyapp_jetpackcompose.models.ProductList
import com.example.tweetsyapp_jetpackcompose.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productApi: ProductApi) {

    private val _products: MutableStateFlow<Response<List<ProductList>>> =
        MutableStateFlow(Response.Success(emptyList()))
    val products: StateFlow<Response<List<ProductList>>>
        get() = _products

    private val _categories: MutableStateFlow<Response<List<String>>> =
        MutableStateFlow(Response.Success(emptyList()))
    val categories: StateFlow<Response<List<String>>>
        get() = _categories

    private val _categoryProducts: MutableStateFlow<Response<List<ProductList>>> =
        MutableStateFlow(Response.Success(emptyList()))
    val categoryProducts: StateFlow<Response<List<ProductList>>>
        get() = _categoryProducts

    suspend fun getProducts() {
        try {
            val response = productApi.getProducts()
            if (response.isSuccessful && response.body() != null) {
                _products.emit(Response.Success(response.body()))
            } else {
                _products.emit(Response.Error("Error"))
            }
        } catch (e: Exception) {
            _products.emit(Response.Error(e.message.toString()))
        }
    }

    suspend fun getCategories() {
        try {
            val response = productApi.getCategories()
            if (response.isSuccessful && response.body() != null) {
                _categories.emit(Response.Success(response.body()))
            } else {
                _categories.emit(Response.Error("Error"))
            }
        } catch (e: Exception) {
            _categories.emit(Response.Error(e.message.toString()))
        }
    }

    suspend fun getProductsByCategory(category: String) {
        try {
            val response = productApi.getProductsByCategory(category)
            if (response.isSuccessful && response.body() != null) {
                _categoryProducts.emit(Response.Success(response.body()))
            } else {
                _categoryProducts.emit(Response.Error("Error"))
            }
        } catch (e: Exception) {
            _categoryProducts.emit(Response.Error(e.message.toString()))
        }
    }
}