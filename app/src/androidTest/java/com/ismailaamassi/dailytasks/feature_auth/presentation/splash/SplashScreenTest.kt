package com.ismailaamassi.dailytasks.feature_auth.presentation.splash

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ismailaamassi.dailytasks.core.presentation.MainActivity
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SplashScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @RelaxedMockK
    lateinit var navigator: DestinationsNavigator

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `splashScreen_displayAndDisappears`() = runBlockingTest{
       /* composeTestRule.setContent {
            DailyTasksTheme {
                SplashScreen()
            }
        }
        composeTestRule
            .onNodeWithContentDescription("Daily Tasks")
            .assertExists()

        advanceTimeBy(Constants.SPLASH_SCREEN_DELAY)

        verify {
            navigator.popBackStack()
            navigator.navigate(LandingScreenDestination.route)
        }*/
    }
}