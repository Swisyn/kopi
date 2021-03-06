package com.qautomatron.kopi.library.wait

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.Root
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import com.azimolabs.conditionwatcher.ConditionWatcher.waitForCondition
import com.azimolabs.conditionwatcher.Instruction
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

/**
 * Used by the waitForView() shorthand fluent function
 */
class ViewMatcherWaiter constructor(val viewMatcher: Matcher<View>, val inRoot: Matcher<Root>) {

    private val desc: StringDescription
        get() {
            val desc = StringDescription()
            desc.appendText("Wait for view ")
            viewMatcher.describeTo(desc)
            if (inRoot != RootMatchers.DEFAULT) {
                desc.appendText(" in root ")
                inRoot.describeTo(desc)
            }
            return desc
        }

    /**
     * Specify the Espresso matches which will satisfy the condition
     */
    fun toMatch(viewChecker: Matcher<View>) =
        waitForCondition(object : Instruction() {
            override fun getDescription(): String {
                val desc = desc
                desc.appendText(" to match ")
                viewChecker.describeTo(desc)
                return desc.toString()
            }

            override fun checkCondition(): Boolean {
                return try {
                    onView(viewMatcher).inRoot(inRoot).check(matches(viewChecker))
                    true
                } catch (e: AssertionError) {
                    false
                } catch (e: NoMatchingViewException) {
                    false
                }
            }
        })

    fun toCheck(viewAssertion: ViewAssertion) =
        waitForCondition(object : Instruction() {
            override fun getDescription(): String = desc.appendText(" to check ").toString()

            override fun checkCondition(): Boolean {
                return try {
                    onView(viewMatcher).inRoot(inRoot).check(viewAssertion)
                    true
                } catch (e: AssertionError) {
                    false
                }
            }
        })
}