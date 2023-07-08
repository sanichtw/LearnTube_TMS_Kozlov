package com.example.learntube.presentation.fragments

import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learntube.R
import com.example.learntube.databinding.FragmentPostsScreenBinding
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.presentation.adapters.SearchItemAdapter
import com.example.learntube.presentation.viewmodels.SearchItemsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.abs


@AndroidEntryPoint
class PostsScreen : Fragment() {

    private var _binding: FragmentPostsScreenBinding? = null
    private val viewModel: SearchItemsViewModel by viewModels()
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPostsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindDrawer()

        if (!viewModel.searchQueryState.isNullOrBlank()) {
            lifecycleScope.launch {
                viewModel.getPosts(viewModel.searchQueryState)
                observePosts()
            }
        }

        binding.searchButton.setOnClickListener {
            val searchInputText = binding.searchInput.text.toString()

            binding.apply {
                loader.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }

            if (searchInputText.isNotBlank()) {
                lifecycleScope.launch {
                    viewModel.apply {
                        searchQueryState = searchInputText
                        getPosts(searchQueryState)
                    }
                    observePosts()
                }
            }
        }
    }

    private fun observePosts() {
        viewModel.postList.observe(viewLifecycleOwner) { posts ->
            initRecycler(posts)
        }

        binding.apply {
            loader.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun initRecycler(posts: List<SearchItem>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SearchItemAdapter(
                context = this@PostsScreen,
                items = posts,
                event = {
                    findNavController().navigate(R.id.action_PostsScreen_to_InfoFragment)
                },
                event2 = {
                    findNavController().navigate(R.id.action_PostsScreen_to_InfoFragment)
                }
            )
        }
    }

    private fun bindDrawer() {
        val gestureDetector = GestureDetectorCompat(
            requireContext(),
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onFling(
                    e1: MotionEvent,
                    e2: MotionEvent,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {
                    // Определение направления свайпа
                    val distanceX = e2.x
                    val distanceY = e2.y

                    if (distanceX > 0 && abs(distanceX) > abs(distanceY)) {
                        // Свайп вправо
                        binding.drawerLayout.openDrawer(binding.drawer)
                        return true
                    }

                    return super.onFling(e1, e2, velocityX, velocityY)
                }
            })

        // Прикрепление обработчика жестов к DrawerLayout
        binding.drawerLayout.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }
    }

    companion object {
        private const val SEARCH_KEY = "searchQuery"
    }
}