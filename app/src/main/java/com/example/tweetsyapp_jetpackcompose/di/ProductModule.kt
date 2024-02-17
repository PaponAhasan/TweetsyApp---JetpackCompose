package com.example.tweetsyapp_jetpackcompose.di

import com.example.tweetsyapp_jetpackcompose.api.ProductApi
import com.example.tweetsyapp_jetpackcompose.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

//@Module
//@InstallIn(ViewModelComponent::class)
//class ProductModule {
//    @Provides
//    fun providesProductRepository(productApi: ProductApi) : ProductRepository{
//        return ProductRepository(productApi)
//    }
//}