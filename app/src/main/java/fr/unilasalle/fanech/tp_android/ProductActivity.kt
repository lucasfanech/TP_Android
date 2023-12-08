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
    }
}