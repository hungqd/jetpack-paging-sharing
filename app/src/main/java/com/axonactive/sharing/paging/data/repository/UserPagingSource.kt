package com.axonactive.sharing.paging.data.repository

import androidx.paging.PagingSource
import com.axonactive.sharing.paging.data.model.User

class UserPagingSource(private val repo: UserRepository) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val nextPagePage = params.key ?: BASE_INDEX
        val prevKey = if (nextPagePage == BASE_INDEX) null else nextPagePage - 1
        val nextKey = nextPagePage + 1
        return try {
            val resp = repo.getUsers(nextPagePage, params.loadSize)
            LoadResult.Page(data = resp.results, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val BASE_INDEX = 1
    }
}