package com.example.shoes_store.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.shoes_store.model.BrandModel
import com.example.shoes_store.model.ItemModel
import com.example.shoes_store.model.SliderModel
import com.example.shoes_store.repository.MainRepository


class MainViewModel :ViewModel() {
    private val repository = MainRepository()
    val brands : LiveData<MutableList<BrandModel>> = repository.brands
    val banners : LiveData<List<SliderModel>> = repository.banners
    val popular : LiveData<MutableList<ItemModel>> = repository.popular

    fun loadBrands( ) = repository.loadBrands()
    fun loadBanners() = repository.loadBanners()
    fun loadPopular() = repository.loadPopular()
}