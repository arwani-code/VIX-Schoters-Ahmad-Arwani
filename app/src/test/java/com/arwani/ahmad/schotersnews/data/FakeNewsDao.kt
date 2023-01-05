package com.arwani.ahmad.schotersnews.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity
import com.arwani.ahmad.schotersnews.data.local.room.NewsDao

class FakeNewsDao : NewsDao {

    private var newsData = mutableListOf<NewsEntity>()

    override fun getNews(category: String): LiveData<List<NewsEntity>> {
        val observableNews = MutableLiveData<List<NewsEntity>>()
        observableNews.value = newsData
        return observableNews
    }

    override fun getBookmarkedNews(): LiveData<List<NewsEntity>> {
        val observableNews = MutableLiveData<List<NewsEntity>>()
        observableNews.value = newsData
        return observableNews
    }

    override suspend fun insertNews(news: List<NewsEntity>) {
       newsData.addAll(news)
    }

    override suspend fun updateNews(news: NewsEntity) {
        newsData.replaceAll { news }
    }

    override suspend fun deleteAll(name: String) {
        newsData.removeIf { it.title == name }
    }

    override suspend fun isNewsBookmarked(title: String): Boolean {
        return newsData.isNotEmpty()
    }
}