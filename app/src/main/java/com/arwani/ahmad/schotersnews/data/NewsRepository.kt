package com.arwani.ahmad.schotersnews.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.arwani.ahmad.schotersnews.BuildConfig
import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity
import com.arwani.ahmad.schotersnews.data.local.room.NewsDao
import com.arwani.ahmad.schotersnews.data.network.NetworkConstant
import com.arwani.ahmad.schotersnews.data.network.retrofit.ApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {
    fun getNewsData(category: String): LiveData<Result<List<NewsEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val articles = apiService.getNews(
                category = category,
                country = NetworkConstant.id,
                apiKey = BuildConfig.API_KEY
            ).articles
            val newsList = articles.map { article ->
                val isBookmarked = newsDao.isNewsBookmarked(article.title)
                NewsEntity(
                    article.title,
                    category,
                    article.publishedAt,
                    article.urlToImage,
                    article.url,
                    isBookmarked
                )
            }
            newsDao.deleteAll(category)
            newsDao.insertNews(newsList)
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<NewsEntity>>> = newsDao.getNews(category = category).map { Result.Success(it) }
        emitSource(localData)
    }

    fun getBookmarkedNews(): LiveData<List<NewsEntity>> {
        return newsDao.getBookmarkedNews()
    }

    suspend fun setNewsBookmark(news: NewsEntity, bookmarkState: Boolean) {
        news.isBookmarked = bookmarkState
        newsDao.updateNews(news)
    }
}