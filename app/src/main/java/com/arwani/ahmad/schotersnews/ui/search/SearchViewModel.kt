package com.arwani.ahmad.schotersnews.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.arwani.ahmad.schotersnews.data.NewsRepository
import com.arwani.ahmad.schotersnews.data.network.response.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.arwani.ahmad.schotersnews.data.Result
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@HiltViewModel
class SearchViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModel() {

    var userInput by mutableStateOf("")

    fun getSearchNews(query: String): Flow<Result<List<Article>>> =
        newsRepository.getNewsQuery(query)
            .catch { emit(Result.Error(it.message.toString())) }
}