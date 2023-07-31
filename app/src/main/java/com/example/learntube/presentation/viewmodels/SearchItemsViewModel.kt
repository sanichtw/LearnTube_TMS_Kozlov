package com.example.learntube.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.use_cases.GetSearchItemsBySearchQueryUseCase
import com.example.learntube.domain.use_cases.SetVideoAsFavoriteUseCase
import com.example.learntube.presentation.models.SearchFragmentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchItemsViewModel @Inject constructor(
    private val getPostsUseCase: GetSearchItemsBySearchQueryUseCase,
    private val setToFavouriteVideoUseCase: SetVideoAsFavoriteUseCase
) : ViewModel() {
    private val eventFlow: MutableStateFlow<Event> = MutableStateFlow(value = Event.Default)
    private val newsScreenUiState =
        MutableStateFlow(value = SearchFragmentUiState(isProgressVisible = false))

    internal fun getScreenStream(): Flow<SearchFragmentUiState> = newsScreenUiState
    internal fun getEventsStream(): Flow<Event> = eventFlow

    var searchQueryState: String? = null
    var filterState: String? = null
    var isInitialLoad = true
    private var videosList: List<SearchItem>? = null


    fun uploadVideos(searchText: String?) {
        viewModelScope.launch {
            updateProgressVisibilityState(isProgressVisible = true)

            viewModelScope.launch {
                delay(POST_LOADING_DELAY)
                videosList = getPostsUseCase.getSearchItemsBySearchQuery(searchText)
                onEventHandled(event = Event.InitRecycler(videosList = videosList))
                updateProgressVisibilityState(isProgressVisible = false)
            }
        }
    }

    suspend fun setVideoAsFavorite(favouriteVideo: SearchItem?) {
        setToFavouriteVideoUseCase.setVideoAsFavorite(favouriteVideo = favouriteVideo)
    }

    suspend fun filterSearchItems(sortBy: String) {
        updateProgressVisibilityState(isProgressVisible = true)
        delay(POST_LOADING_DELAY)
        videosList = when (sortBy) {
            UPLOAD_DATE -> videosList?.sortedBy { searchItem -> searchItem.snippet?.publishedAt }
            NAME -> videosList?.sortedBy { searchItem -> searchItem.snippet?.title }
            else -> getPostsUseCase.getSearchItemsBySearchQuery(searchQueryState)
        }
        onEventHandled(event = Event.InitRecycler(videosList = videosList))
        updateProgressVisibilityState(isProgressVisible = false)
    }

    private fun updateProgressVisibilityState(isProgressVisible: Boolean) {
        newsScreenUiState.update { screenUiState ->
            screenUiState.copy(isProgressVisible = isProgressVisible)
        }
    }

    private fun onEventHandled(event: Event) = viewModelScope.launch { eventFlow.emit(event) }

    internal sealed class Event {
        object Default : Event()
        data class InitRecycler(val videosList: List<SearchItem>?) : Event()
    }

    companion object {
        const val UPLOAD_DATE = "Upload Date"
        const val NAME = "Name"
        private const val POST_LOADING_DELAY = 1500L
    }
}
