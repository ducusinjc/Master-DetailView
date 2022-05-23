package com.example.githubusersapp.main

import android.util.Log
import androidx.lifecycle.*
import com.example.githubusersapp.api.Api
import com.example.githubusersapp.model.GithubUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private lateinit var githubUsers: List<GithubUser>
    var listUsers = MutableLiveData<List<GithubUser>>()

    fun setSearchUsers() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiInstance: Api = retrofit.create(Api::class.java)

            apiInstance.getUsers().enqueue(object : Callback<List<GithubUser>> {
                override fun onResponse(
                    call: Call<List<GithubUser>>,
                    response: Response<List<GithubUser>>
                ) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body())

                        if (response.body() != null) {
                            githubUsers = response.body()!!
                        }
                    }
                }

                override fun onFailure(call: Call<List<GithubUser>>, t: Throwable) {
                    Log.e("TAG","Nope, Error")
                    Log.e("Error", t.message.toString())
                }
            })
    }

    fun getSearchUsers(): LiveData<List<GithubUser>>{
        return listUsers
    }

    fun filterUsers(query: String?) {
        if (query.isNullOrEmpty()) {
            listUsers.value = githubUsers
            return
        }

        val filteredList = githubUsers.filter { githubUser ->
            githubUser.login.contains(query)
        }
        listUsers.value = filteredList
    }
}