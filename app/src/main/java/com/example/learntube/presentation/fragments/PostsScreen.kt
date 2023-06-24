package com.example.learntube.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

    private val binding get() = _binding!!

    @Inject
    lateinit var getSearchItemsBySearchQueryUseCase: GetSearchItemsBySearchQueryUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPostsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchInput = binding.searchInput
        saveStringToSharedPreferences(searchInput = searchInput)


        if (!searchInput.text.isNullOrBlank()) {
            viewModel.searchQueryState = searchInput.text.toString()
            lifecycleScope.launch {
                viewModel.getPosts(viewModel.searchQueryState)
                observePosts()
            }
        }

        binding.searchButton.setOnClickListener {
            binding.loader?.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
            val searchText = searchInput.text.toString()

            if (searchText.isNotBlank()) {
                lifecycleScope.launch {
                    viewModel.searchQueryState = searchText
                    viewModel.getPosts(viewModel.searchQueryState)
                    observePosts()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            addItemDecoration(customItemDecoration)
            adapter = SearchItemAdapter(
                context = this@PostsScreen,
                items = posts,
                event = {
                    findNavController().navigate(R.id.action_PostsScreen_to_LoginScreen)
                },
                event2 = {
                    findNavController().navigate(R.id.action_PostsScreen_to_LoginScreen)
                }
            )
        }
    }

    private fun saveStringToSharedPreferences(searchInput: EditText) {
        val sharedPreferences =
            requireContext()
                .getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        searchInput.text = Editable
            .Factory
            .getInstance()
            .newEditable(sharedPreferences.getString("SearchQuery", ""))

        searchInput.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {
                sharedPreferences.edit().putString("SearchQuery", s.toString()).commit()
            }
        })
    }
}