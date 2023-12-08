package fr.unilasalle.fanech.tp_android

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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
    private var cartList    = ArrayList<Product>()
    private lateinit var viewModel: RetrofitViewModel


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val intent = Intent(applicationContext, ProductActivity::class.java)
        setContentView(binding.root) // R.layout.activity_main


        productList.add(Product(1,"Album C'est pas des LOL",9.99f,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSnDwLhK-z_u-lD0AUlHyeKZ5Wgb5yn6s6_fg&usqp=CAU","Meilleur album du J","test",Rating(1.0f,1)))

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
        // Products recyclerView
        val recyclerViewVariable = binding.productsRecyclerView
        productsAdapter = ProductsAdapter(productList, this)


        with(recyclerViewVariable) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
            adapter = productsAdapter
        }






    }

    override fun oncClickAddToCart(position: Product) {
        cartList.add(position)
        // Pop up message
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Ajout au panier")
    }
    override fun onClick(product: Product)
    {
        print("clicked")
        val intent = Intent(applicationContext, ProductActivity::class.java);
        intent.putExtra("product", product);
        startActivity(intent)

    }


}