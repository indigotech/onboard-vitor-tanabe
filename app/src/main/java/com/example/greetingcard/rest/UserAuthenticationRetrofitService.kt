package com.example.greetingcard.rest

import com.example.greetingcard.model.AuthenticationRequestBody
import com.example.greetingcard.model.AuthenticationResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthenticationRetrofitService {
    @POST("authenticate")
    fun authenticateUser(@Body authenticationRequestBody: AuthenticationRequestBody): Call<AuthenticationResponse>

    companion object RetrofitInstance {

        private const val BASE_URL = "https://template-onboarding-node-sjz6wnaoia-uc.a.run.app/"

        val userAuthenticationRetrofitService: UserAuthenticationRetrofitService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserAuthenticationRetrofitService::class.java)
        }
    }

    fun getInstance(): UserAuthenticationRetrofitService {
        return userAuthenticationRetrofitService
    }

}
