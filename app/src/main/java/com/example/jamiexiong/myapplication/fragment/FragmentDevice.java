package com.example.jamiexiong.myapplication.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.Util.UrlUtil;
import com.example.jamiexiong.myapplication.bean.DeviceItemBean;
import com.example.jamiexiong.myapplication.activity.ActivityDetail;
import com.example.jamiexiong.myapplication.adapter.ItemDeviceAdapter;
import com.example.jamiexiong.myapplication.bean.DeviceTypeBean;
import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mylhyl.circledialog.CircleDialog;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;
import com.scu.miomin.shswiperefresh.view.SHListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jamiexiong on 2017/6/6.
 */

@ContentView(R.layout.fragment_device)
public class FragmentDevice extends BaseFragment {
    private List<String> mDatas;

    @ViewInject(R.id.swipeRefreshLayout)
    private SHSwipeRefreshLayout swipeRefreshLayout;


    @ViewInject(R.id.shLv)
    private SHListView shLv;



    private View view;
    @ViewInject(R.id.nicespinner)
    MaterialSpinner spinner;
    private KProgressHUD hud;

    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private RequestQueue mQueue;

    private String deviceType = "";

    private List<DeviceItemBean.DeviceResultBean> deviceBeanList;

    private List<DeviceTypeBean.ResultBean> resultBeanList;

    private final String typeUrl = "http://"+ UrlUtil.url1+"/request?rname=i_plc.Page.mobile.machq.ztName.ztNameList";

    private final String typeItemsUrl = "http://"+UrlUtil.url1+"/request?rname=i_plc.Page.mobile.machq.ztName.ztNameMachList";


    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        initLayout();
        initData();
        initLv();
        initSwipeRefreshLayout();
        initType();
    }

    private void initTypeDatas(){
        hud.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, typeItemsUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hud.dismiss();
                DeviceItemBean resultCode = new Gson().fromJson(response.toString(),DeviceItemBean.class);

                Log.d("设备列表：", response.toString());

                if(resultCode.getCode().equals("200")){

                    deviceBeanList = resultCode.getResult();

                    shLv.setAdapter(new ItemDeviceAdapter(getContext(),deviceBeanList));
                    shLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String code = deviceBeanList.get(position).getCode();
                            String name = deviceBeanList.get(position).getMachName();
                            String status = deviceBeanList.get(position).getStatus();
                            Intent intent = new Intent(getContext(), ActivityDetail.class);
                            intent.putExtra("code",code);
                            intent.putExtra("name",name);
                            intent.putExtra("status",status);
                            startActivity(intent);
                        }
                    });

                }else{
                    new CircleDialog.Builder(getActivity())
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

                new CircleDialog.Builder(getActivity())
                        .setTitle("提示")
                        .setText("网络发生异常")
                        .setPositive("确定", null)
                        .show();
            }}) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("ztID", deviceType);
                map.put("date", df.format(new Date()));
                return map;
            }
        };
        mQueue.add(stringRequest);
    }

    private void initType(){
        hud.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, typeUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hud.dismiss();
                DeviceTypeBean resultCode = new Gson().fromJson(response.toString(),DeviceTypeBean.class);

                Log.d("设备分类：", response.toString());

                if(resultCode.getCode().equals("200")){

                    resultBeanList = resultCode.getResult();
                    List<String> types = new ArrayList<>();
                    for(DeviceTypeBean.ResultBean resultBean : resultBeanList){
                        types.add(resultBean.getZtName());
                    }


                    spinner.setItems(types);
                    spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                        @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                            deviceType = resultBeanList.get(position).getId();
                            initTypeDatas();
                            //Toast.makeText(getContext(),"Clicked " + position+"", Toast.LENGTH_LONG).show();
                        }
                    });
                    deviceType = resultBeanList.get(0).getId();
                    initTypeDatas();

                }else{
                    new CircleDialog.Builder(getActivity())
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

                new CircleDialog.Builder(getActivity())
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

    private void initLayout(){

        mQueue = Volley.newRequestQueue(getContext());
        hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);


    }
    private void initSwipeRefreshLayout() {

        /*final TextView textView = (TextView) view.findViewById(R.id.title);
        swipeRefreshLayout.setFooterView(view);*/
        swipeRefreshLayout.setOnRefreshListener(new SHSwipeRefreshLayout.SHSOnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.finishRefresh();
                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 1600);
            }

            @Override
            public void onLoading() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.finishLoadmore();
                        Toast.makeText(getActivity(), "加载完成", Toast.LENGTH_SHORT).show();
                    }
                }, 1600);
            }

            /**
             * 监听下拉刷新过程中的状态改变
             * @param percent 当前下拉距离的百分比（0-1）
             * @param state 分三种状态{NOT_OVER_TRIGGER_POINT：还未到触发下拉刷新的距离；OVER_TRIGGER_POINT：已经到触发下拉刷新的距离；START：正在下拉刷新}
             */
            @Override
            public void onRefreshPulStateChange(float percent, int state) {
                switch (state) {
                    case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                        swipeRefreshLayout.setRefreshViewText("下拉刷新");
                        break;
                    case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                        swipeRefreshLayout.setRefreshViewText("松开刷新");
                        break;
                    case SHSwipeRefreshLayout.START:
                        swipeRefreshLayout.setRefreshViewText("正在刷新");
                        break;
                }
            }

            @Override
            public void onLoadmorePullStateChange(float percent, int state) {
                switch (state) {
                    case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                        swipeRefreshLayout.setLoaderViewText("上拉加载");
                        break;
                    case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                        swipeRefreshLayout.setLoaderViewText("松开加载");
                        break;
                    case SHSwipeRefreshLayout.START:
                        swipeRefreshLayout.setLoaderViewText("正在加载...");
                        break;
                }
            }
        });
    }

    protected void initData() {
        mDatas = new ArrayList();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initLv() {


    }

}
