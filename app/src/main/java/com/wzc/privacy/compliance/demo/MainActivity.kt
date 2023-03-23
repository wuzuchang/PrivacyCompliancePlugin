package com.wzc.privacy.compliance.demo

import android.Manifest
import android.annotation.SuppressLint
import android.app.Service
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.wzc.privacy.compliance.testlibrary.Test

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("wzc==", "imei=${getImei()}, " + "android_id=${getAndroidID()}")
        Test(this)
    }

    private fun getImei(): String {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return "unknow"
        }
        val tm = this.getSystemService(Service.TELEPHONY_SERVICE) as TelephonyManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tm.imei
        } else {
            tm.deviceId
        }
    }

    @SuppressLint("HardwareIds")
    private fun getAndroidID(): String {
        Settings.System.getString(contentResolver, Settings.System.ANDROID_ID)
        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }
}