package fr.unilasalle.fanech.tp_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.unilasalle.fanech.tp_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productsAdapter: ProductsAdapter
    private var productList = ArrayList<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // R.layout.activity_main



        // Products recyclerView
        val recyclerViewVariable = binding.productsRecyclerView
        productsAdapter = ProductsAdapter(productList)

        with(recyclerViewVariable) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
            adapter = productsAdapter
        }





    }
}