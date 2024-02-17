package com.example.tweetsyapp_jetpackcompose.viewmodels

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsyapp_jetpackcompose.models.ProductList
import com.example.tweetsyapp_jetpackcompose.repository.ProductRepository
import com.example.tweetsyapp_jetpackcompose.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            async { productRepository.getProducts() }.await()
            async { productRepository.getProductsByCategory("jewelery") }.await()
        }
    }

    val products: StateFlow<Response<List<ProductList>>>
        get() = productRepository.products
    val categoryProducts: StateFlow<Response<List<ProductList>>>
        get() = productRepository.categoryProducts

}