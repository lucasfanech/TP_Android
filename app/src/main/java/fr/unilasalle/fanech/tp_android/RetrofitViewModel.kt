package fr.unilasalle.fanech.tp_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch

class RetrofitViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    private val _categories = MutableLiveData<List<String>>()
    val products: LiveData<List<Product>>
        get() = _products

    val categories: LiveData<List<String>>
        get() = _categories

    init {
        viewModelScope.launch {
            _products.value = retrofitService.getProducts()
            _categories.value = retrofitService.getCategories()
        }
    }
}