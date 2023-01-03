package com.arwani.ahmad.schotersnews.ui.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.arwani.ahmad.schotersnews.data.Result
import com.arwani.ahmad.schotersnews.ui.component.SearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Column(modifier = modifier.fillMaxSize()) {
        SearchBar(value = viewModel.userInput, onValueChanged = {
            viewModel.userInput = it
        })

    }
//    val search by viewModel.getSearchNews("tesla").collectAsState(initial = Result.Loading)
//    when(search){
//        is Result.Loading -> {
//            CircularProgressIndicator(color = Color.White)
//        }
//        is Result.Success -> {
//            Log.i("SJDJSDNJSNDJSNDSJD", "SearchScreen: ${search}")
//            Text(text = search.toString(), color = Color.White)
//        }
//        is Result.Error -> {
//            Log.i("SEARCHQUERY", "SearchScreen: Errorrr")
//        }
//    }
}