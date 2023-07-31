package com.example.learntube.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.learntube.data.local.entity.SearchItemEntity

@Dao
internal interface SearchItemDao {
    companion object {
        const val TRUE_FAVOURITE_STATE = 1
    }

    @Query("SELECT * FROM SearchItemEntity WHERE searchQuery = :searchQuery")
    suspend fun getAllSearchItems(searchQuery: String?): List<SearchItemEntity>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(searchItems: List<SearchItemEntity>?)  //return result of working (such as true/false) Response<D, E>, Success<T>

    @Update()
    suspend fun setVideoAsFavorite(favouriteVideo: SearchItemEntity?)

    @Query("SELECT * FROM SearchItemEntity WHERE snippet_isFavourite = $TRUE_FAVOURITE_STATE")
    suspend fun getFavouriteVideo(): List<SearchItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(searchItems: SearchItemEntity)
}
