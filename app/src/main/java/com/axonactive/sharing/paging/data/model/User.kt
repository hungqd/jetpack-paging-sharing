package com.axonactive.sharing.paging.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: Name,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("picture")
    val picture: Map<String, String>
) {
    data class Name(
        @SerializedName("title")
        val title: String,
        @SerializedName("first")
        val first: String,
        @SerializedName("last")
        val last: String
    )
}