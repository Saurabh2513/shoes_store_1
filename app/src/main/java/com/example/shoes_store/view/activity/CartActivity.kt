package com.example.shoes_store.view.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoes_store.Helper.ChangeNumberItemsListener
import com.example.shoes_store.Helper.ManagmentCart
import com.example.shoes_store.databinding.ActivityCartBinding
import com.example.shoes_store.model.ItemModel
import com.example.shoes_store.view.adapter.CartAdapter

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var total: Double = 0.0
    private lateinit var adapter: CartAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        managmentCart = ManagmentCart(this)

        initViews()
        calculateCart()
        initCartList()
    }

    private fun initViews() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun initCartList() {
        binding.apply {
            viewCart.layoutManager =
                LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)

            viewCart.adapter = CartAdapter(managmentCart.getListCart(), this@CartActivity, object :
                ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }
            })
            emptyTxt.visibility =
                if (managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE

            scrollView2.visibility =
                if (managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 10
        total = 0.0
        val tax = Math.round((managmentCart.getTotalFee() * percentTax) * 100) / 100.0
        val total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100.0
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100.0

        with(binding) {
            totalFeeTxt.text = "Rs$itemTotal"
            taxTxt.text = "Rs$tax"
            deliverTxt.text = "Rs$delivery"
            totalTxt.text = "Rs$total"

        }

    }
}