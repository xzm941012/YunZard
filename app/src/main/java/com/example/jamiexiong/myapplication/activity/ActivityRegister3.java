package com.example.jamiexiong.myapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.example.jamiexiong.myapplication.Util.ResultCode;
import com.example.jamiexiong.myapplication.Util.UrlUtil;
import com.example.jamiexiong.myapplication.bean.GroupBean;
import com.example.jamiexiong.myapplication.bean.JobBean;
import com.example.jamiexiong.myapplication.bean.RegisterBean;
import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jamiexiong on 2017/6/7.
 */

@ContentView(R.layout.activity_register3)
public class ActivityRegister3 extends FragmentActivity {

    @ViewInject(R.id.editText12)
    private EditText nameText;

    private RequestQueue mQueue;

    @ViewInject(R.id.editText)
    private EditText zhiweiText;

    @ViewInject(R.id.checkBox3)
    private CheckBox tiaokuanCheckBox;

    @ViewInject(R.id.button2)
    private Button nextButton;

    @ViewInject(R.id.nicespinner)
    MaterialSpinner groupSpinner;

    @ViewInject(R.id.nicespinner1)
    MaterialSpinner jobSpinner;

    private KProgressHUD hud;

    private String groupLx = "";

    private String jobLx = "";

    private final String getGroupUrl = "http://"+ UrlUtil.url1+"/request?rname=i_plc.Page.mobile.user.Industry.TypeGroupList";

    private final String getJobUrl = "http://"+UrlUtil.url1+"/request?rname=i_plc.Page.mobile.user.Industry.TypeListList";

    private final String registerUrl = "http://"+UrlUtil.url1+"/request?rname=i_plc.Page.mobile.user.Register.AddUser2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        x.view().inject(this);
        init();
    }

    private void init(){
        initlayout();
        initData();
    }

    private void initData(){

        getJobGroups();
    }

    private void initlayout(){
        mQueue = Volley.newRequestQueue(this);
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);

        groupSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                groupLx = item;
                getJobs(groupLx);
            }
        });
        jobSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                jobLx = item;

                Toast.makeText(ActivityRegister3.this,"Clicked " + item, Toast.LENGTH_LONG).show();
            }
        });
        Log.d("获取Intent", getIntent().getStringExtra("erCode"));
    }

    @Event(value={R.id.button2})
    private void getEvent(View view) {


        switch (view.getId()) {
            case R.id.button2:
                if(groupLx.equals("")||jobLx.equals("")){
                    Toast.makeText(ActivityRegister3.this,"请填写完整资料",Toast.LENGTH_SHORT).show();
                }else if(!tiaokuanCheckBox.isChecked()){
                    Toast.makeText(ActivityRegister3.this,"请勾选同意条款",Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(ActivityRegister3.this,"注册成功",Toast.LENGTH_SHORT).show();
                    //finish();
                    pushRegister();
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

    //获取行业分组
    private void getJobGroups(){
        hud.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getGroupUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hud.dismiss();
                GroupBean resultCode = new Gson().fromJson(response.toString(),GroupBean.class);

                Log.d("TAG", response.toString());

                if(resultCode.getCode() == 200){
                    List<String> groups = new ArrayList<>();
                    for(GroupBean.ResultBean resultBean : resultCode.getResult()){
                        groups.add(resultBean.getTypeName());
                    }
                    groupSpinner.setItems(groups);
                    if(groups.size()>0){
                        groupLx = groups.get(0);
                        getJobs(groups.get(0));
                    }
                }else{
                    Toast.makeText(ActivityRegister3.this,"网络出错",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hud.dismiss();
                Log.e("TAG", error.getMessage(), error);

            }});
        mQueue.add(stringRequest);
    }
    //获取行业分组
    private void getJobs(final String group){
        hud.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getJobUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hud.dismiss();
                JobBean resultCode = new Gson().fromJson(response.toString(),JobBean.class);

                Log.d("TAG", response.toString());

                if(resultCode.getCode() == 200){
                    List<String> groups = new ArrayList<>();
                    for(JobBean.ResultBean resultBean : resultCode.getResult()){
                        groups.add(resultBean.getText());
                    }
                    if(groups.size()>0){
                        jobLx = groups.get(0);
                    }
                    jobSpinner.setItems(groups);
                }else{
                    Toast.makeText(ActivityRegister3.this,"网络出错",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hud.dismiss();
                Log.e("TAG", error.getMessage(), error);

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("TypeName", group);
                return map;
            }
        };
        mQueue.add(stringRequest);
    }

    //注册
    private void pushRegister(){
        hud.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, registerUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hud.dismiss();
                RegisterBean resultCode = new Gson().fromJson(response.toString(),RegisterBean.class);

                Log.d("TAG", response.toString());

                if(resultCode.getCode().equals("200")){
                    Toast.makeText(ActivityRegister3.this,"注册成功",Toast.LENGTH_SHORT).show();
                    setResult(ResultCode.REGISTER_SUCCESS);
                    finish();
                }else{
                    Toast.makeText(ActivityRegister3.this,"网络出错",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hud.dismiss();
                Log.e("TAG", error.getMessage(), error);

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("nickname", getIntent().getStringExtra("nickname"));
                map.put("password", Md5Util.md5(getIntent().getStringExtra("password")));
                map.put("industryTypeID", jobLx);
                map.put("p_TMP", getIntent().getStringExtra("erCode"));
                return map;
            }
        };
        mQueue.add(stringRequest);
    }
}
