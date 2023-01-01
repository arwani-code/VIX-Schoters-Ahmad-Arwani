package com.arwani.ahmad.schotersnews.ui

import androidx.lifecycle.ViewModel
import com.arwani.ahmad.schotersnews.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    val getNewsData = newsRepository.getNewsData("music")
}