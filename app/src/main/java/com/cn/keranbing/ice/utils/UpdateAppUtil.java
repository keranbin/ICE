package com.cn.keranbing.ice.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.keranbin.business.App;
import com.example.keranbin.business.okHttp.HttpCon;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

/**
 * Created by keranbin on 2016/8/9.
 */
public class UpdateAppUtil {

    /*
 * 判断当前app版本和服务器端版本是否一致
 * */
    public static boolean compareVersion(String newVersion) {
        PackageManager packageManager = App.getContext().getPackageManager();
        try {
            PackageInfo packag = packageManager.getPackageInfo(App.getContext().getPackageName(), 0);
            int oldVersionCode = packag.versionCode;
            String oldVersionName = packag.versionName;
            if (!newVersion.equals(oldVersionName)) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    /*
  * 检测是否需要更新app版本
  * */
    public static boolean testIfUpdate(String s) {
        try {
            if (!s.equals("")) {
                JSONObject jsonObject = new JSONObject(s);
                HttpCon.updateUrl = jsonObject.getString("url");
                if (UpdateAppUtil.compareVersion(jsonObject.getString("version"))) {
                    return true;
                } else {
                   return false;
                }
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  false;
    }


    /*
    * 安装新版本的App
    * */
    public static void installApp(Activity context, Response response){
        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filepath, "/download/attence.apk");
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        byte[] buff = new byte[1024 * 4];
        InputStream inputStream = response.body().byteStream();
        int len = 0;
        int lens = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            while ((len = inputStream.read(buff)) != -1) {
                Log.i("dd", len + "");
                fileOutputStream.write(buff, 0, len);
                lens += len;
            }
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivityForResult(intent,1);
    }
}
