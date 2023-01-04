package com.arwani.ahmad.schotersnews.ui.home

import androidx.lifecycle.ViewModel
import com.arwani.ahmad.schotersnews.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {
    val newsInit =  newsRepository.getNewsData("science")
}