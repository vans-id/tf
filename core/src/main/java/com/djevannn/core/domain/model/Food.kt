package com.djevannn.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val title: String,
    val readyInMinutes: Int,
    val servings: Int,
    val id: Int,
    val summary: String,
    val image: String,
    val spoonacularScore: Double,
    val pricePerServing: Double,
    val sourceName: String,
    var isFavorite: Boolean = false
) : Parcelable