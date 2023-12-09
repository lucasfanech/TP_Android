package fr.unilasalle.fanech.tp_android

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import fr.unilasalle.fanech.tp_android.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity(), CartAdapter.OnClickListener {

    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter
    private var cartList    = ArrayList<Product>()
    private val total : Float = 0.0f

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root) // R.layout.activity_cart


        //Use the type-safer getParcelableArrayListExtra
        cartList = intent.getParcelableArrayListExtra("cartList",Product::class.java) as ArrayList<Product>

        val recyclerView = binding.cartRecyclerView
        cartAdapter = CartAdapter(cartList, this)

        with(recyclerView) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }





        binding.cartCount.text = cartList.size.toString() + " items"
        binding.cartPrice.text = "Total : " + "%.2f".format(cartList.sumOf{it.price.toDouble()}) + "€"

    }
    override fun onClick(position: Product) {
        val intent = Intent(applicationContext, ProductActivity::class.java);
        intent.putExtra("product", position);
        startActivity(intent)
    }

}