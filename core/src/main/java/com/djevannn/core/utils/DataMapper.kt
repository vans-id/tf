package com.djevannn.core.utils

import com.djevannn.core.data.source.local.entity.FoodEntity
import com.djevannn.core.data.source.remote.response.FoodResponse
import com.djevannn.core.domain.model.Food

object DataMapper {

    fun mapResponsesToEntities(input: List<FoodResponse>): List<FoodEntity> =
        input.map {
            FoodEntity(
                title = it.title,
                readyInMinutes = it.readyInMinutes,
                servings = it.servings,
                id = it.id,
                summary = it.summary,
                image = it.image,
                spoonacularScore = it.spoonacularScore,
                pricePerServing = it.pricePerServing,
                sourceName = it.sourceName,
                isFavorite = false,
            )
        }

    fun mapEntitiesToDomain(input: List<FoodEntity>): List<Food> =
        input.map {
            Food(
                title = it.title,
                readyInMinutes = it.readyInMinutes,
                servings = it.servings,
                id = it.id,
                summary = it.summary,
                image = it.image,
                spoonacularScore = it.spoonacularScore,
                pricePerServing = it.pricePerServing,
                sourceName = it.sourceName,
                isFavorite = it.isFavorite,
            )
        }


    fun mapDomainToEntity(input: Food) = FoodEntity(
        title = input.title,
        readyInMinutes = input.readyInMinutes,
        servings = input.servings,
        id = input.id,
        summary = input.summary,
        image = input.image,
        spoonacularScore = input.spoonacularScore,
        pricePerServing = input.pricePerServing,
        sourceName = input.sourceName,
        isFavorite = input.isFavorite,
    )

}