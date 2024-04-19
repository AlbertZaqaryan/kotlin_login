package com.example.loginregister.DataModel

import com.google.gson.annotations.SerializedName

data class KotlinUser(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
