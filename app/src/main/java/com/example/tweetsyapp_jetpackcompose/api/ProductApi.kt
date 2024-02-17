package com.example.tweetsyapp_jetpackcompose.api

import com.example.tweetsyapp_jetpackcompose.models.ProductList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("/products")
    suspend fun getProducts() : Response<List<ProductList>>

    @GET("/products/categories")
    suspend fun getCategories() : Response<List<String>>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String) : Response<List<ProductList>>
}