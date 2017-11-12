package com.example.jamiexiong.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.Util.RequestCode;
import com.example.jamiexiong.myapplication.Util.UrlUtil;
import com.example.jamiexiong.myapplication.adapter.FocusAdapter;
import com.example.jamiexiong.myapplication.adapter.ItemDeviceAdapter1;
import com.example.jamiexiong.myapplication.bean.DeviceItemBean;
import com.example.jamiexiong.myapplication.bean.DevideDetailBean;
import com.example.jamiexiong.myapplication.bean.FocusListBean;
import com.example.jamiexiong.myapplication.fragment.FragmentDevice1;
import com.example.jamiexiong.myapplication.fragment.FragmentHome1;
import com.example.jamiexiong.myapplication.fragment.FragmentMy1;
import com.example.jamiexiong.myapplication.fragment.FragmentProduce1;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mylhyl.circledialog.CircleDialog;
import com.scu.miomin.shswiperefresh.view.SHListView;
import com.yuyh.library.imgsel.ImgSelActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ContentView(R.layout.my_focus)
public class ActivityFocus extends FragmentActivity{
//晚上资料只是确认自己的标签，更具标签选择活动标签

    @ViewInject(R.id.shLv)
    private SHListView shLv;

    @ViewInject(R.id.imageView58)
    ImageView backImage;

    private RequestQueue mQueue;

    private KProgressHUD hud;

    private List<FocusListBean.FoocusListItems> focusListItems;

    private final String url = "http://"+ UrlUtil.url1 +"/request?rname=i_plc.Page.mobile.mine.mineInfo.mineAttentionAllList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        x.view().inject(this);
        init();
    }
    @Override
    protected void onStart(){
        super.onStart();
    }


    private void init(){
        mQueue = Volley.newRequestQueue(this);
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);
        initMessage();
        initUser();
        initLayout();
        initListener();
        initData();
        /*
        FragmentTransaction ts=getSupportFragmentManager().beginTransaction();
        ts.replace(R.id.frameLayout, fm3) .commit();
        */
    }
    private void initData(){
        hud.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hud.dismiss();
                FocusListBean resultCode = null;

                try {
                    resultCode = new Gson().fromJson(response.toString(),FocusListBean.class);
                }catch(JsonSyntaxException e){
                    hud.dismiss();
                    Log.e("TAG", e.getMessage(), e);

                    new CircleDialog.Builder(ActivityFocus.this)
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
                    hud.dismiss();

                    new CircleDialog.Builder(ActivityFocus.this)
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

                Log.d("设备列表：", response.toString());

                if(resultCode.getCode().equals("200")){

                    focusListItems = resultCode.getResult();

                    shLv.setAdapter(new FocusAdapter(ActivityFocus.this,focusListItems));
                    shLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }
                    });

                }else{
                    new CircleDialog.Builder(ActivityFocus.this)
                            .setTitle("提示")
                            .setText("数据异常")
                            .setPositive("确定", null)
                            .show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hud.dismiss();
                Log.e("TAG", error.getMessage(), error);

                new CircleDialog.Builder(ActivityFocus.this)
                        .setTitle("提示")
                        .setText("网络发生异常")
                        .setPositive("确定", null)
                        .show();
            }}) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();

                return map;
            }
        };
        mQueue.add(stringRequest);
    }
    private void initUser(){

    }
    private void initLayout(){

    }
    private void initMessage(){

    }
    private void initListener(){


    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Event(value={R.id.imageView58})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.imageView58:
                finish();
                break;
        }
    }






}