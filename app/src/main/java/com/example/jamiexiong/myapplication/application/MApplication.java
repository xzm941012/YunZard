package com.example.jamiexiong.myapplication.application;

import android.app.Application;
import android.content.SharedPreferences;

import com.tencent.bugly.crashreport.CrashReport;

import org.xutils.BuildConfig;
import org.xutils.x;

import cn.smssdk.SMSSDK;


/**
 * Created by 真爱de仙 on 2015/6/22.
 */
public class MApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        CrashReport.initCrashReport(getApplicationContext(), "4af63e6c25", false);
        //SMSSDK.initSDK(this, "1e826e60d57f8", "03153ec080352fc5b837a95a7af081b7");
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }


    public void setStringValue(String source, String key ,String value){
        SharedPreferences sp = getSharedPreferences(source,MODE_PRIVATE);
        //获取编辑对象
        SharedPreferences.Editor edit = sp.edit();
        //添加值
        edit.putString(key,value);
        //提交
        edit.commit();
    }

    public String getStringValue(String source, String key){
        SharedPreferences sp = getSharedPreferences(source,MODE_PRIVATE);
        return sp.getString(key,"");
    }


}
