package fr.unilasalle.fanech.tp_android

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import fr.unilasalle.fanech.tp_android.databinding.ActivityCartBinding
import java.util.ArrayList

class CartActivity : AppCompatActivity(), CartAdapter.OnClickListener {

    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter
    private var cartList    = ArrayList<Product>()
    private var cartCounter = 0
    private val total : Float = 0.0f
    private lateinit var db: AppDatabase
    private lateinit var productViewModel: RoomViewModel
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root) // R.layout.activity_cart

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "product-database"
        ).allowMainThreadQueries().build()

        productViewModel = RoomViewModelFactory(db).create(RoomViewModel::class.java)

        //Use the type-safer getParcelableArrayListExtra
        cartList = intent.getParcelableArrayListExtra<Product>("cartList") ?: ArrayList<Product>()
        cartCounter = intent.getIntExtra("cartCounter", 0)
        val recyclerView = binding.cartRecyclerView
        cartAdapter = CartAdapter(cartList, this)

        with(recyclerView) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }

        binding.cartCount.text = cartList.size.toString() + " items"
        binding.cartPrice.text = "Total : " + "%.2f".format(cartList.sumOf{it.price.toDouble()}) + "€"

        binding.purchaseButton.setOnClickListener() {
            val builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("Purchase")
            builder.setMessage("Are you sure you want to purchase these items ?" + "\n" + "Total : " + "%.2f".format(cartList.sumOf{it.price.toDouble()}) + "€" )
            builder.setPositiveButton("Yes") { dialog, which ->
                cartList.clear()
                binding.cartCount.text = cartList.size.toString() + " items"
                binding.cartPrice.text = "Total : " + "%.2f".format(cartList.sumOf{it.price.toDouble()}) + "€"
                cartAdapter.notifyDataSetChanged()


                db.productDao().getAllProducts().forEach {
                    db.productDao().updateProductCartId(it.id, cartCounter)
                }

                val intent = Intent()
                intent.putExtra("cartList", cartList)
                setResult(RESULT_OK, intent)
                finish()

                //  make a toast to display the current selected item
                Toast.makeText(this, "Purchase done", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("No") { dialog, which ->
                Log.d("Main", "Negative button clicked")
            }
            builder.show()
        }

    }
    override fun onClick(position: Product) {
        val intent = Intent(applicationContext, ProductActivity::class.java);
        intent.putExtra("product", position);
        startActivity(intent)
    }

}