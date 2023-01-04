package com.arwani.ahmad.schotersnews.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.arwani.ahmad.schotersnews.BuildConfig
import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity
import com.arwani.ahmad.schotersnews.data.local.room.NewsDao
import com.arwani.ahmad.schotersnews.data.network.NetworkConstant
import com.arwani.ahmad.schotersnews.data.network.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {
    fun getNewsData(category: String): LiveData<Result<List<NewsEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getNews(
                category = category,
                country = NetworkConstant.id,
                apiKey = BuildConfig.API_KEY
            )
            val articles = response.articles
            val newsList = articles.map { article ->
                val isBookmarked = newsDao.isNewsBookmarked(article.title)
                NewsEntity(
                    title = article.title,
                    category = category,
                    publishedAt = article.publishedAt,
                    urlToImage = article.urlToImage,
                    url = article.url,
                    isBookmarked = isBookmarked
                )
            }
            newsDao.deleteAll(name = category)
            newsDao.insertNews(newsList)
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<NewsEntity>>> =
            newsDao.getNews(category = category).map { Result.Success(it) }
        emitSource(localData)
    }

    fun getBookmarkedNews(): LiveData<List<NewsEntity>> {
        return newsDao.getBookmarkedNews()
    }

    fun getNewsQuery(title: String): LiveData<Result<List<NewsEntity>>> = liveData {
        emit(Result.Loading)
        val articles: LiveData<Result<List<NewsEntity>>> = newsDao.searchNews(title).map { Result.Success(it) }
        emitSource(articles)
    }

    suspend fun setNewsBookmark(news: NewsEntity, bookmarkState: Boolean) {
        news.isBookmarked = bookmarkState
        newsDao.updateNews(news)
    }
}