package com.example.shoes_store.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoes_store.R
import com.example.shoes_store.databinding.ViewholderBrandBinding
import com.example.shoes_store.model.BrandModel

class BrandAdapter(private val items: MutableList<BrandModel>) :
    RecyclerView.Adapter<BrandAdapter.ViewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    fun updateData(newData: List<BrandModel>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        val binding: ViewholderBrandBinding
    ) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.ViewHolder {
        val binding =
            ViewholderBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandAdapter.ViewHolder, position: Int) {
        val item = items[position]

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        holder.binding.pic.setOnClickListener {
            if (selectedPosition != position) {
                lastSelectedPosition = selectedPosition
                selectedPosition = position
                if (selectedPosition != -1)
                    notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }
            val idSelect = selectedPosition == position
            holder.binding.pic.setBackgroundResource(
                if (idSelect) 0 else R.drawable.grey_full_corner
            )
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
               ColorStateList.valueOf(
                   holder.itemView.context.getColor(
                       if (idSelect) R.color.indigo else R.color.violet
                   )
               )
            )
        }

    }


    override fun getItemCount() = items.size
}