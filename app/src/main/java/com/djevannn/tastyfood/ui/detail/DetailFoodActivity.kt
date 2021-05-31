package com.djevannn.tastyfood.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.djevannn.core.domain.model.Food
import com.djevannn.tastyfood.R
import com.djevannn.tastyfood.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFoodActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FOOD = "EXTRA_FOOD"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailFoodViewModel by viewModel()
    private lateinit var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.detailToolbar)
        supportActionBar?.title = ""
        setupToolbar()

        val detailFood = intent.getParcelableExtra<Food>(EXTRA_FOOD)
        showDetailFood(detailFood)

    }

    private fun showDetailFood(currentFood: Food?) {
        currentFood?.let {
            Glide.with(this@DetailFoodActivity)
                .load(currentFood.image)
                .into(binding.ivHero)

            binding.detailContent.apply {
                title = currentFood.title
                tvDetailTitle.text = title
                tvDetailCredit.text =
                    currentFood.sourceName
                tvDetailScore.text = resources.getString(
                    R.string.text_ratting,
                    currentFood.spoonacularScore.toString()
                )
                tvDetailMoney.text = resources.getString(
                    R.string.text_cost,
                    currentFood.pricePerServing.toString()
                )
                tvDetailTime.text = resources.getString(
                    R.string.text_time,
                    currentFood.readyInMinutes.toString()
                )
                tvDetailPeople.text = resources.getString(
                    R.string.text_servings,
                    currentFood.servings.toString()
                )
                tvDetailDescription.text = HtmlCompat.fromHtml(
                    currentFood.summary,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            }

            var statusFavorite = currentFood.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteFood(currentFood, statusFavorite)
                setStatusFavorite(statusFavorite)
                Snackbar.make(
                    binding.detailActivity,
                    if (statusFavorite) "Added $title to favorite" else "Removed $title from favorite",
                    Snackbar.LENGTH_LONG
                ).setAction("OK", null).show()
            }
        }
    }

    private fun setupToolbar() {
        binding.detailToolbar.apply {
            setNavigationOnClickListener { onBackPressed() }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }
}