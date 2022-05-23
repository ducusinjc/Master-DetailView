package com.example.githubusersapp.main

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusersapp.databinding.ActivityMainBinding
import com.example.githubusersapp.model.UserAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.apply{
            rvUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUsers.adapter = adapter
            viewModel.setSearchUsers()
            svUsers.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.filterUsers(newText)
                    rvUsers.smoothScrollToPosition(0)
                    return true
                }
            })
        }

        viewModel.getSearchUsers().observe(this) {
            adapter.setList(it)
        }
    }
}