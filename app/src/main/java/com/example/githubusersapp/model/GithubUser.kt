package com.example.githubusersapp.model

import java.io.Serializable

data class GithubUser (
    val login: String,
    val avatar_url: String,
    val id: Int,
    val type: String
    ): Serializable