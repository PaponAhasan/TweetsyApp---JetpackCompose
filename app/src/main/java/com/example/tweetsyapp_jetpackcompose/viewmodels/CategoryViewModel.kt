package com.example.tweetsyapp_jetpackcompose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsyapp_jetpackcompose.repository.ProductRepository
import com.example.tweetsyapp_jetpackcompose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {
    init {
        viewModelScope.launch {
            productRepository.getCategories()
        }
    }

    val categories: StateFlow<Response<List<String>>>
        get() = productRepository.categories
}