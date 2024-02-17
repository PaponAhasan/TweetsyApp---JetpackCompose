package com.example.tweetsyapp_jetpackcompose.viewmodels

import androidx.lifecycle.SavedStateHandle
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
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            async { productRepository.getProducts() }.await()

            val category = savedStateHandle.get<String>("category") ?: "jewelery"
            async { productRepository.getProductsByCategory(category) }.await()
        }
    }

    val products: StateFlow<Response<List<ProductList>>>
        get() = productRepository.products
    val categoryProducts: StateFlow<Response<List<ProductList>>>
        get() = productRepository.categoryProducts

}