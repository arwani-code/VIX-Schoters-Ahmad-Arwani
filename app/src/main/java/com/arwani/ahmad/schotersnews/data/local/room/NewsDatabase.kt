package com.arwani.ahmad.schotersnews.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}