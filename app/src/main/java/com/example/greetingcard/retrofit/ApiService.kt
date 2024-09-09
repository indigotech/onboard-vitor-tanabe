package com.example.greetingcard.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("authenticate")
    fun authenticateUser(@Body authenticationRequestBody: AuthenticationRequestBody): Call<AuthenticationResponse>
}
