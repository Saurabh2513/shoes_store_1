package com.example.shoes_store.adapter

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.shoes_store.databinding.ViewholderColorBinding

class ColorAdapter(
    private val items: ArrayList<String>
) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {
    private var selectedPosition = -1
    private var LastSelectedPosition = -1

    inner class ViewHolder(val binding: ViewholderColorBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ViewholderColorBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val color = items[position].toColorInt()
        holder.binding.colorCircle.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        holder.binding.strokeView.visibility = if (selectedPosition == position) {
            View.VISIBLE
        } else {
            View.GONE
        }
        holder.binding.root.setOnClickListener {
           if(selectedPosition != position){
               LastSelectedPosition = selectedPosition
               selectedPosition = position
              if (LastSelectedPosition!=-1) notifyItemChanged(LastSelectedPosition)
               notifyItemChanged(selectedPosition)
           }

        }

    }

    override fun getItemCount(): Int = items.size
}