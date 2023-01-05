package com.arwani.ahmad.schotersnews.data.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.arwani.ahmad.schotersnews.data.local.room.NewsDao
import com.arwani.ahmad.schotersnews.data.local.room.NewsDatabase
import com.arwani.ahmad.schotersnews.utils.DummyData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class NewsDatabaseTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: NewsDatabase
    private lateinit var dao: NewsDao

    private val sampleNews = DummyData.generateDummyNewsEntity()

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NewsDatabase::class.java
        ).build()
        dao = database.newsDao()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun isNews_Bookmarked() = runBlockingTest {
        dao.insertNews(sampleNews)
        val actualNews = dao.isNewsBookmarked("music")
        Assert.assertEquals(sampleNews[0].isBookmarked, actualNews)
    }
}

