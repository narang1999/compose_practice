package com.example.composebasics.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    val baseUrl= "https://api.stackexchange.com/2.2/"
    val api :MovieApiService by lazy{
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build().create(MovieApiService::class.java)
    }
}