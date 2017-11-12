package com.example.jamiexiong.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.bean.DevideDetailBean;
import com.example.jamiexiong.myapplication.bean.ProduceDetailBean;
import com.example.jamiexiong.myapplication.Util.UrlUtil;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mylhyl.circledialog.CircleDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;


@ContentView(R.layout.activity_detail_produce)
public class ActivityProduceDetai extends FragmentActivity{

    @ViewInject(R.id.textView2)
    TextView gdText;

    @ViewInject(R.id.textView5)
    TextView statusText;

    @ViewInject(R.id.textView17)
    TextView czyText;

    @ViewInject(R.id.textView92)
    TextView kssjText;

    @ViewInject(R.id.textView30)
    TextView jssjText;

    @ViewInject(R.id.textView31)
    TextView gxText;

    @ViewInject(R.id.textView32)
    TextView sscjText;

    @ViewInject(R.id.textView192)
    TextView sbbhText;

    @ViewInject(R.id.textView130)
    TextView cpmcText;

    @ViewInject(R.id.ggxhtext)
    TextView ggxhText;

    @ViewInject(R.id.textView312)
    TextView ycText;

    @ViewInject(R.id.textView132)
    TextView czText;

    @ViewInject(R.id.textView292)
    TextView dbText;

    @ViewInject(R.id.textView230)
    TextView tsText;

    private RequestQueue mQueue;
    private KProgressHUD hud;

    private final String detailUrl = "http://"+ UrlUtil.url1+"/request?rname=i_plc.Page.mobile.production.workorder.requestProductionInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mQueue = Volley.newRequestQueue(this);
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);
        x.view().inject(this);
        init();
    }
    @Override
    protected void onStart(){
        super.onStart();
    }


    private void init(){
        initMessage();
        initUser();
        initLayout();
        initListener();
        initData();
    }
    private void initData(){
        hud.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, detailUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hud.dismiss();
                if(response!=null&&!response.equals("")){
                    ProduceDetailBean resultCode = null;

                    try {
                        resultCode = new Gson().fromJson(response.toString(),ProduceDetailBean.class);
                    }catch(Exception e){
                        hud.dismiss();
                        Log.e("TAG", e.getMessage(), e);

                        new CircleDialog.Builder(ActivityProduceDetai.this)
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

                        new CircleDialog.Builder(ActivityProduceDetai.this)
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
                    if(!resultCode.getCode().equals("")&&resultCode.getCode()!=null&&resultCode.getCode().equals("200")){
                        ProduceDetailBean.ProduceResultBean resultBean = resultCode.getResult().get(0);
                        gdText.setText(resultBean.getPOCode());
                        statusText.setText(resultBean.getState());
                        kssjText.setText(resultBean.getDPlanSTime());
                        jssjText.setText(resultBean.getDPlanETime());
                        gxText.setText(resultBean.getCProcedureName());
                        sscjText.setText(resultBean.getZtName());
                        sbbhText.setText(resultBean.getCMac());
                        cpmcText.setText(resultBean.getCInvName());
                        ggxhText.setText(resultBean.getSpec());
                        ycText.setText(resultBean.getTooth());
                        czText.setText(resultBean.getMaterialQuality());
                        dbText.setText(resultBean.getElectroplateType());
                        tsText.setText(resultBean.getBuckets());

                    }else{
                        new CircleDialog.Builder(ActivityProduceDetai.this)
                                .setTitle("提示")
                                .setText("网络发生异常")
                                .setPositive("确定", null)
                                .show();
                    }
                }else{
                    new CircleDialog.Builder(ActivityProduceDetai.this)
                            .setTitle("提示")
                            .setText("网络发生异常")
                            .setPositive("确定", null)
                            .show();
                }


                Log.d("TAG", response.toString());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hud.dismiss();
                Log.e("TAG", error.getMessage(), error);
                new CircleDialog.Builder(ActivityProduceDetai.this)
                        .setTitle("提示")
                        .setText("网络发生异常")
                        .setPositive("确定", null)
                        .show();
            }}) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("poCode", getIntent().getStringExtra("code"));
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

        }

    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Event(value={R.id.imageView52})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.imageView52:
                finish();
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

   /* @Event(value={R.id.bt1,R.id.bt2,R.id.bt3,R.id.bt4})
    private void getEvent(View view) {
        switch (view.getId()) {

        }
    }*/

}