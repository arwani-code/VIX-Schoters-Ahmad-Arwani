package com.arwani.ahmad.schotersnews.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.viewinterop.AndroidView
import com.arwani.ahmad.schotersnews.R
import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity
import com.arwani.ahmad.schotersnews.ui.theme.Blue50
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailActivity : AppCompatActivity() {

    private val viewModel: NewsDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newsDetail = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(NEWS_DATA, NewsEntity::class.java)
        } else {
            intent.getParcelableExtra<NewsEntity>(NEWS_DATA)
        }

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (newsDetail != null) {
                        Text(text = "$newsDetail")
                        NewsDetailScreen(
                            newsDetail,
                            viewModel
                        )
                    }
                }
            }
        }
    }

    companion object {
        const val NEWS_DATA = "data"
    }
}

@Composable
fun NewsDetailScreen(
    newsDetail: NewsEntity,
    viewModel: NewsDetailViewModel,
) {
    var newsState by remember {
        mutableStateOf(newsDetail.isBookmarked)
    }
    NewsDetailContent(
        title = newsDetail.title,
        url = newsDetail.url,
        bookmarkStatus = newsState,
        updateBookmarkStatus = {
            if (newsDetail.isBookmarked){
                newsState = !newsState
                viewModel.deleteNews(newsDetail)
            }else {
                newsState = !newsState
                viewModel.saveNews(newsDetail)
            }
        })
}

@Composable
fun NewsDetailContent(
    title: String,
    url: String?,
    bookmarkStatus: Boolean,
    updateBookmarkStatus: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Blue50,
                title = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black
                    )
                },
                actions = {
                    IconButton(onClick = updateBookmarkStatus) {
                        Icon(
                            painter = if (bookmarkStatus) {
                                painterResource(R.drawable.ic_bookmarked_white)
                            } else {
                                painterResource(R.drawable.ic_bookmark_white)
                            },
                            contentDescription = stringResource(R.string.save_bookmark),
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            AndroidView(
                factory = {
                    WebView(it).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        webViewClient = WebViewClient()
                    }
                },
                update = {
                    if (url != null) {
                        it.loadUrl(url)
                    }
                }
            )
        }
    }
}