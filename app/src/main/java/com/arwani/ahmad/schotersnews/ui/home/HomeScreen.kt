package com.arwani.ahmad.schotersnews.ui.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arwani.ahmad.schotersnews.R
import com.arwani.ahmad.schotersnews.ui.category.CategoryActivity
import com.arwani.ahmad.schotersnews.ui.favorite.FavoriteActivity

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    val newsInit by viewModel.newsInit.observeAsState()
    val mContext = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(color = Color.Black),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = modifier
                    .size(180.dp)
                    .padding(start = 8.dp)
            )
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_bookmarked_white),
                contentDescription = "Favorite",
                modifier = modifier
                    .padding(16.dp)
                    .size(27.dp)
                    .clickable {
                        mContext.startActivity(Intent(mContext, FavoriteActivity::class.java))
                    }
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item(span = { GridItemSpan(2) }) {
                Text(
                    text = "News Categories",
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    modifier = modifier.padding(bottom = 8.dp)
                )
            }
            items(dummyCategory, key = { it.textCategory }) { category ->
                CategoryItem(category = category, onClick = {
                    val intent = Intent(mContext, CategoryActivity::class.java)
                    intent.putExtra(CategoryActivity.NEWS_CATEGORY, it)
                    mContext.startActivity(intent)
                })
            }
        }
    }
}
