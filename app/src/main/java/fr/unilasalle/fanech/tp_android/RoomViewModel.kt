package fr.unilasalle.fanech.tp_android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoomViewModel(private val database: AppDatabase) : ViewModel()
{

    private var products = MutableLiveData<List<ProductEntity>>()
    val productsLiveData = products


    init {
        getAllProducts()
    }


    fun getAllProducts() {
        products.postValue(database.productDao().getAllProducts())
    }

    fun insertProduct(product: ProductEntity) {
        database.productDao().insertProduct(product)
    }

    fun deleteProductById(cartId: Int) {
        database.productDao().deleteProductById(cartId)
    }

    fun deleteProductByCartIdAndId(cartId: Int, id: Int) {
        database.productDao().deleteProductByCartIdAndId(cartId, id)
    }

    fun updateProductCartId(id: Int, cartId: Int) {
        database.productDao().updateProductCartId(id, cartId)
    }

    fun getMaxCartId(): Int {
        return database.productDao().getMaxCartId()
    }






}