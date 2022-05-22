package com.example.githubusersapp.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusersapp.R
import com.google.android.material.imageview.ShapeableImageView
import java.util.concurrent.Executors

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    private val list = ArrayList<GithubUser>()

    fun setList(users: List<GithubUser>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ShapeableImageView = itemView.findViewById(R.id.ivUsers)
        val loginView: TextView = itemView.findViewById(R.id.tvLogin)
        val userTypeView: TextView = itemView.findViewById(R.id.tvType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_design, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val itemList = list[position]
        val executor = Executors.newSingleThreadExecutor()
        var urlImage: Bitmap?
        holder.imageView.setImageResource(R.drawable.noprofile)
        executor.execute{
            val imageUrl = itemList.avatar_url
            try {
                val `in` = java.net.URL(imageUrl).openStream()
                urlImage = BitmapFactory.decodeStream(`in`)
                holder.imageView.setImageBitmap(urlImage)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        holder.loginView.text = itemList.login
        holder.userTypeView.text = itemList.type
    }

    override fun getItemCount(): Int {
        return list.size
    }
}