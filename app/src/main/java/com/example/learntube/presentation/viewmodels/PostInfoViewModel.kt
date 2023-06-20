package com.example.learntube.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.learntube.domain.use_cases.GetPostInfoUseCase
import com.example.learntube.domain.use_cases.GetPostsBySearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostInfoViewModel @Inject constructor(
    private val getPostInfoUseCase: GetPostInfoUseCase
): ViewModel() {

    suspend fun getPostInfo() {}
}