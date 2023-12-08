package fr.unilasalle.fanech.tp_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.unilasalle.fanech.tp_android.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity()
{

    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root) // R.layout.activity_product

        //binding.textView2.text = intent.extras?.getString("value")
        binding.productNameTextView.text = intent.extras?.getString("title")
        binding.productPriceTextView.text = intent.extras?.getString("price")
        binding.productDescTextView.text = intent.extras?.getString("description")
        binding.productCategoryTextView.text = intent.extras?.getString("category")
        binding.productReviewTextView.text = intent.extras?.getString("rating")
        binding.productReviewsNbTextView.text = intent.extras?.getString("count")
    }
}