package com.qautomatron.kopi.sample

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.qautomatron.kopi.library.element.Element
import com.qautomatron.kopi.library.matcher.first
import org.junit.Rule
import org.junit.Test

/**
 * Check for ElementAction
 */
class ElementMatcherTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    private val element = Element(first(withId(R.id.sameId1)))

    @Test
    fun should_get_first_element() {
        element.sameAs("Text1")
    }
}
