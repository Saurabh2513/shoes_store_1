package com.example.shoes_store.adapter




import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoes_store.R
import com.example.shoes_store.databinding.ViewholderSizeBinding

class SizeAdapter(private val items: MutableList<String>) :
    RecyclerView.Adapter<SizeAdapter.ViewHolder>() {
    private var selectedPosition = -1
    private var LastSelectedPosition = -1


    inner class ViewHolder(val binding: ViewholderSizeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ViewholderSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.sizeTxt.text = items[position]
        holder.binding.root.setOnClickListener {
            LastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(LastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if (selectedPosition == position) {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.color_bg)
            holder.binding.sizeTxt.setTextColor(holder.itemView.context.resources.getColor(android.R.color.white))
        }
        else {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.stroke_bg_blue)
            holder.binding.sizeTxt.setTextColor(holder.itemView.context.resources.getColor(android.R.color.black))
        }
    }

    override fun getItemCount(): Int = items.size
}