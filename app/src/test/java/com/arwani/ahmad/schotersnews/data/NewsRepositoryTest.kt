package com.arwani.ahmad.schotersnews.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arwani.ahmad.schotersnews.DataDummy
import com.arwani.ahmad.schotersnews.MainDispatcherRule
import com.arwani.ahmad.schotersnews.data.local.room.NewsDao
import com.arwani.ahmad.schotersnews.data.network.retrofit.ApiService
import com.arwani.ahmad.schotersnews.observeForTesting
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class NewsRepositoryTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var apiService: ApiService
    private lateinit var newsDao: NewsDao
    private lateinit var newsRepository: NewsRepository

    @Before
    fun setUp() {
        apiService = FakeApiService()
        newsDao = FakeNewsDao()
        newsRepository = NewsRepository(apiService, newsDao)
    }

    @Test
    fun `when getNewsData Should Not Null`() = runTest {
        val expectedNews = DataDummy.generateDummyNewsResponse()
        val actualNews = newsRepository.getNewsData("music")
        actualNews.observeForTesting {
            Assert.assertNotNull(actualNews)
            Assert.assertEquals(
                expectedNews.articles.size,
                (actualNews.value as Result.Success).data.size
            )
        }
    }
}