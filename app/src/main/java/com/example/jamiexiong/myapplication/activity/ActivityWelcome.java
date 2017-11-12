package com.example.jamiexiong.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.Util.Md5Util;
import com.example.jamiexiong.myapplication.Util.UrlUtil;
import com.example.jamiexiong.myapplication.application.MApplication;
import com.example.jamiexiong.myapplication.bean.DevideDetailBean;
import com.example.jamiexiong.myapplication.bean.ResultCode;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mylhyl.circledialog.CircleDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jamiexiong on 2017/6/19.
 */
@ContentView(R.layout.activity_welcome)
public class ActivityWelcome extends FragmentActivity {
    private int hasLogin = 0;  //0:没有登录 1：登录了 2：第一次打开
    private RequestQueue mQueue;
    private KProgressHUD hud;

    private final String url = "http://"+ UrlUtil.url1+"/mobile/getinfo/loginf/";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        x.view().inject(this);
        mQueue = Volley.newRequestQueue(this);
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);
        new TimeTask().execute("");

    }

    class TimeTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                SharedPreferences sp = ActivityWelcome.this.getSharedPreferences("app",MODE_PRIVATE);
                final String phone = sp.getString("phone","");
                final String password = sp.getString("password","");
                final String hasOpened = sp.getString("open1","");
                if(hasOpened.equals("")||hasOpened.equals("false")){
                    hasLogin = 2;
                    SharedPreferences.Editor edit = sp.edit();
                    //添加值
                    edit.putString("open1","true");
                    //提交
                    edit.commit();
                }else {
                    if (phone.equals("") || password.equals("")) {
                        Log.d("ActivityWelcome", "没有登录");
                    } else {
                        hasLogin = 1;
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                hud.dismiss();
                                ResultCode resultCode = null;

                                try {
                                    resultCode = new Gson().fromJson(response.toString(), ResultCode.class);
                                }catch(Exception e){
                                    Log.e("TAG", e.getMessage(), e);

                                    new CircleDialog.Builder(ActivityWelcome.this)
                                            .setTitle("提示")
                                            .setText("网络发生异常")
                                            .setPositive("确定", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    finish();
                                                }
                                            })
                                            .show();
                                }
                                if(resultCode ==null){

                                    new CircleDialog.Builder(ActivityWelcome.this)
                                            .setTitle("提示")
                                            .setText("网络发生异常")
                                            .setPositive("确定", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    finish();
                                                }
                                            })
                                            .show();
                                    return;
                                }
                                Log.d("TAG", response.toString());

                                if (resultCode.getCode().equals("200")) {
                                    MApplication.setUser(resultCode);
                                    startActivity(new Intent(ActivityWelcome.this, ActivityHome.class));
                                    finish();
                                } else {
                                    Toast.makeText(ActivityWelcome.this, "账号发生改变，请重新登录", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ActivityWelcome.this, ActivityLogin.class));
                                    finish();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                hud.dismiss();
                                Log.e("TAG", error.getMessage(), error);
                                new CircleDialog.Builder(ActivityWelcome.this)
                                        .setTitle("提示")
                                        .setText("网络发生异常!")
                                        .setPositive("确定", null)
                                        .show();
                                finish();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("phone", phone);
                                map.put("password", Md5Util.md5(password));
                                Log.d("登录密码:", Md5Util.md5(password));
                                return map;
                            }
                        };
                        mQueue.add(stringRequest);
                    }
                }
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String result){
            if(hasLogin == 2){
                startActivity(new Intent(ActivityWelcome.this,ActivityYindao.class));
                finish();
            }else {
                if (hasLogin == 0) {
                    startActivity(new Intent(ActivityWelcome.this, ActivityLogin.class));
                    finish();
                }
            }
        }
        @Override
        protected void onPreExecute(){


        }

    }
}
