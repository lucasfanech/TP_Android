package fr.unilasalle.fanech.tp_android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDataAccessObject
{
    @Insert
    fun insertProduct(product: ProductEntity)

    @Query("SELECT * FROM product")
    fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM product WHERE productId = :id")
    fun getProductById(id: Int): ProductEntity

    @Query("SELECT * FROM product WHERE cartId = :cartId")
    fun getProductsByCartId(cartId: Int): List<ProductEntity>

    @Query("DELETE FROM product WHERE productId = :id")
    fun deleteProductById(id: Int)
}