package com.qautomatron.kopi.library.element.action

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.view.View
import android.widget.TextView
import org.hamcrest.Matcher

class GetTextAction : ViewAction {

    lateinit var text: CharSequence

    override fun getConstraints(): Matcher<View> {
        return isAssignableFrom(TextView::class.java)
    }

    override fun getDescription(): String {
        return "get text"
    }

    override fun perform(uiController: UiController, view: View) {
        val textView = view as TextView
        text = textView.text
    }

    fun getTexts(): CharSequence {
        return text
    }
}