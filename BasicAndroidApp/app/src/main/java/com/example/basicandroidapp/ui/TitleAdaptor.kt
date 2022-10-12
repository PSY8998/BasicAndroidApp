package com.example.basicandroidapp.ui

import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.basicandroidapp.R
import com.example.basicandroidapp.data.Posts

class TitleAdaptor(
    val onPostClick: (postId: Int) -> PostsFragment
) : ListAdapter<Posts, TitleAdaptor.TitleViewHolder>(PostsDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TitleViewHolder{

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.title_item,
            parent, false
        )

        return TitleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        val post = getItem(position)
        holder.titleName.text = post.title
        holder.titleName.setOnClickListener { onPostClick(post.id) }
    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleName: TextView = itemView.findViewById(R.id.titleItem)


    }

    class PostsDiffUtil: DiffUtil.ItemCallback<Posts>() {
        override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return oldItem == newItem
        }

    }
}