package fr.unilasalle.fanech.tp_android

import androidx.room.Entity

@Entity(tableName = "product")
data class ProductEntity (
    @androidx.room.PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val productId: Int,
    val cartId: Int,
    val name: String,
    val price: Float,
    val image: String,
    val description: String,
    val category: String,
    val rate: Float,
    val rateCount: Int,
)
{
}