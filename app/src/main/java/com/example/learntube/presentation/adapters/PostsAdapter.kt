package com.example.learntube.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learntube.R
import com.example.learntube.domain.models.SearchItemDomain
import com.example.learntube.presentation.fragments.PostsScreen

class PostsAdapter(
    private val context: PostsScreen,
    private val items: List<SearchItemDomain>,
    private val event: (View) -> Unit
) :
    RecyclerView.Adapter<PostsAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.icon)
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val publishedAtTextView: TextView = itemView.findViewById(R.id.publishedAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.posts_items, parent, false)

        itemView.setOnClickListener {
            event(it)
        }
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = items[position].snippet.title
            publishedAtTextView.text = items[position].snippet.publishedAt
        }
        Glide
            .with(context)
            .load(items[position].snippet.thumbnails.default)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}