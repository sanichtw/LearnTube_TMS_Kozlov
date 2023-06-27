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
        println(isSearchQueryExist)

        if (!isSearchQueryExist.isNullOrEmpty()) {
            viewModel.searchQueryState = isSearchQueryExist
            lifecycleScope.launch {
                viewModel.getPosts(viewModel.searchQueryState)
                observePosts()
            }
        }

        binding.searchButton.setOnClickListener {
            binding.loader?.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE

            val searchInputText = binding.searchInput.text.toString()
            saveStringToSharedPreferences(searchInputText = searchInputText)
            if (searchInputText.isNotBlank()) {
                lifecycleScope.launch {
                    viewModel.searchQueryState = searchInputText
                    viewModel.getPosts(viewModel.searchQueryState)
                    observePosts()
                }
            }
        }
    }

    private fun observePosts() {
        viewModel.postList.observe(viewLifecycleOwner) { posts ->
            initRecycler(posts)
        }
        binding.loader?.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun initRecycler(posts: List<SearchItem>) {
        val customItemDecoration = CustomItemDecoration(requireContext())

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
//            addItemDecoration(customItemDecoration)
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
        println(isSearchQueryExist)
        super.onDestroy()
        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(SEARCH_KEY)
        editor.apply()
    }

    private fun saveStringToSharedPreferences(searchInputText: String) {
        val sharedPreferences =
            requireContext()
                .getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(SEARCH_KEY, searchInputText)
        editor.apply()

    }

    companion object {
        private const val SEARCH_KEY = "searchQuery"
    }
}