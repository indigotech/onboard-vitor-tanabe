package com.example.greetingcard.rest

import com.example.greetingcard.model.LoadListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface LoadUserListRetrofitService {

    @GET
    fun loadUserList(@Header("Authorization") token: String): Call<LoadListResponse>

    companion object RetrofitInstance {

        private const val BASE_URL = "https://template-onboarding-node-sjz6wnaoia-uc.a.run.app/"

        val loadUserListRetrofitService: LoadUserListRetrofitService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoadUserListRetrofitService::class.java)
        }
    }

    fun getInstance(): LoadUserListRetrofitService {
        return loadUserListRetrofitService
    }
}