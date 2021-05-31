package com.djevannn.core.data

import com.djevannn.core.data.source.local.LocalDataSource
import com.djevannn.core.data.source.remote.RemoteDataSource
import com.djevannn.core.data.source.remote.network.ApiResponse
import com.djevannn.core.data.source.remote.response.FoodResponse
import com.djevannn.core.domain.model.Food
import com.djevannn.core.domain.repository.IFoodRepository
import com.djevannn.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FoodRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IFoodRepository {

    override fun getAllFoods(query: String): Flow<Resource<List<Food>>> =
        object :
            NetworkBoundResource<List<Food>, List<FoodResponse>>() {
            override fun loadFromDB(): Flow<List<Food>> =
                localDataSource.getAllFood(query).map {
                    DataMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Food>?): Boolean =
                data == null || data.isEmpty() || query != "steak"

            override suspend fun createCall(): Flow<ApiResponse<List<FoodResponse>>> =
                remoteDataSource.getFoodList(query)

            override suspend fun saveCallResult(data: List<FoodResponse>) {
                val foodList =
                    DataMapper.mapResponsesToEntities(data)
                localDataSource.insertFoods(foodList)
            }

        }.asFlow()

    override fun getAllFavoriteFoods(): Flow<List<Food>> =
        localDataSource.getAllFavoriteFood().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteFood(food: Food, state: Boolean) {
        val foodEntity = DataMapper.mapDomainToEntity(food)
//        withContext(Dispatchers.IO) {
//            localDataSource.updateFavoriteTourism(foodEntity, state)
//        }
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.updateFavoriteTourism(foodEntity, state)
        }
//        appExecutors.diskIO().execute {
//            localDataSource.updateFavoriteTourism(foodEntity, state)
//        }
    }

}