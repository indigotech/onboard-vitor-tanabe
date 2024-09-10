package com.example.greetingcard.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.greetingcard.model.AuthenticationRequestBody
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.greetingcard.model.UserListItem
=======
import com.example.greetingcard.model.User
import com.example.greetingcard.model.UserPagingSource
>>>>>>> 986e103 (pagination)
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
=======
import com.example.greetingcard.rest.UserAuthenticationRetrofitService
=======
import com.example.greetingcard.model.User
import com.example.greetingcard.rest.UserRetrofitService
>>>>>>> b2ba40f (list comming from repository)
import retrofit2.await

class UserRepository private constructor() {

<<<<<<< HEAD
<<<<<<< HEAD
    private var token: String? = null
>>>>>>> a2e96cb (creating repository to pass token)
=======
     var token: String? = null
>>>>>>> b2ba40f (list comming from repository)
=======
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
>>>>>>> 38bd94d (feat:userList)

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

<<<<<<< HEAD
    suspend fun loadUsers(): Result<List<User>> {
        return try {
<<<<<<< HEAD
            val users = mutableListOf<User>()

            users.add(User("João", "joao@email.com"))
            users.add(User("Maria", "maria@email.com"))
            users.add(User("Carlos", "carlos@email.com"))

>>>>>>> b2ba40f (list comming from repository)
=======
            val loadUserResponse = UserRetrofitService.userRetrofitService.loadUsers(token).await()
            val users = loadUserResponse.data.nodes
>>>>>>> 38bd94d (feat:userList)
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> a2e96cb (creating repository to pass token)
=======
}
>>>>>>> 38bd94d (feat:userList)
=======
    suspend fun loadUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserPagingSource(token) }
        ).flow
    }
<<<<<<< HEAD

//    suspend fun loadUsers(): Result<List<User>> {
//        return try {
//            val loadUserResponse = UserRetrofitService.userRetrofitService.loadUsers(token).await()
//            val users = loadUserResponse.data.nodes
//            Result.success(users)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }

}
>>>>>>> 986e103 (pagination)
=======
}
>>>>>>> 05807af (removing extra lines and comentaries)
