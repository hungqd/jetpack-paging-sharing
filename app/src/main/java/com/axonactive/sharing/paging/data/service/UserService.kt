package com.axonactive.sharing.paging.data.service

import com.axonactive.sharing.paging.data.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("api")
    fun getUsers(
        @Query("page")
        page: Int,
        @Query("results")
        results: Int
    ): Single<Response<User>>
}