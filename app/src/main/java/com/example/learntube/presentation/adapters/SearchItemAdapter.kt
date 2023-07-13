package com.example.learntube.presentation.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.learntube.R
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.use_cases.SetToFavouriteVideosUseCase
import com.example.learntube.presentation.fragments.PostsScreen
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import javax.inject.Singleton

class SearchItemAdapter(
    private val context: PostsScreen,
    private val items: List<SearchItem>,
) :
    RecyclerView.Adapter<SearchItemAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.icon)
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val checkBox: CheckBox = itemView.findViewById((R.id.checkBox))
//        val publishedAtTextView: TextView = itemView.findViewById(R.id.publishedAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.posts_items, parent, false)


        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = items[position].snippet.title
//            publishedAtTextView.text = items[position].snippet.publishedAt
        }

        Glide
            .with(context)
            .load(items[position].snippet.thumbnails.high?.url)
            .into(holder.imageView)

        when (items[position].kindId.kind) {
            "youtube#video" -> holder.imageView.setOnClickListener {
                val videoId = items[position].kindId.videoId
                println(videoId)
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=$videoId")
                )
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setPackage("com.google.android.youtube")
                context.startActivity(intent)
            }

            "youtube#playlist" -> holder.imageView.setOnClickListener {
                val playlistId = items[position].kindId.playlistId
                println(playlistId)
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/playlist?list=$playlistId")
                )
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setPackage("com.google.android.youtube")
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
