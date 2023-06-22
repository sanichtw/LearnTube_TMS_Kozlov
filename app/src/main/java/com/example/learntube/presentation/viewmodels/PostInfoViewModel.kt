package com.example.learntube.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learntube.data.local.SearchItemEntity
import com.example.learntube.domain.use_cases.GetPostInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostInfoViewModel @Inject constructor(
    private val getPostInfoUseCase: GetPostInfoUseCase
) : ViewModel() {
    private val _postInfo = MutableLiveData<SearchItemEntity>()
    val postInfo: LiveData<SearchItemEntity> = _postInfo

    init {
        viewModelScope.launch {
            getPostInfo()
        }
    }

    private suspend fun getPostInfo() {
        _postInfo.value = getPostInfoUseCase.getPostInfo(3)
    }
}