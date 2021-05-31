package com.djevannn.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_entities")
data class FoodEntity(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "ready_in_minutes")
    val readyInMinutes: Int,

    @ColumnInfo(name = "servings")
    val servings: Int,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "summary")
    val summary: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "spoonacular_score")
    val spoonacularScore: Double,

    @ColumnInfo(name = "price_per_serving")
    val pricePerServing: Double,

    @ColumnInfo(name = "source_name")
    val sourceName: String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)