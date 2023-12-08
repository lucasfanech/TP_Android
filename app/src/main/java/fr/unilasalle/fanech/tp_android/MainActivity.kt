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

        productList.add(Product(1, "Sac à dos", 29.99f, "https://media.licdn.com/dms/image/C4D03AQG-FgaSvuhJkw/profile-displayphoto-shrink_400_400/0/1573481088941?e=2147483647&v=beta&t=VY3VLQlj4UgJqyyuahIB_Hvmz-zD8StQv0YcolIAgqk"))
        productList.add(Product(2, "T-shirt", 19.99f, "https://media.licdn.com/dms/image/C4D03AQG-FgaSvuhJkw/profile-displayphoto-shrink_400_400/0/1573481088941?e=2147483647&v=beta&t=VY3VLQlj4UgJqyyuahIB_Hvmz-zD8StQv0YcolIAgqk"))
        productList.add(Product(3, "Pantalon", 39.99f, "https://media.licdn.com/dms/image/C4D03AQG-FgaSvuhJkw/profile-displayphoto-shrink_400_400/0/1573481088941?e=2147483647&v=beta&t=VY3VLQlj4UgJqyyuahIB_Hvmz-zD8StQv0YcolIAgqk"))

        // Products recyclerView
        val recyclerViewVariable = binding.productsRecyclerView
        productsAdapter = ProductsAdapter(productList)

        with(recyclerViewVariable) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
            adapter = productsAdapter
        }

    }
}