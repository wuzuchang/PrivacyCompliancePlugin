package com.wzc.privacy.compliance.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAndroidID()
    }

    fun getImei(){
        "xxxx"
    }

    private fun getAndroidID(){
        Settings.System.getString(contentResolver, Settings.System.ANDROID_ID)
    }
}