package com.djevannn.core.data.source.remote

import android.util.Log
import com.djevannn.core.data.source.remote.network.ApiResponse
import com.djevannn.core.data.source.remote.network.ApiService
import com.djevannn.core.data.source.remote.response.FoodResponse
import com.djevannn.core.utils.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getFoodList(query: String): Flow<ApiResponse<List<FoodResponse>>> {
        return flow {
            try {
                val response = apiService.getFoodsList(
                    ApiConfig.API_KEY,
                    query,
                    true,
                    10
                )
                val dataArray = response.results

                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                    Log.e("RemoteDataSource", "Empty")
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}