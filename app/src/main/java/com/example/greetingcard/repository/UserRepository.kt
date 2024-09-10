package com.example.greetingcard.repository

import com.example.greetingcard.model.AuthenticationRequestBody
import com.example.greetingcard.model.User
import com.example.greetingcard.rest.UserRetrofitService
import retrofit2.await

class UserRepository {

     var token: String? = null

    suspend fun authenticateUser(email: String, password: String): Result<String> {
        return try {
            val user = AuthenticationRequestBody(email, password)
            val response = UserRetrofitService
                .userRetrofitService
                .authenticateUser(user).await()
            val authToken = response.data.token
            token = authToken
            Result.success(authToken)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loadUsers(): Result<List<User>> {
        return try {
            val users = mutableListOf<User>()

            users.add(User("João", "joao@email.com"))
            users.add(User("Maria", "maria@email.com"))
            users.add(User("Carlos", "carlos@email.com"))

            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
