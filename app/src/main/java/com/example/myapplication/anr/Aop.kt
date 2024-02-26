package com.example.myapplication.anr

import android.app.Activity
import android.os.Looper
import com.example.myapplication.MainActivity

fun injectMainLooper(call: (isStart: Boolean, msg: String) -> Unit) {
    Looper.getMainLooper().setMessageLogging {
        if (it.startsWith(">>>>>")) {
            call(true, it)
        } else {
            call(false, it)
        }
    }
}

fun injectInputMsg(activity: Activity) {
    activity.window.decorView.post {
        var root = activity.window.decorView.parent
        while (root.parent != null) {
            root = root.parent
        }
        /*val inputEventReceiverField = root::class.java.getDeclaredField("mInputEventReceiver")
        val inputEventReceiver=inputEventReceiverField.get(root)*/
    }
}