package com.example.greetingcard.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.greetingcard.model.AuthenticationRequestBody
import com.example.greetingcard.model.NewUserRequest
import com.example.greetingcard.model.User
import com.example.greetingcard.model.UserPagingSource
import com.example.greetingcard.rest.UserRetrofitService
import kotlinx.coroutines.flow.Flow
import retrofit2.await

class UserRepository private constructor() {

    var token: String = ""

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(): UserRepository {
            return instance ?: synchronized(this) {
                instance ?: UserRepository().also { instance = it }
            }
        }
    }

    suspend fun authenticateUser(email: String, password: String): Result<String> {
        return try {
            val user = AuthenticationRequestBody(email, password)
            val response = UserRetrofitService
                .userRetrofitService
                .authenticateUser(user)
            val authToken = response.data.token
            token = authToken
            Result.success(authToken)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun loadUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserPagingSource(token) }
        ).flow
    }

    suspend fun newUser(newUserRequest: NewUserRequest) {
        try {
            UserRetrofitService.userRetrofitService.newUser(token, newUserRequest)
        } catch (e: Exception) {
            throw Exception("Erro ao cadastrar usuário")
        }
    }
}