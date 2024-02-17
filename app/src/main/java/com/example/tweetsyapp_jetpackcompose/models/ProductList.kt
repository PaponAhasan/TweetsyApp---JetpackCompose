package com.example.tweetsyapp_jetpackcompose.models

data class ProductList(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)