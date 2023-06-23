package com.example.learntube.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.learntube.data.local.entity.SearchItemEntity

@Dao
interface SearchItemDao {
    @Query("SELECT * FROM SearchItemEntity WHERE searchQuery = :searchQuery")
    suspend fun getAll(searchQuery: String): List<SearchItemEntity>

    @Query("SELECT * FROM SearchItemEntity")
    suspend fun getAll(): List<SearchItemEntity>


    @Query("SELECT * FROM SearchItemEntity WHERE id = :searchItemId")
    suspend fun getById(searchItemId: Long): SearchItemEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(searchItems: List<SearchItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(searchItems: SearchItemEntity)
}