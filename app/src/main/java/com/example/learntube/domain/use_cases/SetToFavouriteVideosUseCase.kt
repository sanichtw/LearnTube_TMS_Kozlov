package com.example.learntube.domain.use_cases

import com.example.learntube.data.local.entity.FavouriteItemEntity
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.repository.SearchItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetToFavouriteVideosUseCase @Inject constructor(
    private val searchItemRepository: SearchItemRepository
){
    suspend fun setToFavouriteVideo(favouriteVideo: SearchItem) {
        searchItemRepository.setToFavouriteVideo(favouriteVideo = favouriteVideo)
    }
}