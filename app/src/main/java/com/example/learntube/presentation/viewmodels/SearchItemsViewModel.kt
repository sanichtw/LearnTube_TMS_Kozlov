package com.example.learntube.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.use_cases.GetSearchItemsBySearchQueryUseCase
import com.example.learntube.domain.use_cases.SetVideoAsFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchItemsViewModel @Inject constructor(
    private val getPostsUseCase: GetSearchItemsBySearchQueryUseCase,
    private val setToFavouriteVideoUseCase: SetVideoAsFavoriteUseCase
) : ViewModel() {

    private val _postList = MutableLiveData<List<SearchItem>>()
    val postList: LiveData<List<SearchItem>> = _postList
    var searchQueryState: String? = null

    suspend fun getPosts(searchText: String?) {
        _postList.postValue(getPostsUseCase.getSearchItemsBySearchQuery(searchText))
    }

    suspend fun setVideoAsFavorite(favouriteVideo: SearchItem) {
        setToFavouriteVideoUseCase.setVideoAsFavorite(favouriteVideo = favouriteVideo)
    }
}