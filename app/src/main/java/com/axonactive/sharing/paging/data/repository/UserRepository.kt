package com.axonactive.sharing.paging.data.repository

import com.axonactive.sharing.paging.data.model.User
import com.axonactive.sharing.paging.data.service.Response
import com.axonactive.sharing.paging.data.service.UserService
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository {

    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }
    }

    private val api: UserService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        retrofit.create(UserService::class.java)
    }

    fun getUsers(page: Int, limits: Int): Single<Response<User>> {
        return api.getUsers(page, limits)
    }

    companion object {
        private const val BASE_URL = "https://randomuser.me/"
    }
}