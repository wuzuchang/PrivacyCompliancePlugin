# PrivacyCompliancePlugin
## 环境

基于gradle 7.3.+ 开发

## 功能

静态扫描Android项目java代码中隐私合规相关代码，会在构建时打印扫描到的隐私合规风险代码。也可通过配置插入日志，在运行时打印隐私合规风险代码调用栈。

## 使用

在项目跟目录下的`setting.gradle`文件中添加以下代码

```groovy
maven { url 'https://jitpack.io' }
```

在项目跟目录下的`build.gradle`文件中添加以下代码

```groovy
buildscript {
    dependencies {
        classpath 'com.github.wuzuchang:PrivacyCompliancePlugin:1.0.0'
    }
}
```

在app module下的`build.gradle`中添加以下代码

```groovy
plugins {
    id 'com.android.application'
    // 合规检测插件
    id 'com.wzc.privacy_compliance'
}
//合规检测插件配置项
compliance{
    packageNameList = ["com.wzc"]
    insertLog = true
    logTag = "Test"
}
```

配置项参数说明

| 参数            | 类型      | 含义                                              |
| --------------- |---------| ------------------------------------------------- |
| packageNameList | List    | 需要检测的包名列表                                |
| insertLog       | Boolean | 是否在检测到隐私合规代码时插入Log，会在运行时打印 |
| logTag          | String  | 运行时Log打印的TAG                                |

编译时检测结果

![build1](resource\build1.png)

![build2](resource\build2.png)

运行时检测结果

![running](resource\running.png)
