package fr.unilasalle.fanech.tp_android

import retrofit2.http.GET

interface RetrofitService
{
    @GET("/products")
    suspend fun getProducts(): List<Product>
}