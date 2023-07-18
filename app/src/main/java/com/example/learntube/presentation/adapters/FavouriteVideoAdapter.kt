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
import com.example.learntube.R
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.presentation.viewmodels.FavouriteVideoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


internal class FavouriteVideoAdapter(
    private val context: Context,
    private val favouriteVideo: List<SearchItem>,
    private val viewModel: FavouriteVideoViewModel
) :
    RecyclerView.Adapter<FavouriteVideoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.icon)
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.posts_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = favouriteVideo[position]

        holder.apply {
            titleTextView.text = item.snippet?.title

            checkBox.apply {
                isChecked = item.snippet?.isFavourite ?: false

                setOnCheckedChangeListener { _, isChecked ->
                    CoroutineScope(Dispatchers.Main).launch {
                        item.snippet?.isFavourite = isChecked
//                        viewModel.setVideoAsFavorite(item)
                    }
                }
            }
        }

        when (item.kindId?.kind) {
            "youtube#video" -> holder.itemView.setOnClickListener {
                val videoId = item.kindId.videoId

                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=$videoId")
                )
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .setPackage("com.google.android.youtube")
                context.startActivity(intent)
            }

            "youtube#playlist" -> holder.itemView.setOnClickListener {
                val playlistId = item.kindId.playlistId

                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/playlist?list=$playlistId")
                )
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .setPackage("com.google.android.youtube")
                context.startActivity(intent)
            }
        }

        Glide
            .with(context)
            .load(item.snippet?.thumbnails?.highSize?.url)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return favouriteVideo.size
    }
}