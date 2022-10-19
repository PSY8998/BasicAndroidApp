package com.example.basicandroidapp.services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class MyAccessibilityService: AccessibilityService() {

    override fun onInterrupt() {
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d("accessibility", "onAccessibilityEvent:")
        if(event?.eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED){
            if(event?.packageName.toString() == "com.whatsapp"){
                Toast.makeText(applicationContext, "whatsapp launched", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onServiceConnected() {
        serviceInfo.apply{
            eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or AccessibilityEvent.TYPE_VIEW_FOCUSED or AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
            packageNames = arrayOf("com.example.BasicAndroidApp", "com.whatsapp")
            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN
            notificationTimeout = 100
        }
    }
}