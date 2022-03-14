package com.cherish.mynewtodoapp.data.remote

import com.cherish.mynewtodoapp.data.model.api.GetNewsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getNewByHeadLines(
        @Header("X-Api-Key")apiKey: String,
        @Query("country")country:String,
    ):GetNewsResponse





}