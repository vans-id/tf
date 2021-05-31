package com.djevannn.core.data.source.local

import com.djevannn.core.data.source.local.entity.FoodEntity
import com.djevannn.core.data.source.local.room.FoodDao
import com.djevannn.core.utils.SearchUtils
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val foodDao: FoodDao) {

    fun getAllFood(query: String): Flow<List<FoodEntity>> {
        val sqlQuery = SearchUtils.getSortedQuery(query)
        return foodDao.getAllFood(sqlQuery)
    }

    fun getAllFavoriteFood(): Flow<List<FoodEntity>> =
        foodDao.getAllFavoriteFood()

    suspend fun insertFoods(foods: List<FoodEntity>) =
        foodDao.insertFoods(foods)

    fun updateFavoriteTourism(
        food: FoodEntity,
        newState: Boolean
    ) {
        food.isFavorite = newState
        foodDao.updateFavoriteTourism(food)
    }

}