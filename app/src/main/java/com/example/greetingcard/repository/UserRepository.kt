package com.example.greetingcard.repository

import com.example.greetingcard.model.AuthenticationRequestBody
import com.example.greetingcard.rest.UserAuthenticationRetrofitService
import retrofit2.await

class UserRepository {

    private var token: String? = null

    suspend fun authenticateUser(email: String, password: String): Result<String> {
        return try {
            val user = AuthenticationRequestBody(email, password)
            val response = UserAuthenticationRetrofitService
                .userAuthenticationRetrofitService
                .authenticateUser(user).await()

            val authToken = response.data.token
            token = authToken
            Result.success(authToken)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
