package fr.unilasalle.fanech.tp_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch

class RetrofitViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products



    init {
        viewModelScope.launch {
            _products.value = retrofitService.getProducts()
        }
    }
}