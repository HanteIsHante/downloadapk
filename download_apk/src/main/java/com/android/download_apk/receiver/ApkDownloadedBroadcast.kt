package com.android.download_apk.receiver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import com.android.download_apk.constant.Constant
import com.android.download_apk.down.DownLoadApkManager
import com.android.download_apk.util.DownUtil
import java.io.File

/**
 *  CreateDate: 2018/6/12
 *  Desc: DownloadManager下载完成后会发出一个广播
 *        android.intent.action.DOWNLOAD_COMPLETE 新建一个广播接收者即可：
 */
class ApkDownloadedBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE) {
            val downloadApkId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (downloadApkId == Constant.DOWN_LOAD_APK_HAS_EXIST) return
            if (downloadApkId != DownLoadApkManager.getInstance().downLoadID) return
            val query = DownloadManager.Query()
            query.setFilterById(DownLoadApkManager.getInstance().downLoadID)
            val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val c = dm.query(query) ?: return
            if (c.moveToFirst()) {
                val columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS)
                // 下载失败也会返回这个广播，所以要判断下是否真的下载成功
                if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
                    // 获取下载好的 apk 路径
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        val s_path = DownLoadApkManager.getInstance().mApkPaths.get(DownLoadApkManager.getInstance().downLoadID)
                        val file = File(s_path)
                        DownUtil().AndroidNInstallApk(context, file)// Android N
                    } else { // Android N  以下
                        val path = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME))
                        val data = Uri.parse("file://$path")
                        DownUtil().AndroidMInstallApk(context, data)
                    }
                    DownLoadApkManager.getInstance().downLoadID = Constant.DOWN_LOAD_INIT_ID
                }
            }
            c.close()
        }
    }
}