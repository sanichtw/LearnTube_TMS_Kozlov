package com.example.learntube.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learntube.databinding.FragmentFavouriteVideoBinding
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.presentation.adapters.FavouriteVideoAdapter
import com.example.learntube.presentation.viewmodels.FavouriteVideoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteVideoScreen : Fragment() {
    private var _binding: FragmentFavouriteVideoBinding? = null
    private val viewModel: FavouriteVideoViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavouriteVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFavouriteVideo()
    }

    private fun observeFavouriteVideo() {
        viewModel.favouriteVideo.observe(viewLifecycleOwner) { favouriteVideo ->
            initRecycler(favouriteVideo)
        }
    }

    private fun initRecycler(favouriteVideo: List<SearchItem>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FavouriteVideoAdapter(
                context = requireContext(),
                favouriteVideo = favouriteVideo,
                viewModel = viewModel
            )
        }
    }
}