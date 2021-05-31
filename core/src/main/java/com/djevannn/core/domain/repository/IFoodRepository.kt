package com.djevannn.core.domain.repository

import com.djevannn.core.data.Resource
import com.djevannn.core.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface IFoodRepository {

    fun getAllFoods(query: String): Flow<Resource<List<Food>>>

    fun getAllFavoriteFoods(): Flow<List<Food>>

    fun setFavoriteFood(food: Food, state: Boolean)

}