package com.example.learntube.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.learntube.domain.use_cases.GetPostsBySearchUseCase
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsBySearchUseCase
): ViewModel() {


    private suspend fun getPosts() {
    }
}