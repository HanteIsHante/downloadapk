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


