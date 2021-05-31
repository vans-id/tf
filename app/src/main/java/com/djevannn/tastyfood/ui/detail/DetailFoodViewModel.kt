package com.djevannn.tastyfood.ui.detail

import androidx.lifecycle.ViewModel
import com.djevannn.core.domain.model.Food
import com.djevannn.core.domain.usecase.FoodUseCase

class DetailFoodViewModel(private val foodUseCase: FoodUseCase) :
    ViewModel() {

    fun setFavoriteFood(food: Food, newStatus: Boolean) =
        foodUseCase.setFavoriteFood(food, newStatus)

}