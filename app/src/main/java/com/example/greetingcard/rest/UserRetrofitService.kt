package com.example.greetingcard.rest

import com.example.greetingcard.model.AuthenticationRequestBody
import com.example.greetingcard.model.AuthenticationResponse
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.greetingcard.model.LoadListResponse
=======
>>>>>>> b2ba40f (list comming from repository)
=======
import com.example.greetingcard.model.LoadListResponse
>>>>>>> 38bd94d (feat:userList)
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
<<<<<<< HEAD
<<<<<<< HEAD
import retrofit2.http.GET
import retrofit2.http.Header
=======
>>>>>>> b2ba40f (list comming from repository)
=======
import retrofit2.http.GET
import retrofit2.http.Header
>>>>>>> 38bd94d (feat:userList)
import retrofit2.http.POST

interface UserRetrofitService {
    @POST("authenticate")
<<<<<<< HEAD
    suspend fun authenticateUser(@Body authenticationRequestBody: AuthenticationRequestBody): AuthenticationResponse

    @GET("users")
    suspend fun loadUsers(@Header("Authorization") token: String): LoadListResponse
=======
    fun authenticateUser(@Body authenticationRequestBody: AuthenticationRequestBody): Call<AuthenticationResponse>

<<<<<<< HEAD
    
>>>>>>> b2ba40f (list comming from repository)
=======
    @GET("users")
    fun loadUsers(@Header("Authorization") token: String): Call<LoadListResponse>
>>>>>>> 38bd94d (feat:userList)

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
<<<<<<< HEAD
=======


>>>>>>> b2ba40f (list comming from repository)
=======
>>>>>>> bfce5a7 (removing extra blank lines)
}