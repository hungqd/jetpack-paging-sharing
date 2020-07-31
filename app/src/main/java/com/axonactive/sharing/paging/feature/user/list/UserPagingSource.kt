package com.axonactive.sharing.paging.feature.user.list

import androidx.paging.rxjava2.RxPagingSource
import com.axonactive.sharing.paging.data.model.User
import com.axonactive.sharing.paging.data.repository.UserRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(private val repo: UserRepository) : RxPagingSource<Int, User>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, User>> {
        val nextPagePage = params.key ?: BASE_INDEX
        val prevKey = if (nextPagePage == BASE_INDEX) null else nextPagePage - 1
        val nextKey = nextPagePage + 1
        return repo.getUsers(nextPagePage, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map<LoadResult<Int, User>> { resp ->
                LoadResult.Page(
                    data = resp.results,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            .onErrorReturn { e ->
                when (e) {
                    is IOException -> LoadResult.Error(e)
                    is HttpException -> LoadResult.Error(e)
                    else -> throw e
                }
            }
    }

    companion object {
        private const val BASE_INDEX = 1
    }
}