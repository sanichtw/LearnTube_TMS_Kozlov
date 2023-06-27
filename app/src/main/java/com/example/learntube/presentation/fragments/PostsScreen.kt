package com.example.learntube.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learntube.R
import com.example.learntube.databinding.FragmentPostsScreenBinding
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.use_cases.GetSearchItemsBySearchQueryUseCase
import com.example.learntube.presentation.adapters.ItemDecoration.CustomItemDecoration
import com.example.learntube.presentation.adapters.SearchItemAdapter
import com.example.learntube.presentation.viewmodels.SearchItemsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class PostsScreen : Fragment() {

    private var _binding: FragmentPostsScreenBinding? = null
    private val viewModel: SearchItemsViewModel by viewModels()
    private var isSearchQueryExist: String? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        isSearchQueryExist = sharedPreferences.getString(SEARCH_KEY, null)

        _binding = FragmentPostsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isSearchQueryExist.isNullOrEmpty()) {
            viewModel.searchQueryState = isSearchQueryExist
            lifecycleScope.launch {
                viewModel.getPosts(viewModel.searchQueryState)
                observePosts()
            }
        }

        binding.searchButton.setOnClickListener {
            val searchInputText = binding.searchInput.text.toString()
            saveStringToSharedPreferences(searchInputText = searchInputText)

            binding.apply {
                loader?.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }

            if (searchInputText.isNotBlank()) {
                lifecycleScope.launch {
                    viewModel.apply {
                        searchQueryState = searchInputText
                        getPosts(viewModel.searchQueryState)
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
            loader?.visibility = View.GONE
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

    override fun onDestroy() {
        requireContext()
            .getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            .edit()
            .remove(SEARCH_KEY)
            .apply()

    }

    private fun saveStringToSharedPreferences(searchInputText: String) {
        requireContext()
            .getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            .edit()
            .putString(SEARCH_KEY, searchInputText)
            .apply()

    }

    companion object {
        private const val SEARCH_KEY = "searchQuery"
    }
}