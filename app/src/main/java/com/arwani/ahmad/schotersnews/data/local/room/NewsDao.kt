package com.arwani.ahmad.schotersnews.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arwani.ahmad.schotersnews.data.local.DatabaseConstant
import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM ${DatabaseConstant.news} ORDER BY ${DatabaseConstant.published_at} DESC")
    fun getNews(): LiveData<List<NewsEntity>>

    @Query("SELECT * FROM ${DatabaseConstant.news} where ${DatabaseConstant.bookmarked} = 1")
    fun getBookmarkedNews(): LiveData<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Update
    suspend fun updateNews(news: NewsEntity)

    @Query("DELETE FROM ${DatabaseConstant.news} WHERE ${DatabaseConstant.bookmarked} = 0")
    suspend fun deleteAll()

    @Query("SELECT EXISTS(SELECT * FROM ${DatabaseConstant.news} WHERE ${DatabaseConstant.title} = :title AND ${DatabaseConstant.bookmarked} = 1)")
    suspend fun isNewsBookmarked(title: String): Boolean
}