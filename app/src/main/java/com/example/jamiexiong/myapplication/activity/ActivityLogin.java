package com.example.jamiexiong.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.Util.Md5Util;
import com.example.jamiexiong.myapplication.Util.RequestCode;
import com.example.jamiexiong.myapplication.Util.UrlUtil;
import com.example.jamiexiong.myapplication.bean.ResultCode;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mylhyl.circledialog.CircleDialog;

import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jamiexiong on 2017/6/7.
 */

@ContentView(R.layout.activity_login_dark)
public class ActivityLogin extends FragmentActivity {

    @ViewInject(R.id.edt)
    private EditText phoneText;

    private RequestQueue mQueue;

    @ViewInject(R.id.edt_password)
    private EditText passwordText;

    @ViewInject(R.id.button6)
    private Button loginButton;

    @ViewInject(R.id.to_register_txt)
    private TextView registerText;

    private KProgressHUD hud;

    private final String url = "http://"+ UrlUtil.url1+"/mobile/getinfo/loginf/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mQueue = Volley.newRequestQueue(this);
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);
        x.view().inject(this);
        initLayout();

    }

    private void initLayout(){
       /* Drawable drawable1 = getResources().getDrawable(R.drawable.loginuser);
        drawable1.setBounds(0, 0, 40, 40);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        phoneText.setCompoundDrawables(drawable1, null, null, null);//只放左边
        Drawable drawable2 = getResources().getDrawable(R.drawable.loginpass);
        drawable2.setBounds(0, 0, 40, 40);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        passwordText.setCompoundDrawables(drawable2, null, null, null);//只放左边*/
    }

    @Event(value={R.id.button6,R.id.to_register_txt})
    private void getEvent(View view) {
        final String phone;
        final String password;
        switch (view.getId()) {
            case R.id.to_register_txt:
                startActivityForResult(new Intent(ActivityLogin.this,ActivityRegister1.class), RequestCode.REGISTER);
                break;
            case R.id.button6:
                phone = phoneText.getText().toString();
                password = passwordText.getText().toString();
                if(phone.equals("")||password.equals("")){
                    new CircleDialog.Builder(ActivityLogin.this)
                            .setTitle("提示")
                            .setText("请完善用户名或密码!")
                            .setPositive("确定", null)
                            .show();
                }else{

                    hud.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            hud.dismiss();
                            ResultCode resultCode = new Gson().fromJson(response.toString(),ResultCode.class);

                            Log.d("TAG", response.toString());

                            if(resultCode.getCode().equals("200")){
                                SharedPreferences sp = ActivityLogin.this.getSharedPreferences("app",MODE_PRIVATE);
                                //获取编辑对象
                                SharedPreferences.Editor edit = sp.edit();
                                //添加值
                                edit.putString("phone",phone);
                                edit.putString("password",password);
                                //提交
                                edit.commit();
                                new CircleDialog.Builder(ActivityLogin.this)
                                        .setTitle("提示")
                                        .setText("登录成功!")
                                        .setPositive("确定", null)
                                        .show();
                                startActivity(new Intent(ActivityLogin.this,ActivityHome.class));
                                finish();
                            }else{
                                new CircleDialog.Builder(ActivityLogin.this)
                                        .setTitle("提示")
                                        .setText("用户名或密码错误!")
                                        .setPositive("确定", null)
                                        .show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            hud.dismiss();
                            Log.e("TAG", error.getMessage(), error);
                            new CircleDialog.Builder(ActivityLogin.this)
                                    .setTitle("提示")
                                    .setText("网络发生异常!")
                                    .setPositive("确定", null)
                                    .show();
                        }}) {
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
                break;
        }
    }

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 500);
    }
}
