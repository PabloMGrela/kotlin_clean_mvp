package com.grela.clean

import androidx.test.rule.ActivityTestRule
import com.facebook.testing.screenshot.Screenshot
import java.lang.Thread.sleep

object ScreenshotInstruments {
    fun takeScreenShot(testRule: ActivityTestRule<*>, name: String) {
        sleep(500)
        Screenshot.snapActivity(testRule.activity).setName(name).record()
    }
}