package com.djevannn.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.djevannn.core.ui.FoodAdapter
import com.djevannn.favorite.databinding.ActivityFavoriteBinding
import com.djevannn.tastyfood.ui.detail.DetailFoodActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        setupToolbar()
        loadFavoriteData()
    }

    private fun loadFavoriteData() {
        val foodAdapter = FoodAdapter()
        foodAdapter.onItemClick = { selectedData ->
            val intent =
                Intent(
                    this@FavoriteActivity,
                    DetailFoodActivity::class.java
                )
            intent.putExtra(
                DetailFoodActivity.EXTRA_FOOD,
                selectedData
            )
            startActivity(intent)
        }

        viewModel.foods.observe(this, { foods ->
            binding.progressBar.visibility =
                if (foods.isNotEmpty()) View.GONE else View.VISIBLE
            foodAdapter.setData(foods)
        })

        with(binding.rvFoods) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = foodAdapter
        }
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            setNavigationOnClickListener { onBackPressed() }
        }
        binding.tvAppTitle.text =
            getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
    }
}