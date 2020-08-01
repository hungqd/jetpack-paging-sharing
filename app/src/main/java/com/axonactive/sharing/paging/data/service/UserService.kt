package com.axonactive.sharing.paging.data.service

import com.axonactive.sharing.paging.data.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("api")
    suspend fun getUsers(
        @Query("page")
        page: Int,
        @Query("results")
        results: Int
    ): Response<User>
}