package com.example.learntube.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learntube.domain.models.SearchItemDomain
import com.example.learntube.domain.use_cases.GetPostsBySearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsBySearchUseCase
): ViewModel() {

    private val _postList = MutableLiveData<List<SearchItemDomain>>()
    val postList: LiveData<List<SearchItemDomain>> = _postList

    init {
        viewModelScope.launch {
            getPosts()
        }
    }

    private suspend fun getPosts() {
        _postList.value = getPostsUseCase.getPostsBySearch()
    }

}