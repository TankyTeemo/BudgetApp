package com.example.android.budgetapp.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.android.budgetapp.R
import kotlinx.android.synthetic.main.fragment_calendar_screen.view.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var mainActivity: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }
    // Navigation Tests
    @Test
    fun navigateToPieChart() {
        onView(withId(R.id.graph_button)).perform(click())
        onView(withId(R.id.pieChart)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun navigateToCategoryScreen() {
        onView(withId(R.id.expense_screen_button)).perform(click())
        onView(withId(R.id.submit_button)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun navigateUpTest(){
        onView(withId(R.id.expense_screen_button)).perform(click())
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.graph_button)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun addExpenditure(){

    }

    @Test
    fun addAndDeleteCategory(){

    }

    @Test
    fun pieChartGetsCategories(){

    }
}