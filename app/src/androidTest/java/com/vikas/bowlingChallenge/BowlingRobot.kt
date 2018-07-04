package com.vikas.bowlingChallenge

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.vikas.bowlingChallenge.help.TestUtil.withRecyclerView
import com.vikas.bowlingChallenge.R
import com.vikas.bowlingChallenge.FrameViewHolder
import com.vikas.bowlingChallenge.help.TestUtil.withRecyclerView

class BowlingRobot {
    fun pressRoll(): BowlingRobot {
        onView(withId(R.id.submitButton)).perform(click())
        return this
    }

    fun enterNumber(pinsDown: Int): BowlingRobot {
        onView(withId(R.id.editText)).perform(clearText())
                .perform(click())
                .perform(typeText("$pinsDown"))

        return this
    }

    fun enterNumbers(vararg sequence: Int): BowlingRobot {
        sequence.forEach {
            enterNumber(it).pressRoll()
        }
        return this
    }

    fun scrollTo(position: Int): BowlingRobot {
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToPosition<FrameViewHolder>(position))
        return this
    }

    fun assertCumulativeScoreFor(position: Int, isEqualTo: Int): BowlingRobot {
        scrollTo(position)
        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(position, R.id.totalTextView))
                .check(matches(withText("$isEqualTo")))
        return this
    }
}