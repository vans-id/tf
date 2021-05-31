package com.djevannn.tastyfood.di

import com.djevannn.core.domain.usecase.FoodInteractor
import com.djevannn.core.domain.usecase.FoodUseCase
import com.djevannn.tastyfood.ui.detail.DetailFoodViewModel
import com.djevannn.tastyfood.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FoodUseCase> { FoodInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailFoodViewModel(get()) }
}