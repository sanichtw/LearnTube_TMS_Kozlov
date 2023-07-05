package com.example.learntube.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.learntube.data.local.entity.FavouriteItemEntity
import com.example.learntube.data.local.entity.SearchItemEntity

@Database(entities = [SearchItemEntity::class, FavouriteItemEntity::class], version = 2)
abstract class SearchItemDataBase : RoomDatabase() {
    abstract fun searchItemDao(): SearchItemDao
}