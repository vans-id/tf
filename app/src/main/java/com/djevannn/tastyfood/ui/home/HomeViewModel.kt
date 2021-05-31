package com.djevannn.tastyfood.ui.home

import androidx.lifecycle.*
import com.djevannn.core.data.Resource
import com.djevannn.core.domain.model.Food
import com.djevannn.core.domain.usecase.FoodUseCase

class HomeViewModel(private val foodUseCase: FoodUseCase) :
    ViewModel() {

    private val query = MutableLiveData<String>()

    fun setQuery(query: String) {
        this.query.value = query
    }

    var foods: LiveData<Resource<List<Food>>> =
        Transformations.switchMap(query)
        { mQuery ->
            foodUseCase.getAllFoods(mQuery).asLiveData()
        }
}