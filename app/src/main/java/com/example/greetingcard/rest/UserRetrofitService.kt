package com.example.greetingcard.rest

import com.example.greetingcard.model.AuthenticationRequestBody
import com.example.greetingcard.model.AuthenticationResponse
import com.example.greetingcard.model.LoadListResponse
import com.example.greetingcard.model.LoadUserResponse
import com.example.greetingcard.model.NewUserRequest
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserRetrofitService {
    @POST("authenticate")
    suspend fun authenticateUser(@Body authenticationRequestBody: AuthenticationRequestBody): AuthenticationResponse

    @GET("users")
    suspend fun loadUsers(@Header("Authorization") token: String, @Query("offset") offset: Int): LoadListResponse

    @POST("users")
    suspend fun newUser(@Header("Authorization") token: String, @Body newUserRequest: NewUserRequest)

    @GET("users/{id}")
    suspend fun loadUserDetail(@Header("Authorization") token: String, @Path("id") userId: String): LoadUserResponse
    companion object RetrofitInstance {

        private const val BASE_URL = "https://template-onboarding-node-sjz6wnaoia-uc.a.run.app/"

        val userRetrofitService: UserRetrofitService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserRetrofitService::class.java)
        }
    }
}