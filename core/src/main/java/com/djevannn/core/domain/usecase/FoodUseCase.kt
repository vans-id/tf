package com.djevannn.core.domain.usecase

import com.djevannn.core.data.Resource
import com.djevannn.core.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodUseCase {

    fun getAllFoods(query: String): Flow<Resource<List<Food>>>

    fun getFavoriteFoods(): Flow<List<Food>>

    fun setFavoriteFood(food: Food, state: Boolean)

}