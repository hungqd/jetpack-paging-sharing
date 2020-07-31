package com.axonactive.sharing.paging.data.service

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("results")
    val results: List<T>,
    @SerializedName("info")
    val info: Info
) {

    data class Info(
        @SerializedName("seed")
        val seed: String,
        @SerializedName("results")
        val results: Int,
        @SerializedName("page")
        val page: Int,
        @SerializedName("version")
        val version: String
    )
}