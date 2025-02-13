package com.example.storidemoapp.utils

import androidx.test.espresso.IdlingRegistry
import com.example.shared.idle.CountingIdling
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class IdlingTestRule : TestWatcher() {
    override fun starting(description: Description) {
        super.starting(description)
        IdlingRegistry
            .getInstance()
            .register(
                CountingIdling.countingIdlingResource
            )
    }

    override fun finished(description: Description) {
        super.finished(description)
        IdlingRegistry
            .getInstance()
            .unregister(CountingIdling.countingIdlingResource)
    }
}