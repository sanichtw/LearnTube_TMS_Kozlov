package com.example.learntube.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.learntube.data.local.entity.SearchItemEntity

@Database(entities = [SearchItemEntity::class], version = 1)
abstract class SearchItemDataBase : RoomDatabase() {
    abstract fun searchItemDao(): SearchItemDao
}