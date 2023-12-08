package fr.unilasalle.fanech.tp_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import fr.unilasalle.fanech.tp_android.databinding.ActivityMainBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productsAdapter: ProductsAdapter
    private val client = OkHttpClient()
    private var productList = ArrayList<Product>()
    private lateinit var viewModel: RetrofitViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val intent = Intent(applicationContext, ProductActivity::class.java)
        setContentView(binding.root) // R.layout.activity_main

        viewModel = RetrofitViewModel(RetrofitApi.getService())
        viewModel.products.observe(this) {
            for (product in it) {
                productList.add(product)
            }
            productsAdapter.notifyDataSetChanged()
        }
        // Products recyclerView
        val recyclerViewVariable = binding.productsRecyclerView
        productsAdapter = ProductsAdapter(productList)


        with(recyclerViewVariable) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
            adapter = productsAdapter
        }






    }


}