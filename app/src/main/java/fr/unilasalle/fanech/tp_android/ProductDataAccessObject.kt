package fr.unilasalle.fanech.tp_android

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

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

    @Query("DELETE FROM product WHERE cartId = :cartId AND productId = :id")
    fun deleteProductByCartIdAndId(cartId: Int, id: Int)

    // UPDATE
    @Query("UPDATE product SET cartId = :cartId WHERE productId = :id")
    fun updateProductCartId(id: Int, cartId: Int)


    @Query("SELECT MAX(cartId) FROM product")
    fun getMaxCartId(): Int
}