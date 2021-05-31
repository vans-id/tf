package com.djevannn.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListFoodResponse(
	@field:SerializedName("results")
	val results: List<FoodResponse>
)

data class FoodResponse(

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("readyInMinutes")
	val readyInMinutes: Int,

	@field:SerializedName("servings")
	val servings: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("summary")
	val summary: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("spoonacularScore")
	val spoonacularScore: Double,

	@field:SerializedName("pricePerServing")
	val pricePerServing: Double,

	@field:SerializedName("sourceName")
	val sourceName: String,
)
