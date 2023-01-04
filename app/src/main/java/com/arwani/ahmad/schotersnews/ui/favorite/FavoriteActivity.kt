package com.arwani.ahmad.schotersnews.ui.favorite

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Constraints
import androidx.work.Data.Builder
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.arwani.ahmad.schotersnews.R
import com.arwani.ahmad.schotersnews.databinding.ActivityFavoriteBinding
import com.arwani.ahmad.schotersnews.notification.NotificationConstant
import com.arwani.ahmad.schotersnews.notification.NotificationWorker
import com.arwani.ahmad.schotersnews.ui.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var workManager: WorkManager
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        supportActionBar?.title = getString(R.string.favorite_articles)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        workManager = WorkManager.getInstance(this)

        val newsAdapter = NewsAdapter { news ->
            if (news.isBookmarked) {
                viewModel.deleteNews(news)
            } else {
                viewModel.saveNews(news)
            }
        }

        viewModel.newsFavorite.observe(this) { news ->
            newsAdapter.submitList(news)
            if (news.isEmpty()){
                binding.textView.visibility = View.VISIBLE
                requestWorkManager()
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

    private fun requestWorkManager() {
        val data = Builder()
            .build()
        val constraints = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Constraints.Builder()
                .setRequiresDeviceIdle(false)
                .build()
        } else {
            Constraints.Builder().build()
        }
        val oneWorkRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setInputData(data)
            .setConstraints(constraints)
            .addTag(NotificationConstant.NOTIFICATION_CHANNEL_ID)
            .build()
        workManager.enqueue(oneWorkRequest)
    }

}