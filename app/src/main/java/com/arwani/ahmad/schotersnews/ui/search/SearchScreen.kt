package com.arwani.ahmad.schotersnews.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.arwani.ahmad.schotersnews.ui.component.SearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    var value by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(modifier = modifier.background(Color.Black)) {
            SearchBar(value = value, onValueChanged = { value = it })
        }
        if(value.isNotEmpty()){
            val articles by viewModel.getSearchNews(value).collectAsState(initial = emptyList())
            Text(text = articles.toString(), color = Color.White)
        }
//        articles?.let { NewsLazyColumn(articles = it) }
    }
}
