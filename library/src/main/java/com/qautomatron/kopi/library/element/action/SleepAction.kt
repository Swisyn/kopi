package com.qautomatron.kopi.library.element.action

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.view.View
import org.hamcrest.Matcher


object SleepAction {

    fun sleep(millis: Long): ViewAction {
        return object : ViewAction {

            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "Wait for at least $millis millis"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }
}