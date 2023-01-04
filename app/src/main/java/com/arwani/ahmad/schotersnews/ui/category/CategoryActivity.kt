package com.arwani.ahmad.schotersnews.ui.category

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Constraints
import com.arwani.ahmad.schotersnews.data.Result
import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity
import com.arwani.ahmad.schotersnews.databinding.CategoryMainBinding
import com.arwani.ahmad.schotersnews.ui.NewsViewModel
import com.arwani.ahmad.schotersnews.ui.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.arwani.ahmad.schotersnews.notification.NotificationConstant
import com.arwani.ahmad.schotersnews.notification.NotificationConstant.NEWS_ID
import com.arwani.ahmad.schotersnews.notification.NotificationConstant.NOTIFICATION_CHANNEL_ID
import com.arwani.ahmad.schotersnews.notification.NotificationWorker

@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: CategoryMainBinding
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CategoryMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val category = intent.getStringExtra(NEWS_CATEGORY)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val newsCategory = category?.lowercase()
        supportActionBar?.title = "Schoters $category"

        val newsAdapter = NewsAdapter { news ->
            if (news.isBookmarked) {
                viewModel.deleteNews(news)
            } else {
                viewModel.saveNews(news)
            }
        }

        if (newsCategory != null) {
            viewModel.getNews(newsCategory).observe(this) { news ->
                if (news != null) {
                    when (news) {
                        is Result.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val newsData = news.data
                            newsAdapter.submitList(newsData)
                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                this,
                                "Connect to the internet and try again",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val NEWS_CATEGORY = "news"
    }
}