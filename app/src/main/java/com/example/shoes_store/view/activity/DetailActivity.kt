package com.example.shoes_store.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.shoes_store.Helper.ManagmentCart
import com.example.shoes_store.adapter.ColorAdapter
import com.example.shoes_store.adapter.PicsAdapter
import com.example.shoes_store.adapter.SizeAdapter
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
        setUpPicList()
        setUpColorList()
        setUpSizeList()
    }

    private fun setUpSizeList() {
        val sizeList = item.size.map { it }
        binding.sizeList.apply {
            adapter = SizeAdapter(sizeList as MutableList<String>)
            layoutManager =
                LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setUpColorList() {

        binding.colorList.adapter = ColorAdapter(item.color)
        binding.colorList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun setUpPicList() {
        val picList = item.picUrl.toList()
        binding.picList.apply {
            adapter = PicsAdapter(picList as MutableList<String>) { imageUrl ->
                Glide.with(this@DetailActivity)
                    .load(imageUrl)
                    .into(binding.picMain)
            }
            layoutManager =
                LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)

        }
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