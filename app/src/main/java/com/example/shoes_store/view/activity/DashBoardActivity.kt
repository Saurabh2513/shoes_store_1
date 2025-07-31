package com.example.shoes_store.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.shoes_store.adapter.BrandAdapter
import com.example.shoes_store.adapter.SliderAdapter
import com.example.shoes_store.databinding.ActivityMainBinding
import com.example.shoes_store.model.SliderModel
import com.example.shoes_store.viewmodel.MainViewModel

class DashBoardActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var binding: ActivityMainBinding
    private val brandAdapter = BrandAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

    }

    private fun initUI() {
        initBrand()
        intBanner()
    }

    private fun initBrand() {
        binding.recyclerViewBrand.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewBrand.adapter = brandAdapter
        binding.progressBarBrand.visibility = View.VISIBLE

        viewModel.brands.observe(this) { data ->
            brandAdapter.updateData(data)
            binding.progressBarBrand.visibility = View.GONE
        }
        viewModel.loadBrands()

    }
    private fun setUpBanner(image: List<SliderModel>) {
        binding.viewpagerSlider.apply {
            adapter = SliderAdapter(image, this)
            offscreenPageLimit = 3
            clipToPadding = false
            clipChildren = false
            (getChildAt(0) as? RecyclerView)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(
                CompositePageTransformer().apply {
                    addTransformer(MarginPageTransformer(40))
                }
            )
        }
        binding.dotIndicator.apply {
            visibility = if (image.size > 1) View.VISIBLE else View.GONE
            if (image.size > 1) attachTo(binding.viewpagerSlider)
        }
    }
    private fun intBanner(){
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this){
          item -> setUpBanner(item)
            binding.progressBarBanner.visibility = View.GONE
        }
        viewModel.loadBanners()

    }
}