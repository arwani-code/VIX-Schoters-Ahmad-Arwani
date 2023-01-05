package com.arwani.ahmad.schotersnews.ui.detail


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.arwani.ahmad.schotersnews.DataDummy
import com.arwani.ahmad.schotersnews.MainDispatcherRule
import com.arwani.ahmad.schotersnews.data.NewsRepository
import com.arwani.ahmad.schotersnews.ui.category.CategoryViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class NewsDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var newsRepository: NewsRepository
    private lateinit var categoryViewModel: CategoryViewModel
    private val dummyEntity = DataDummy.generateDummyNewsEntity()[0]

    @Before
    fun setUp() {
        categoryViewModel = CategoryViewModel(newsRepository)
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