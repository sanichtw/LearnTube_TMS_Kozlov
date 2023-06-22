package com.example.learntube.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learntube.R
import com.example.learntube.databinding.FragmentPostsScreenBinding
import com.example.learntube.domain.models.SearchItemDomain
import com.example.learntube.presentation.adapters.PostsAdapter
import com.example.learntube.presentation.viewmodels.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostsScreen : Fragment() {

    private var _binding: FragmentPostsScreenBinding? = null
    private val viewModel: PostsViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPostsScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observePosts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observePosts() {
        viewModel.postList.observe(viewLifecycleOwner) { posts ->
            initRecycler(posts)
        }
    }

    private fun initRecycler(posts: List<SearchItemDomain>) {
//        val customItemDecoration = CustomItemDecoration(requireContext())

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
//            addItemDecoration(customItemDecoration)
            adapter = PostsAdapter(
                context = this@PostsScreen,
                items = posts,
                event = {
                    findNavController().navigate(R.id.action_PostsScreen_to_FirstFragment)
                }
            )
        }
    }
}