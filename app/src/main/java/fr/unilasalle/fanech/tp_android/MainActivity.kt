package fr.unilasalle.fanech.tp_android

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import fr.unilasalle.fanech.tp_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProductsAdapter.OnClickListener,
    AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productsAdapter: ProductsAdapter
    private var originalProductList = ArrayList<Product>()
    private var productList = ArrayList<Product>()
    private var categoryList = ArrayList<String>()
    private var cartList = java.util.ArrayList<Product>()
    private lateinit var viewModel: RetrofitViewModel
    private lateinit var db: AppDatabase
    private lateinit var productViewModel: RoomViewModel
    private var cartProductCount = 0


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //val intent = Intent(applicationContext, ProductActivity::class.java)
        setContentView(binding.root) // R.layout.activity_main

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "product-database"
        ).allowMainThreadQueries().build()

        productViewModel = RoomViewModelFactory(db).create(RoomViewModel::class.java)
        db.productDao().getAllProducts().forEach {
            if (it.cartId == 0)
            {
                cartList.add(Product(it.id, it.name, it.price, it.image, it.description, it.category, Rating(it.rate, it.rateCount)))
            }
            binding.cartProductCount.text = cartList.size.toString()
        }

        cartProductCount = db.productDao().getMaxCartId();


        productList.add(
            Product(
                1,
                "Album C'est pas des LOL",
                9.99f,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSnDwLhK-z_u-lD0AUlHyeKZ5Wgb5yn6s6_fg&usqp=CAU",
                "Meilleur album du J",
                "chef d'oeuvre",
                Rating(5.0f, 1)
            )
        )
        categoryList.add("All categories")


        binding.cart.setOnClickListener {
            Log.e("aladin", cartList.size.toString())
            val cartIntent = Intent(applicationContext, CartActivity::class.java)
            //cartIntent.putParcelableArrayListExtra("cartList", cartList)
            cartIntent.putExtra("cartList",  cartList)
            cartIntent.putExtra("cartCounter", cartProductCount)
            startActivity(cartIntent)
        }


        viewModel = RetrofitViewModel(RetrofitApi.getService())
        viewModel.products.observe(this) {
            if (it != null) {
                for (product in it) {
                    productList.add(product)
                    originalProductList.add(product)
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
        val categorySpinner: Spinner = binding.categoriesSpinner
        categorySpinner.onItemSelectedListener = this
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

    override fun onResume() {
        super.onResume()
        cartList = intent.getParcelableArrayListExtra("cartList") ?: ArrayList<Product>()
        binding.cartProductCount.text = cartList.size.toString()
    }

    override fun oncClickAddToCart(position: Product) {
        cartList.add(position)
        // Pop up message
        Log.d("cartList", cartList.toString())
        binding.cartProductCount.text = cartList.size.toString()
        db.productDao().insertProduct(ProductEntity(
            productId = position.id,
            cartId = 0,
            name = position.title,
            price = position.price,
            image = position.image,
            description = position.description,
            category = position.category,
            rate = position.rating.rate,
            rateCount = position.rating.count
        ))

    }

    override fun onClick(position: Product) {
        val intent = Intent(applicationContext, ProductActivity::class.java)
        intent.putExtra("product", position);
        startActivity(intent)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        val selectedCategory = parent?.getItemAtPosition(pos).toString()
        val filteredProducts = if (selectedCategory == "All categories") {
            originalProductList
        } else {
            originalProductList.filter { it.category == selectedCategory }
        }
        productList.clear()
        productList.addAll(filteredProducts)
        productsAdapter.notifyDataSetChanged()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }


}