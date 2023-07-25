package com.example.learntube.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.use_cases.GetSearchItemsBySearchQueryUseCase
import com.example.learntube.domain.use_cases.SetVideoAsFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchItemsViewModel @Inject constructor(
    private val getPostsUseCase: GetSearchItemsBySearchQueryUseCase,
    private val setToFavouriteVideoUseCase: SetVideoAsFavoriteUseCase
) : ViewModel() {

    private val _postList: MutableStateFlow<List<SearchItem>> = MutableStateFlow(emptyList())
    val postList: StateFlow<List<SearchItem>> = _postList.asStateFlow()

    var searchQueryState: String? = null

    fun getPosts(searchText: String?) {
        viewModelScope.launch {
            _postList.emit(getPostsUseCase.getSearchItemsBySearchQuery(searchText))
        }
    }

    suspend fun setVideoAsFavorite(favouriteVideo: SearchItem) {
        setToFavouriteVideoUseCase.setVideoAsFavorite(favouriteVideo = favouriteVideo)
    }
}
