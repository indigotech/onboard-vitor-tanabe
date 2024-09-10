package com.example.greetingcard.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.greetingcard.rest.UserRetrofitService
import retrofit2.await

class UserPagingSource(
    private val token: String
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val currentPage = params.key ?: 0

            val response = UserRetrofitService.userRetrofitService.loadUsers(token).await()
            val users = response.data.nodes

            LoadResult.Page(
                data = users,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (users.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
