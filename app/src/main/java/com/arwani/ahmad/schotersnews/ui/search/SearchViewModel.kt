package com.arwani.ahmad.schotersnews.ui.search

import androidx.lifecycle.ViewModel
import com.arwani.ahmad.schotersnews.data.NewsRepository
import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModel() {
    fun getSearchNews(title: String): Flow<List<NewsEntity>> = newsRepository.getNewsQuery(title)
}