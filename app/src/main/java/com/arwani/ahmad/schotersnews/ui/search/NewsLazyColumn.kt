package com.arwani.ahmad.schotersnews.ui.search

import android.content.Intent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity
import com.arwani.ahmad.schotersnews.ui.component.NewsItem
import com.arwani.ahmad.schotersnews.ui.detail.NewsDetailActivity

@Composable
fun NewsLazyColumn(
    modifier: Modifier = Modifier,
    articles: List<NewsEntity>
) {
    val mContext = LocalContext.current
    LazyColumn {
        items(articles, key = { it.id }) { article ->
            NewsItem(
                urlToImage = article.urlToImage,
                title = article.title,
                publishedAt = article.publishedAt,
                onItemClick = {
                    val intent = Intent(mContext, NewsDetailActivity::class.java)
                    intent.putExtra(NewsDetailActivity.NEWS_DATA, article)
                    mContext.startActivity(intent)
                }
            )
        }
    }
}