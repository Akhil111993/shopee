package com.griitingz.shopee.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.griitingz.shopee.R
import com.griitingz.shopee.models.DataX
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class ProductAdapter(private val items: List<DataX>, private val isShop: Boolean) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        if (isShop) {
            holder.oldPrice.text = ""
            holder.qr.text = ""
            Picasso.get().load(currentItem.shop_logo).into(holder.image)
        } else {
            holder.qr.text = "QR"
            Picasso.get().load(currentItem.photo).into(holder.image)
            holder.price.text = currentItem.price
            holder.oldPrice.text = "QR ${currentItem.old_price}"
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.product_image)
        val price: TextView = itemView.findViewById(R.id.product_price)
        val oldPrice: TextView = itemView.findViewById(R.id.product_old_price)
        val qr: TextView = itemView.findViewById(R.id.qr_label)
    }
}
