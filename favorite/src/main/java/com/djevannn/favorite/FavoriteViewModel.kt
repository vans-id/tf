package com.djevannn.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.djevannn.core.domain.usecase.FoodUseCase

class FavoriteViewModel(foodUseCase: FoodUseCase) : ViewModel() {

    val foods = foodUseCase.getFavoriteFoods().asLiveData()
}