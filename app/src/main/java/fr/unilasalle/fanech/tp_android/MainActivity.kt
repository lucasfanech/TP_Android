package fr.unilasalle.fanech.tp_android

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // R.layout.activity_main
        run("https://fakestoreapi.com/products")


        // Products recyclerView
        val recyclerViewVariable = binding.productsRecyclerView
        productsAdapter = ProductsAdapter(productList)


        with(recyclerViewVariable) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
            adapter = productsAdapter
        }






    }

    fun run(url: String)
    {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response)
            {
                //println(response.body()?.string())

                val mapper = jacksonObjectMapper()
                var products: List<Product> = mapper.readValue(response.body()?.string()!!)
                products.forEach { println(it) }

                runOnUiThread {
                    productList.addAll(products)
                    productsAdapter.notifyDataSetChanged()
                }

            }
        })
    }
}