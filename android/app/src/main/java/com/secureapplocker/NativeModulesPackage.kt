package com.secureapplocker

import android.annotation.SuppressLint
import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import com.secureapplocker.nativemodules.DBModule
import com.secureapplocker.nativemodules.HelperModule

class NativeModulesPackage : ReactPackage {

    override fun createViewManagers(
            reactContext: ReactApplicationContext
    ): MutableList<ViewManager<View, ReactShadowNode<*>>> = mutableListOf()

    @SuppressLint("SuspiciousIndentation")
    override fun createNativeModules(
            reactContext: ReactApplicationContext
    ): MutableList<NativeModule>{
     val modules = ArrayList<NativeModule>()
        modules.add(HelperModule(reactContext))
        modules.add(DBModule(reactContext))
        return modules
    }
}