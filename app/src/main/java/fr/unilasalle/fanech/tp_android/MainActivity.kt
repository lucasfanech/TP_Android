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

class MainActivity : AppCompatActivity() , ProductsAdapter.OnClickListener
{
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
        productsAdapter = ProductsAdapter(productList, this)


        with(recyclerViewVariable) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
            adapter = productsAdapter
        }






    }

    override fun onClick(product: Product)
    {
        print("clicked")
        val intent = Intent(applicationContext, ProductActivity::class.java)
        intent.putExtra("id", product.id.toString());
        intent.putExtra("title", product.title);
        intent.putExtra("price", product.price.toString());
        intent.putExtra("image", product.image);
        intent.putExtra("description", product.description);
        intent.putExtra("category", product.category);
        intent.putExtra("rate", product.rating.rate.toString());
        intent.putExtra("count", product.rating.count.toString());

        startActivity(intent)

    }


}