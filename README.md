# downloadapk


### Step 1. Add the JitPack repository to your build file

> Add it in your root build.gradle at the end of repositories:

```
allprojects {
     repositories {
              ...
              maven { url 'https://jitpack.io' }
              }
     }

```

### Step 2. Add the dependency

```
 dependencies {
        implementation 'com.github.HanteIsHante:downloadapk:0.0.1'
    }
```




### 使用


###### PermissionCheck

> 检测权限是否被授予

```
/**
* 判断权限 集合是否获得允许
*
* @param permissions 权限集合
* @return true 获得允许 false 未获得允许
*/
checkPermissions(vararg permissions: String)

```

###### 调用系统下载功能

> 初始化

```
val downLoadApkManager = DownLoadApkManager.getInstance()
```

> 调用下载

```
val downLoadState:Long = downLoadApkManager.startDownLoad(context,
                    Apkurl, ApkName, ApkMd5, ApkDesc)
```

通过md5 来判断本地是否已经存在此apk, 若是存在，直接调用安装程序进行安装

>下载状态 downLoadState

   1. DOWN_LOAD_INIT_ID: 下载中
   2. DOWN_LOAD_MANAGER_UNABLE_USE: 系统下载管理器禁用状态
   3. DOWN_LOAD_APK_HAS_EXIST:  apk 已经存在, 直接进行安装
   4. EXTERNAL_STORAGE_NOT_EXIST: 下载位置文件夹不可用
   
   
   
#### apk 存储路径：Context.getExternalFilesDir

获取路径:/storage/emulated/0/Android/data/应用包名/files/Download

默认存在，可读写。(6.0系统可以不用向用户申请)   
   
   
