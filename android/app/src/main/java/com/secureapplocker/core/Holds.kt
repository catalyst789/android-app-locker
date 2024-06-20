package com.secureapplocker.core

import com.facebook.react.bridge.ReactApplicationContext

object Holds {
    var showLockScreenIntent: Boolean = false
    var whichAppToBeUnlocked: String = ""
    var reactApplicationContext: ReactApplicationContext? = null
}