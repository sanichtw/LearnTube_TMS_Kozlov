package com.example.learntube.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.learntube.data.local.entity.SearchItemEntity

@Dao
interface SearchItemDao {
    @Query("SELECT * FROM SearchItemEntity WHERE searchQuery = :searchQuery")
    suspend fun getAllSearchItems(searchQuery: String?): List<SearchItemEntity>

    @Query("SELECT * FROM SearchItemEntity WHERE snippet_isFavourite = 1")
    suspend fun getFavouriteVideo(): List<SearchItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(searchItems: List<SearchItemEntity>)

    @Update()
    suspend fun setVideoAsFavorite(favouriteVideo: SearchItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(searchItems: SearchItemEntity)
}