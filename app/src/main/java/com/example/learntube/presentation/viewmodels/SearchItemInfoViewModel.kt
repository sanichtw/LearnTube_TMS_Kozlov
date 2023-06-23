package com.example.learntube.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.use_cases.GetSearchItemByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchItemInfoViewModel @Inject constructor(
    private val getSearchItemByIdUseCase: GetSearchItemByIdUseCase
) : ViewModel() {
    private val _searchItemInfo = MutableLiveData<SearchItem>()
    val searchItemInfo: LiveData<SearchItem> = _searchItemInfo

    init {
        viewModelScope.launch {
            getPostInfo()
        }
    }

    private suspend fun getPostInfo() {
        _searchItemInfo.value = getSearchItemByIdUseCase.getSearchItemById(3)
    }
}