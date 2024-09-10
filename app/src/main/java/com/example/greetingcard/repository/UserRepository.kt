package com.example.greetingcard.repository

import com.example.greetingcard.model.AuthenticationRequestBody
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.greetingcard.model.UserListItem
import com.example.greetingcard.rest.UserRetrofitService
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
=======
import com.example.greetingcard.rest.UserAuthenticationRetrofitService
=======
import com.example.greetingcard.model.User
import com.example.greetingcard.rest.UserRetrofitService
>>>>>>> b2ba40f (list comming from repository)
import retrofit2.await

class UserRepository {

<<<<<<< HEAD
    private var token: String? = null
>>>>>>> a2e96cb (creating repository to pass token)
=======
     var token: String? = null
>>>>>>> b2ba40f (list comming from repository)

    suspend fun authenticateUser(email: String, password: String): Result<String> {
        return try {
            val user = AuthenticationRequestBody(email, password)
<<<<<<< HEAD
<<<<<<< HEAD
            val response = UserRetrofitService
                .userRetrofitService
                .authenticateUser(user)
=======
            val response = UserAuthenticationRetrofitService
                .userAuthenticationRetrofitService
                .authenticateUser(user).await()

>>>>>>> a2e96cb (creating repository to pass token)
=======
            val response = UserRetrofitService
                .userRetrofitService
                .authenticateUser(user).await()
>>>>>>> b2ba40f (list comming from repository)
            val authToken = response.data.token
            token = authToken
            Result.success(authToken)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
<<<<<<< HEAD
<<<<<<< HEAD

    suspend fun loadUsers(): Result<List<UserListItem>> {
        return try {
            val loadUserResponse = UserRetrofitService.userRetrofitService.loadUsers(token)
            val users = loadUserResponse.data.nodes
=======

    suspend fun loadUsers(): Result<List<User>> {
        return try {
            val users = mutableListOf<User>()

            users.add(User("João", "joao@email.com"))
            users.add(User("Maria", "maria@email.com"))
            users.add(User("Carlos", "carlos@email.com"))

>>>>>>> b2ba40f (list comming from repository)
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
=======
}
>>>>>>> a2e96cb (creating repository to pass token)
