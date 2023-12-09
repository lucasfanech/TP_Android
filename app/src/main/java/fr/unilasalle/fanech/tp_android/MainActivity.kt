package fr.unilasalle.fanech.tp_android

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.util.Log
import androidx.appcompat.app.AlertDialog
import fr.unilasalle.fanech.tp_android.databinding.ActivityMainBinding
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() , ProductsAdapter.OnClickListener
{
    private lateinit var binding: ActivityMainBinding
    private lateinit var productsAdapter: ProductsAdapter
    private val client = OkHttpClient()
    private var productList = ArrayList<Product>()
    private var categoryList = ArrayList<String>()
    private var cartList    = ArrayList<Product>()
    private lateinit var viewModel: RetrofitViewModel


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val intent = Intent(applicationContext, ProductActivity::class.java)
        setContentView(binding.root) // R.layout.activity_main


        productList.add(Product(1,"Album C'est pas des LOL",9.99f,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSnDwLhK-z_u-lD0AUlHyeKZ5Wgb5yn6s6_fg&usqp=CAU","Meilleur album du J","chef d'oeuvre",Rating(5.0f,1)))

        viewModel = RetrofitViewModel(RetrofitApi.getService())
        viewModel.products.observe(this) {
            if (it != null) {
                for (product in it) {
                    productList.add(product)
                }
            } else {
                print("error")
            }
            productsAdapter.notifyDataSetChanged()
        }

        viewModel.categories.observe(this) {
            if (it != null) {
                for (category in it) {
                    categoryList.add(category)
                }
            } else {
                print("error")
            }
        }
        // Products recyclerView
        val recyclerViewVariable = binding.productsRecyclerView
        productsAdapter = ProductsAdapter(productList, this)


        with(recyclerViewVariable) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
            adapter = productsAdapter
        }

        // category spinner
        val categorySpinner: Spinner = findViewById(R.id.categoriesSpinner)
        // Create an ArrayAdapter using the category list and a default spinner layout
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categoryList
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter
        }




    }
    override fun oncClickAddToCart(position: Product) {
        cartList.add(position)
        // Pop up message
        Log.d("cartList", cartList.toString())
        binding.cartProductCount.text = cartList.size.toString()
    }
    override fun onClick(position: Product)
    {
        val intent = Intent(applicationContext, ProductActivity::class.java);
        intent.putExtra("product", position);
        startActivity(intent)

    }


}