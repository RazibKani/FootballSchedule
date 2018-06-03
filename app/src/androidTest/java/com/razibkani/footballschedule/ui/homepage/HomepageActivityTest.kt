package com.razibkani.footballschedule.ui.homepage

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.razibkani.footballschedule.R.id.listPrevMatch
import com.razibkani.footballschedule.R.id.navigation
import com.razibkani.footballschedule.ui.prevmatch.PrevMatchViewHolder
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
        onView(ViewMatchers.withId(navigation))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(listPrevMatch))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(listPrevMatch)).perform(RecyclerViewActions.scrollToPosition<PrevMatchViewHolder>(5))
        onView(ViewMatchers.withId(listPrevMatch))
                .perform(RecyclerViewActions.actionOnItemAtPosition<PrevMatchViewHolder>(5, ViewActions.click()))
    }

    /*@Test
    fun testRecyclerViewBehaviour() {
        Espresso.onView(ViewMatchers.withId(listEvent))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(listEvent)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Espresso.onView(ViewMatchers.withId(listEvent)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, ViewActions.click()))
    }

    @Test
    fun testAppBehaviour() {
        Espresso.onView(ViewMatchers.withId(spinner))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(spinner)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Spanish La Liga")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withText("Barcelona"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("Barcelona")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Added to favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()

        Espresso.onView(ViewMatchers.withId(bottom_navigation))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(favorites)).perform(ViewActions.click())
    }*/
}