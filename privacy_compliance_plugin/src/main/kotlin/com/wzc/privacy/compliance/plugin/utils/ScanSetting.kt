package com.wzc.privacy.compliance.plugin.utils

class ScanSetting {
    companion object {
        // 需要传参类型的方法，如：Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        var sSettingsRiskMethodsList = ArrayList<RiskMethod>()

        // 不要要传参类型的方法
        var sRiskMethodsList = ArrayList<RiskMethod>()

        init {
            //android id
            sSettingsRiskMethodsList.add(
                RiskMethod(
                    "android/provider/Settings\$Secure",
                    "getString",
                    "(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;",
                    "android_id",
                    null
                )
            )
            //android id
            sSettingsRiskMethodsList.add(
                RiskMethod(
                    "android/provider/Settings\$System",
                    "getString",
                    "(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;",
                    "android_id",
                    null
                )
            )
            //blueMac
            sSettingsRiskMethodsList.add(
                RiskMethod(
                    "android/provider/Settings\$Secure",
                    "getString",
                    "(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;",
                    "bluetooth_address",
                    null
                )
            )
            //blueMac
            sSettingsRiskMethodsList.add(
                RiskMethod(
                    "android/provider/Settings\$System",
                    "getString",
                    "(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;",
                    "bluetooth_address",
                    null
                )
            )
            //android.bluetooth.BluetoothAdapter.getAddress()
            sRiskMethodsList.add(
                RiskMethod(
                    "android/bluetooth/BluetoothAdapter",
                    "getAddress",
                    "()Ljava/lang/String;",
                    null,
                    "bluetooth_address"
                )
            )
            //android.telephony.TelephonyManager.getSimOperatorName()
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager",
                    "getSimOperatorName",
                    "()Ljava/lang/String;",
                    null,
                    "运营商名称"
                )
            )
            //android.telephony.TelephonyManager.getDeviceId(int)
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager",
                    "getDeviceId",
                    "(I)Ljava/lang/String;",
                    null,
                    "imei"
                )
            )
            //android.telephony.TelephonyManager.getDeviceId()
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager",
                    "getDeviceId",
                    "()Ljava/lang/String;",
                    null,
                    "imei"
                )
            )
            //android.telephony.TelephonyManager.getImei(int)
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager",
                    "getImei",
                    "(I)Ljava/lang/String;",
                    null,
                    "imei"
                )
            )
            //android.telephony.TelephonyManager.getImei()
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager",
                    "getImei",
                    "()Ljava/lang/String;",
                    null,
                    "imei"
                )
            )
            //android.telephony.TelephonyManager.getSimState(int)
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager", "getSimState", "(I)I", null, "sim卡状态"
                )
            )
            //android.telephony.TelephonyManager.getSimState()
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager", "getSimState", "()I", null, "sim卡状态"
                )
            )
            //android.telephony.TelephonyManager.getSimOperator(int)
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager",
                    "getSimOperator",
                    "(I)Ljava/lang/String;",
                    null,
                    "MCC+MNC"
                )
            )
            //android.telephony.TelephonyManager.getSimOperator()
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager",
                    "getSimOperator",
                    "()Ljava/lang/String;",
                    null,
                    "MCC+MNC"
                )
            )
            //android.telephony.TelephonyManager.getLine1Number()
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager",
                    "getLine1Number",
                    "()Ljava/lang/String;",
                    null,
                    "手机号"
                )
            )
            //android.telephony.TelephonyManager.getSimSerialNumber(int)
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager",
                    "getSimSerialNumber",
                    "(I)Ljava/lang/String;",
                    null,
                    "sim卡 serial number "
                )
            )
            //android.telephony.TelephonyManager.getSimSerialNumber()
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager",
                    "getSimSerialNumber",
                    "()Ljava/lang/String;",
                    null,
                    "sim卡 serial number"
                )
            )
            //android.telephony.TelephonyManager.getSubscriberId(int)
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager",
                    "getSubscriberId",
                    "(I)Ljava/lang/String;",
                    null,
                    "IMSI（subscriber ID）"
                )
            )
            //android.telephony.TelephonyManager.getSubscriberId()
            sRiskMethodsList.add(
                RiskMethod(
                    "android/telephony/TelephonyManager",
                    "getSubscriberId",
                    "()Ljava/lang/String;",
                    null,
                    "IMSI（subscriber ID）"
                )
            )
            //android.net.wifi.WifiInfo.getSSID()
            sRiskMethodsList.add(
                RiskMethod(
                    "android/net/wifi/WifiInfo", "getSSID", "()Ljava/lang/String;", null, "wifi名称"
                )
            )
            //android.net.wifi.WifiInfo.getBSSID()
            sRiskMethodsList.add(
                RiskMethod(
                    "android/net/wifi/WifiInfo",
                    "getBSSID",
                    "()Ljava/lang/String;",
                    null,
                    "wifi bssid"
                )
            )
            //android.net.wifi.WifiInfo.getMacAddress()
            sRiskMethodsList.add(
                RiskMethod(
                    "android/net/wifi/WifiInfo",
                    "getMacAddress",
                    "()Ljava/lang/String;",
                    null,
                    "wifiMac"
                )
            )
            //android.content.pm.PackageManager.getInstalledPackages(int)
            sRiskMethodsList.add(
                RiskMethod(
                    "android/content/pm/PackageManager",
                    "getInstalledPackages",
                    "(I)Ljava/util/List;",
                    null,
                    "安装包列表"
                )
            )
            //android.content.pm.PackageManager.queryIntentActivities(Intent, int)
            sRiskMethodsList.add(
                RiskMethod(
                    "android/content/pm/PackageManager",
                    "queryIntentActivities",
                    "(Landroid/content/Intent;I)Ljava/util/List;",
                    null,
                    "安装包列表"
                )
            )
            //android.content.pm.PackageManager.getPackageInfo(String, int)
            sRiskMethodsList.add(
                RiskMethod(
                    "android/content/pm/PackageManager",
                    "getPackageInfo",
                    "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;",
                    null,
                    "安装包信息"
                )
            )
            //android.content.pm.PackageManager.getPackageInfo(String, PackageInfoFlags)
            sRiskMethodsList.add(
                RiskMethod(
                    "android/content/pm/PackageManager",
                    "getPackageInfo",
                    "(Ljava/lang/String;Landroid/content/pm/PackageManager/PackageInfoFlags;)Landroid/content/pm/PackageInfo;",
                    null,
                    "安装包信息"
                )
            )

        }
    }

    class RiskMethod(
        var owner: String,
        var name: String,
        var descriptor: String,
        var value: String?,
        var output: String?
    )
}