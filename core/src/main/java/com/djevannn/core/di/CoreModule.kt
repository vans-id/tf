package com.djevannn.core.di

import androidx.room.Room
import com.djevannn.core.data.FoodRepository
import com.djevannn.core.data.source.local.LocalDataSource
import com.djevannn.core.data.source.local.room.FoodDatabase
import com.djevannn.core.data.source.remote.RemoteDataSource
import com.djevannn.core.data.source.remote.network.ApiService
import com.djevannn.core.domain.repository.IFoodRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<FoodDatabase>().foodDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FoodDatabase::class.java,
            "Food.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/recipes/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IFoodRepository> {
        FoodRepository(
            get(),
            get(),
        )
    }
}