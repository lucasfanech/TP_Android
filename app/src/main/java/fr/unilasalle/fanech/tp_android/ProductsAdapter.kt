package fr.unilasalle.fanech.tp_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductsAdapter(
    private var productList: ArrayList<Product>,
    private val listener: OnClickListener
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(
        itemView: View,
        private val listener: OnClickListener
    ) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.productTitle)
        val price: TextView = itemView.findViewById(R.id.productPrice)
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val addToCartImage : ImageView = itemView.findViewById(R.id.addToCart)
        fun bind(item: Product) {
            title.text = if (item.title.length > 18) item.title.substring(0, 18) + "..." else item.title
            price.text = item.price.toString() + "€"
            Glide.with(itemView.context).load(item.image).into(image)

            addToCartImage.setOnClickListener{
                listener.oncClickAddToCart(item)
            }

            itemView.setOnClickListener {
                listener.onClick(item)
            }
        }
    }


    interface OnClickListener {
        fun onClick(position: Product)
        fun oncClickAddToCart(position: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.products_layout, parent, false)
        return ViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = productList[position]
        holder.bind(item)

        fun onClick(position: Product) {
            holder.itemView.setOnClickListener {

            }
        }
    }
}