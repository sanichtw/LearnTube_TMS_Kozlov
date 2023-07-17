package com.example.learntube.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.use_cases.GetFavoriteVideoUseCase
import com.example.learntube.domain.use_cases.SetVideoAsFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteVideoViewModel @Inject constructor(
    private val getFavouriteVideoUseCase: GetFavoriteVideoUseCase,
    private val setVideoAsFavouriteUseCase: SetVideoAsFavoriteUseCase
) : ViewModel() {
    private val _favouriteVideo = MutableLiveData<List<SearchItem>>()
    val favouriteVideo: LiveData<List<SearchItem>> = _favouriteVideo

    init {
        viewModelScope.launch {
            getFavouriteVideo()
        }
    }

    private suspend fun getFavouriteVideo() {
        _favouriteVideo.value = getFavouriteVideoUseCase.getFavouriteVideo()
    }

    suspend fun setVideoAsFavorite(favouriteVideo: SearchItem) {
        setVideoAsFavouriteUseCase.setVideoAsFavorite(favouriteVideo = favouriteVideo)
    }
}