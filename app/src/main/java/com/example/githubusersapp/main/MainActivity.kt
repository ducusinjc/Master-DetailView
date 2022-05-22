package com.example.githubusersapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusersapp.databinding.ActivityMainBinding
import com.example.githubusersapp.model.UserAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter
//    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.apply{
            rvUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUsers.setHasFixedSize(true)
            rvUsers.adapter = adapter

//            val query = svUsers.toString()
//            if(query.isEmpty()) return
//            showLoading(true)
//            viewModel.setSearchUsers(query)
            viewModel.setSearchUsers()



            svUsers.maxWidth = Int.MAX_VALUE
            svUsers.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
//                    searchUser()TODO
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
//                    searchUser()TODO
                    return true
                }

            })
//            btnSearch.setOnClickListener{
//                searchUser()
//            }
//            etQuery.setOnKeyListener {v, keyCode, event ->
//                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
//                    searchUser()
//                    return@setOnKeyListener true
//                }
//                return@setOnKeyListener false
//            }
        }

        viewModel.getSearchUsers().observe(this) {
            if (it != null) {
                adapter.setList(it)
//                showLoading(false)
            }
        }
    }

//    private fun searchUser(){
//        binding.apply{
//            val query = svUsers.toString()
//            if(query.isEmpty()) return
////            showLoading(true)
//            viewModel.setSearchUsers(query)
//        }
//    }

//    private fun showLoading(state: Boolean){
//        if(state){
//            binding.progressBar.visibility = View.VISIBLE
//        }else {
//            binding.progressBar.visibility = View.GONE
//        }
//    }
}