# PrivacyCompliancePlugin
## 环境

基于gradle 7.3.+ 开发

## 功能

静态扫描Android项目java代码中隐私合规相关代码，会在构建时打印扫描到的隐私合规风险代码。也可通过配置插入日志，在运行时打印隐私合规风险代码调用栈。

## 使用

在项目跟目录下的`setting.gradle`文件中添加以下代码

```
maven { url 'https://jitpack.io' }
```

在项目跟目录下的`build.gradle`文件中添加以下代码

```
buildscript {
    dependencies {
        classpath 'com.github.wuzuchang:PrivacyCompliancePlugin:1.0.0'
    }
}
```

在app module下的`build.gradle`中添加以下代码

```
plugins {
    id 'com.android.application'
    // 合规检测插件
     id 'com.wzc.privacy_compliance'
}
//合规检测插件配置项
compliance{
    packageNameList = ["com.wzc"]
}
```
