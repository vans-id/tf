package com.djevannn.core.domain.usecase

import com.djevannn.core.data.Resource
import com.djevannn.core.domain.model.Food
import com.djevannn.core.domain.repository.IFoodRepository
import kotlinx.coroutines.flow.Flow

class FoodInteractor(private val foodRepository: IFoodRepository) :
    FoodUseCase {

    override fun getAllFoods(query: String): Flow<Resource<List<Food>>> =
        foodRepository.getAllFoods(query)

    override fun getFavoriteFoods(): Flow<List<Food>> =
        foodRepository.getAllFavoriteFoods()

    override fun setFavoriteFood(
        food: Food,
        state: Boolean
    ) = foodRepository.setFavoriteFood(food, state)


}