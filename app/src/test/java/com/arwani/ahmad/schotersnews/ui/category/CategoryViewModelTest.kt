package com.arwani.ahmad.schotersnews.ui.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.arwani.ahmad.schotersnews.DataDummy
import com.arwani.ahmad.schotersnews.data.NewsRepository
import com.arwani.ahmad.schotersnews.data.Result
import com.arwani.ahmad.schotersnews.data.local.entity.NewsEntity
import com.arwani.ahmad.schotersnews.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoryViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var newsRepository: NewsRepository
    private lateinit var categoryViewModel: CategoryViewModel
    private val dummyNews = DataDummy.generateDummyNewsEntity()

    @Before
    fun setUp() {
        categoryViewModel = CategoryViewModel(newsRepository)
    }

    @Test
    fun `when Get getNewsData Should Not Null and Return Success`() {
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

}