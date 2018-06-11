package com.android.download_apk.util

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat

/**
 *  CreateDate: 2018/6/11
 *  Desc: 权限检测
 */
class PermissionCheck(var context: Context) {

    /**
     * 判断权限 集合是否获得允许
     *
     * @param permissions 权限集合
     * @return true 获得允许 false 未获得允许
     */
    fun checkPermissions(vararg permissions: String): Boolean {
        for (permission in permissions) {
            if (grantedPermission(permission)) {
                return true
            }
        }
        return false
    }


    /**
     * 检测某个权限是否 已经被授予
     * @param permission 权限名称
     * @return boolean true: 权限已经授予， false: 未授予
     */
    private fun grantedPermission(permission: String): Boolean {
        return PackageManager.PERMISSION_GRANTED ==
                ContextCompat.checkSelfPermission(context, permission)
    }

}