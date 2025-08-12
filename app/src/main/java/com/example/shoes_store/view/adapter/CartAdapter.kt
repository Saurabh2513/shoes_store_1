package com.example.shoes_store.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.shoes_store.Helper.ChangeNumberItemsListener
import com.example.shoes_store.Helper.ManagmentCart
import com.example.shoes_store.databinding.ViewholderCartBinding

import com.example.shoes_store.model.ItemModel

class CartAdapter(
    private val listItemSelected: ArrayList<ItemModel>, context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener
) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private val managmentCart = ManagmentCart(context)

    inner class ViewHolder(val binding: ViewholderCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartAdapter.ViewHolder {
        val binding =
            ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val item = listItemSelected[position]
        holder.binding.titleTxt.text = item.title
        holder.binding.feeEachItemTxt.text = "Rs${item.price}"
        holder.binding.totalEachItem.text = "Rs${Math.round(item.numberInCrate * item.price)}"
        holder.binding.numberItemTxt.text = item.numberInCrate.toString()
        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.pic)
        holder.binding.plusBtn.setOnClickListener {
            managmentCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                     notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }
        holder.binding.minusBtn.setOnClickListener {
            managmentCart.minusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }
    }

    override fun getItemCount(): Int = listItemSelected.size

}