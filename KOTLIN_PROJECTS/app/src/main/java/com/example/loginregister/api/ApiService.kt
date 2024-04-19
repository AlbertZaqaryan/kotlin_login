package com.example.loginregister.api

import com.example.loginregister.DataModel.KotlinUser
import retrofit2.Call
import retrofit2.http.Body

import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("kotlin_user/") // Endpoint for fetching humans
    fun getUsers(): Call<List<KotlinUser>>

    @POST("kotlin_user/")
    fun createUsers(@Body user: KotlinUser): Call<KotlinUser>
    }

