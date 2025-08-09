package com.example.shoes_store.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shoes_store.Helper.ManagmentCart
import com.example.shoes_store.R
import com.example.shoes_store.databinding.ActivityCartBinding
import com.example.shoes_store.model.ItemModel
import com.example.shoes_store.view.adapter.CartAdapter

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private lateinit var adapter: CartAdapter
    private val cartList = ArrayList<ItemModel>()
    private var tax: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        managmentCart = ManagmentCart(this)

        initViews()
    }

    private fun initViews() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
    private fun initCart() {
        cartList.clear()

    }
}