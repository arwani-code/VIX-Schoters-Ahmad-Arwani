package com.arwani.ahmad.schotersnews.ui.category

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.arwani.ahmad.schotersnews.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CategoryActivityTest{

    @get:Rule
    val activity = ActivityScenarioRule(CategoryActivity::class.java)

    @Test
    fun loadCategoryNews_Success() {
        onView(withId(R.id.rv_news)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_news)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
    }
}