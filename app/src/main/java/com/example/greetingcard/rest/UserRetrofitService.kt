package com.example.greetingcard.rest

import com.example.greetingcard.model.AuthenticationRequestBody
import com.example.greetingcard.model.AuthenticationResponse
<<<<<<< HEAD
import com.example.greetingcard.model.LoadListResponse
=======
>>>>>>> b2ba40f (list comming from repository)
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
<<<<<<< HEAD
import retrofit2.http.GET
import retrofit2.http.Header
=======
>>>>>>> b2ba40f (list comming from repository)
import retrofit2.http.POST

interface UserRetrofitService {
    @POST("authenticate")
<<<<<<< HEAD
    suspend fun authenticateUser(@Body authenticationRequestBody: AuthenticationRequestBody): AuthenticationResponse

    @GET("users")
    suspend fun loadUsers(@Header("Authorization") token: String): LoadListResponse
=======
    fun authenticateUser(@Body authenticationRequestBody: AuthenticationRequestBody): Call<AuthenticationResponse>

    
>>>>>>> b2ba40f (list comming from repository)

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
<<<<<<< HEAD
=======


>>>>>>> b2ba40f (list comming from repository)
}