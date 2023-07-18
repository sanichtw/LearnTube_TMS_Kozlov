package com.example.learntube.domain.use_cases

import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.repositories.SearchItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GetFavoriteVideoUseCase @Inject constructor(
    private val searchItemRepository: SearchItemRepository
) {
    suspend fun getFavouriteVideo(): List<SearchItem> = searchItemRepository.getFavouriteVideo()
}