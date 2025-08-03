package com.example.shoes_store.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoes_store.databinding.ViewholderPicsBinding

class PicsAdapter(val items: MutableList<String>, private val onImageSelected: (String) -> Unit) :
    RecyclerView.Adapter<PicsAdapter.ViewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    inner class ViewHolder(val binding: ViewholderPicsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PicsAdapter.ViewHolder {
        context = parent.context
        val binding =
            ViewholderPicsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PicsAdapter.ViewHolder, position: Int) {
        val item = items[position]
        Glide.with(context)
            .load(items[position])
            .into(holder.binding.pic)
        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
            onImageSelected(items[position])
        }
        if (selectedPosition == position) {
            holder.binding.colorLayout.setBackgroundColor(context.getColor(android.R.color.holo_green_light))
        }
        else {
            holder.binding.colorLayout.setBackgroundColor(context.getColor(android.R.color.white))
        }

    }

    override fun getItemCount(): Int = items.size


}