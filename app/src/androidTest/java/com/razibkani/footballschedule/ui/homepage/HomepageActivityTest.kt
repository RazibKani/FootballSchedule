package com.razibkani.footballschedule.ui.homepage

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.razibkani.footballschedule.R.id.*
import com.razibkani.footballschedule.ui.prevmatch.PrevMatchViewHolder
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomepageActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomepageActivity::class.java)

    @Test
    fun testAppBehaviour() {
        onView(withId(navigation)).check(ViewAssertions.matches(isDisplayed()))

        onView(withId(loadingIndicator)).check(matches(ViewMatchers.isDisplayed()))

        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(listPrevMatch)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(listPrevMatch)).perform(RecyclerViewActions.scrollToPosition<PrevMatchViewHolder>(3))
        onView(withId(listPrevMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<PrevMatchViewHolder>(3, click()))

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to Favorite")).inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView)))).check(matches(isDisplayed()))
        pressBack()

        onView(withId(navigation)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(navigation_favorite_match)).perform(click())
    }
}