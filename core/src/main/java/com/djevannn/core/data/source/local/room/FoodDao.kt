package com.djevannn.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.djevannn.core.data.source.local.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @RawQuery(observedEntities = [FoodEntity::class])
    fun getAllFood(query: SupportSQLiteQuery): Flow<List<FoodEntity>>

    @Query("SELECT * FROM food_entities where is_favorite = 1")
    fun getAllFavoriteFood(): Flow<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoods(foods: List<FoodEntity>)

    @Update
    fun updateFavoriteTourism(food: FoodEntity)
}