package com.arwani.ahmad.schotersnews


import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.arwani.ahmad.schotersnews.navigation.Screen
import com.arwani.ahmad.schotersnews.ui.JetNewsApp
import com.arwani.ahmad.schotersnews.ui.favorite.FavoriteActivity
import com.arwani.ahmad.schotersnews.ui.theme.SchotersNewsTheme
import com.arwani.ahmad.schotersnews.utils.assertCurrentRouteName
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class JetSchotersNewsTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var navController: TestNavHostController

    @Before
    fun setupNewsNavHost() {
        composeTestRule.setContent {
            SchotersNewsTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                JetNewsApp(navController = navController)
            }
        }
    }

    @Test
    fun newsNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.splash.route)
    }
}