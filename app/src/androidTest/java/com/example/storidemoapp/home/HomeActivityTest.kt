package com.example.storidemoapp.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.shared.idle.CountingIdling
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import com.example.storidemoapp.R
import com.example.storidemoapp.ui.home.activity.HomeActivity
import org.junit.Test

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {
    @Before
    fun setup() {
        IdlingRegistry
            .getInstance()
            .register(
                CountingIdling.countingIdlingResource
            )
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @After
    fun finish() {
        IdlingRegistry
            .getInstance()
            .unregister(CountingIdling.countingIdlingResource)
    }

    @Test
    fun test_is_activity_displayed() {
        onView(withId(R.id.card_detail_main_content_scrv)).check(matches(isDisplayed()))
    }

    @Test
    fun test_a_Recycler_view_when_fetch_data_from_network() {
        onView(withId(R.id.card_detail_rv))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        onView(withId(R.id.card_detail_prg_loader))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
    }
}
