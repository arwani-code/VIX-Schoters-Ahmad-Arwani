package com.arwani.ahmad.schotersnews.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arwani.ahmad.schotersnews.data.local.DatabaseConstant
import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM ${DatabaseConstant.news} WHERE ${DatabaseConstant.category} = :category ORDER BY ${DatabaseConstant.published_at} DESC")
    fun getNews(category: String): LiveData<List<NewsEntity>>

    @Query("SELECT * FROM ${DatabaseConstant.news} where ${DatabaseConstant.bookmarked} = 1")
    fun getBookmarkedNews(): LiveData<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Update
    suspend fun updateNews(news: NewsEntity)

    @Query("DELETE FROM ${DatabaseConstant.news} WHERE ${DatabaseConstant.bookmarked} = 0 AND ${DatabaseConstant.category} = :name")
    suspend fun deleteAll(name: String)

    @Query("SELECT EXISTS(SELECT * FROM ${DatabaseConstant.news} WHERE ${DatabaseConstant.title} = :title AND ${DatabaseConstant.bookmarked} = 1)")
    suspend fun isNewsBookmarked(title: String): Boolean

    @Query("SELECT * FROM ${DatabaseConstant.news} WHERE ${DatabaseConstant.title} LIKE '%' || :title || '%'")
    fun searchNews(title: String): Flow<List<NewsEntity>>
}