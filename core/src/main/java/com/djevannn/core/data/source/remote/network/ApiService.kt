package com.djevannn.core.data.source.remote.network

import com.djevannn.core.data.source.remote.response.ListFoodResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("complexSearch")
    suspend fun getFoodsList(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String,
        @Query("addRecipeInformation") addRecipeInformation: Boolean,
        @Query("number") number: Int,
    ): ListFoodResponse

}