package com.wzc.privacy.compliance.demo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAndroidID()
    }

    fun getImei(){
        "xxxx"
    }

    @SuppressLint("HardwareIds")
    private fun getAndroidID(){
        Settings.System.getString(contentResolver, Settings.System.ANDROID_ID)
        Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }
}