package com.example.learntube.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.learntube.data.repository.local.SearchItemDao

@Dao
interface SearchItemLocalDao :  SearchItemDao {
    @Query("SELECT * FROM SearchItemEntity WHERE id = :search")
    suspend fun getAll(search: String?): List<SearchItemEntity>?

    @Query("SELECT * FROM SearchItemEntity WHERE id = :postId")
    suspend fun getPostById(postId: Long): SearchItemEntity?
}