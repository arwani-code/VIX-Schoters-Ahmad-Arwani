package com.arwani.ahmad.schotersnews.ui

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.arwani.ahmad.schotersnews.data.Result

@Composable
fun JetNewsApp(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel()
){
    val news = viewModel.getNewsData.observeAsState().value
    Text(text = "$news")
    if(news != null){
        when(news){
            is Result.Loading -> {
                Log.i("news_data", "JetNewsApp: loaading") }
            is Result.Success -> {
                Log.i("news_data", "JetNewsApp: ${news.data}")
            }
            is Result.Error -> {
                Log.i("news_data", "JetNewsApp: ${news.error}")
            }
        }
    }
}