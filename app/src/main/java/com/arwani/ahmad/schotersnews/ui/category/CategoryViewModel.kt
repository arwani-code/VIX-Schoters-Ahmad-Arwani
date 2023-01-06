package com.arwani.ahmad.schotersnews.ui.category

import androidx.lifecycle.*
import com.arwani.ahmad.schotersnews.data.NewsRepository
import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getNews(category: String) = newsRepository.getNewsData(category)

    fun saveNews(news: NewsEntity) {
        viewModelScope.launch {
            newsRepository.setNewsBookmark(news, true)
        }
    }

    fun searchDatabase(searchQuery: String, category: String): LiveData<List<NewsEntity>> {
        _isLoading.value = true
        viewModelScope.launch {
            delay(300)
            _isLoading.value = false
        }
        return newsRepository.searchDatabase(searchQuery, category = category).asLiveData()
    }

    fun deleteNews(news: NewsEntity) {
        viewModelScope.launch {
            newsRepository.setNewsBookmark(news, false)
        }
    }
}