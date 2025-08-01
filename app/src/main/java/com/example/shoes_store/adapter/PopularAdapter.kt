package com.example.shoes_store.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.shoes_store.databinding.ViewholderRecommendedBinding
import com.example.shoes_store.model.ItemModel
import com.example.shoes_store.view.activity.DetailActivity

class PopularAdapter(private val items: MutableList<ItemModel>) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    fun updateData(newData: List<ItemModel>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ViewholderRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.ViewHolder {
        val binding =
            ViewholderRecommendedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            titleTxt.text = item.title
            priceTxt.text = "RS:${item.price}"
            ratingTxt.text = item.rating.toString()
            Glide.with(holder.itemView.context)
                .load(item.picUrl.firstOrNull())
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                .into(picrec)


            root.setOnClickListener {
                val intent = Intent(holder.itemView.context, DetailActivity::class.java)
                intent.putExtra("object", item)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = items.size


}