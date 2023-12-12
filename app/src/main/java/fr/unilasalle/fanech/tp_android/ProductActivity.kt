package fr.unilasalle.fanech.tp_android

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import fr.unilasalle.fanech.tp_android.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity()
{

    private lateinit var binding: ActivityProductBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root) // R.layout.activity_product

        //binding.textView2.text = intent.extras?.getString("value")
        val product = intent.getParcelableExtra("product") as? Product
        //val product = intent.getParcelableExtra("product",Product::class.java)
        binding.productNameTextView.text        = product?.title
        binding.productPriceTextView.text       = product?.price.toString().plus("€")
        binding.productDescTextView.text        = product?.description
        binding.productCategoryTextView.text    = product?.category
        binding.productReviewTextView.text      = product?.rating?.rate.toString().plus("★")
        binding.productReviewsNbTextView.text   = product?.rating?.count.toString().plus(" reviews")
        //Glide.with(itemView.context).load(item.image).into(image)
        binding.imageView2.let {
            Glide.with(this)
                .load(product?.image)
                .into(it)
        }
    }
}