package com.example.githubusersapp.api

import com.example.githubusersapp.model.GithubUser
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("users")
    fun getUsers(): Call<List<GithubUser>>
}
