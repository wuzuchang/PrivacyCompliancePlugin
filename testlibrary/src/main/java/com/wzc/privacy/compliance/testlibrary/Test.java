package com.wzc.privacy.compliance.testlibrary;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class Test {

    public Test(Context context) {
        getImei(context);
        getBlueMac(context);
        getAppList(context);
    }

    public String getImei(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return tm.getImei();
        } else {
            return tm.getDeviceId();
        }
    }

    public String getBlueMac(Context context){
        return Settings.System.getString(context.getContentResolver(),"bluetooth_address");
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void getAppList(Context context){
        PackageManager packageManager = context.getPackageManager();
        packageManager.getInstalledPackages(0);
    }
}
