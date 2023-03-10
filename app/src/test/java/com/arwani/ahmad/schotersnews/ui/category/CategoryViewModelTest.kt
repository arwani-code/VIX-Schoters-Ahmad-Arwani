package com.arwani.ahmad.schotersnews.ui.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.arwani.ahmad.schotersnews.DataDummy
import com.arwani.ahmad.schotersnews.MainDispatcherRule
import com.arwani.ahmad.schotersnews.data.NewsRepository
import com.arwani.ahmad.schotersnews.data.Result
import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity
import com.arwani.ahmad.schotersnews.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CategoryViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var newsRepository: NewsRepository
    private lateinit var categoryViewModel: CategoryViewModel
    private val dummyNews = DataDummy.generateDummyNewsEntity()
    private val dummyEntity = DataDummy.generateDummyNewsEntity()[0]

    @Before
    fun setUp() {
        categoryViewModel = CategoryViewModel(newsRepository)
    }

    @Test
    fun `when Get getNews Should Not Null and Return Success`() {
        val expectedNews = MutableLiveData<Result<List<NewsEntity>>>()
        expectedNews.value = Result.Success(dummyNews)
        `when`(newsRepository.getNewsData("entertainment")).thenReturn(expectedNews)
        val actualNews = categoryViewModel.getNews("entertainment").getOrAwaitValue()
        Mockito.verify(newsRepository).getNewsData("entertainment")
        Assert.assertNotNull(actualNews)
        Assert.assertTrue(actualNews is Result.Success)
        Assert.assertEquals(dummyNews.size, (actualNews as Result.Success).data.size)
    }

    @Test
    fun `when Network Error Should Return Error`() {
        val categoryNews = MutableLiveData<Result<List<NewsEntity>>>()
        categoryNews.value = Result.Error("Error")
        `when`(newsRepository.getNewsData("entertainment")).thenReturn(categoryNews)
        val actualNews = categoryViewModel.getNews("entertainment").getOrAwaitValue()
        Mockito.verify(newsRepository).getNewsData("entertainment")
        Assert.assertNotNull(actualNews)
        Assert.assertTrue(actualNews is Result.Error)
    }

    @Test
    fun `when bookmarkStatus true`() = runTest {
        val expectedBoolean = MutableLiveData<Boolean>()
        expectedBoolean.value = true
        categoryViewModel.saveNews(dummyEntity)
        Mockito.verify(newsRepository).setNewsBookmark(dummyEntity, expectedBoolean.value!!)
    }

    @Test
    fun `when bookmarkStatus false`() = runTest {
        val expectedBoolean = MutableLiveData<Boolean>()
        expectedBoolean.value = false
        categoryViewModel.deleteNews(dummyEntity)
        Mockito.verify(newsRepository).setNewsBookmark(dummyEntity, expectedBoolean.value!!)
    }
}