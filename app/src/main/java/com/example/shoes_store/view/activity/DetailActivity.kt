package com.example.shoes_store.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.shoes_store.Helper.ManagmentCart
import com.example.shoes_store.databinding.ActivityDetailBinding
import com.example.shoes_store.model.ItemModel


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemModel
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managmentCart = ManagmentCart(this)
        item = intent.getSerializableExtra("object")!! as ItemModel
        setupViews()
    }

    private fun setupViews() = with(binding) {
        titleTxt.text = item.title
        descriptionTxt.text = item.description
        priceTxt.text = "RS:${item.price}"
        oldPrice.text = "RS:${item.oldPrice}"
        oldPrice.paintFlags = oldPrice.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
        ratingTxt.text = "${item.rating} Rating"
        numberItemTxt.text = item.numberInCrate.toString()
        updateTotalPrice()
        Glide.with(this@DetailActivity)
            .load(item.picUrl.firstOrNull())
            .into(picMain)
        // back button
        backBtn.setOnClickListener {
            finish()
        }
       plusBtn.setOnClickListener {
           item.numberInCrate++
           numberItemTxt.text = item.numberInCrate.toString()
           updateTotalPrice()
       }
       minusBtn.setOnClickListener {
           if (item.numberInCrate > 1) {
               item.numberInCrate--
               numberItemTxt.text = item.numberInCrate.toString()
               updateTotalPrice()
           }
       }
        addToCartBtn.setOnClickListener {
            managmentCart.insertItem(item)
        }
//        favBtn.setOnClickListener {
//            item. = !item.isFav
//        }
    }

    private fun updateTotalPrice() = with(binding) {
        totalPriceTxt.text = "RS:${item.price * item.numberInCrate}"
    }
}