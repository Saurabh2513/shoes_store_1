package com.example.shoes_store.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.shoes_store.R
import com.example.shoes_store.model.SliderModel

class SliderAdapter(
    private var sliderItem: List<SliderModel>,
    private val viewPager2: ViewPager2,

    ) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView = itemView.findViewById<ImageView>(R.id.imageSlider)
        fun setImage(sliderItem: SliderModel, context: Context) {
            val requestOptions = RequestOptions().transform(CenterInside())
            Glide.with(context).load(sliderItem.url).apply(requestOptions).into(imageView)

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SliderAdapter.SliderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_item_viewholder, parent, false)

        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(sliderItem[position], holder.itemView.context)
        if (position == sliderItem.size - 1) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount() = sliderItem.size

    private val runnable = Runnable {
        sliderItem = sliderItem + sliderItem
        notifyDataSetChanged()
    }
}