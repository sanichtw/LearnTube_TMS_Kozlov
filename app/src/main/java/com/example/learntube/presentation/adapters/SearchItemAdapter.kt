package com.example.learntube.presentation.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learntube.R
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.use_cases.SetVideoAsFavoriteUseCase
import com.example.learntube.presentation.viewmodels.SearchItemsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchItemAdapter(
    private val context: Context,
    private val searchItems: List<SearchItem>,
    private val viewModel: SearchItemsViewModel
) :
    RecyclerView.Adapter<SearchItemAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.icon)
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
//        val publishedAtTextView: TextView = itemView.findViewById(R.id.publishedAt)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.posts_items, parent, false)


        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = searchItems[position]
        Log.d(
            "Test Adapter",
            "${item.snippet.title}: is favourite ${item.snippet.isFavourite}"
        )
        Log.d("Test Adapter", "____________________________")

        holder.apply {
            titleTextView.text = item.snippet.title
//            publishedAtTextView.text = items[position].snippet.publishedAt

            checkBox.apply {
                isChecked = item.snippet.isFavourite

                setOnCheckedChangeListener { _, isChecked ->
                    CoroutineScope(Dispatchers.Main).launch {
                        item.snippet.isFavourite = isChecked
                        viewModel.setVideoAsFavorite(item)
//                        notifyDataSetChanged()
                    }
                    Toast.makeText(context, "Checked $isChecked", Toast.LENGTH_SHORT).show()
                }
            }
        }

        when (item.kindId.kind) {
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
            .load(item.snippet.thumbnails.high?.url)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        Log.d("Test Adapter", "Items size: ${searchItems.size}")

        return searchItems.size
    }
}
