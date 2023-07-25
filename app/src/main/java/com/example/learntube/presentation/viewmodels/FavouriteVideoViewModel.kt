package com.example.learntube.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.use_cases.GetFavoriteVideoUseCase
import com.example.learntube.domain.use_cases.SetVideoAsFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavouriteVideoViewModel @Inject constructor(
    private val getFavouriteVideoUseCase: GetFavoriteVideoUseCase,
    private val setVideoAsFavouriteUseCase: SetVideoAsFavoriteUseCase
) : ViewModel() {
    private val _favouriteVideo: MutableStateFlow<List<SearchItem>> = MutableStateFlow(emptyList()) //Flow (mutableState) and remember (сохранение состояние списка)
    val favouriteVideo: StateFlow<List<SearchItem>> = _favouriteVideo

    init {
        viewModelScope.launch {
            getFavouriteVideo() //TODO rename
        }
    }

    private suspend fun getFavouriteVideo() {
        _favouriteVideo.value = getFavouriteVideoUseCase.getFavouriteVideo()
    }

    suspend fun setVideoAsFavorite(favouriteVideo: SearchItem) {
        setVideoAsFavouriteUseCase.setVideoAsFavorite(favouriteVideo = favouriteVideo)
    }
}

//    адаптер что-то выполняет
//    из адаптера к фрагменту идут callBack
//    callBack = {
//        viewModel.updateFavoriteVideo(favouriteVideo =favouriteVideo )
//    }
//
//
//    во вьюмодели по этой функции
//    fun updateFavoriteVideo(favouriteVideo: SearchItem){
//        handleEvent(event = )
//        через events
//    }
//}