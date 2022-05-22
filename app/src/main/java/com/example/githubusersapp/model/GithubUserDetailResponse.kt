package com.example.githubusersapp.model

import java.io.Serializable

data class GithubUserDetailResponse (
        val login : String,
        val avatar_url : String,
        val blog : String,
        val company : String,
        val created_at : String,
        val updated_at : String,
        val events_url : String,
        val followers : Int,
        val followers_url : String,
        val following : Int,
        val following_url : String,
        val gists_url : String,
        val html_url : String,
        val id : Int,
        val location : String,
        val name : String,
        val node_id : String,
        val organizations_url : String,
        val public_gists : Int,
        val public_repos : Int,
        val received_events_url : String,
        val repos_url : String,
        val site_admin : Boolean,
        val starredUrl : String,
        val subscriptionsUrl : String,
        val type : String,
        ): Serializable