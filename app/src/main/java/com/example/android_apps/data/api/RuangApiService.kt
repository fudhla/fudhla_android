package com.example.android_apps.data.api

import com.example.android_apps.data.model.RuangModel
import com.example.android_apps.data.model.StatusRuangModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RuangApiService {
    @GET("status-ruang")
    suspend fun getStatusRuang(): StatusRuangModel

    @GET("v2/list")
    suspend fun getDaftarRuang(): List<RuangModel>
}

object RuangApiClient {
    private const val BASE_URL = "https://picsum.photos/"

    val apiService: RuangApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RuangApiService::class.java)
    }
}