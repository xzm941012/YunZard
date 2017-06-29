package com.example.jamiexiong.myapplication.fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.PopupWindow;
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
import com.example.jamiexiong.myapplication.activity.ActivityLogin;
import com.example.jamiexiong.myapplication.activity.ActivityProduceDetai;
import com.example.jamiexiong.myapplication.adapter.ItemDeviceAdapter;
import com.example.jamiexiong.myapplication.adapter.ItemProduceAdapter;
import com.example.jamiexiong.myapplication.bean.ProduceBean;
import com.example.jamiexiong.myapplication.bean.ResultCode;
import com.example.jamiexiong.myapplication.poupwindows.SearchPoup;
import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mylhyl.circledialog.CircleDialog;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;
import com.scu.miomin.shswiperefresh.view.SHListView;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jamiexiong on 2017/6/6.
 */
@ContentView(R.layout.fragment_produce)
public class FragmentProduce extends BaseFragment {

    private List<String> mDatas;

    @ViewInject(R.id.swipeRefreshLayout)
    private SHSwipeRefreshLayout swipeRefreshLayout;
    @ViewInject(R.id.shLv)
    private SHListView shLv;
    private View view;
    @ViewInject(R.id.nicespinner)
    MaterialSpinner spinner;

    @ViewInject(R.id.imageView53)
    private View serchImage;

    @ViewInject(R.layout.poup_search)
    private View searchView;

    private List<ProduceBean.ResultBean> resultBeanList;

    private KProgressHUD hud;

    private SearchPoup searchPoup;

    private RequestQueue mQueue;

    private String type = "0";

    private final String[] itemsArray = {"系统所有工单", "已下达工单", "已报工工单", "已完成工单"};
    private final Map<String,String> itemText = new HashMap<>();

    private final String url = "http://"+ UrlUtil.url1+"/request?rname=i_plc.Page.mobile.production.workorder.requestProductionInfoField";

    private final String searchUrl = "http://"+UrlUtil.url1+"/request?rname=i_plc.Page.mobile.production.workorder.fuzzyProductionInfo";
    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        initLayout();
        initListenner();
        initData();
        initLv();
        initSwipeRefreshLayout();
        initItems();
    }

    private void initListenner(){
        shLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProduceBean.ResultBean resultBean = resultBeanList.get(position);
                Intent intent = new Intent(getContext(), ActivityProduceDetai.class);
                intent.putExtra("code",resultBean.getPOCode());
                startActivity(intent);
            }
        });
    }
    //加载listview的数据
    private void initItems(){
        hud.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hud.dismiss();
                ProduceBean resultCode = new Gson().fromJson(response.toString(),ProduceBean.class);
                resultBeanList = resultCode.getResult();

                Log.d("生产列表", response.toString());

                if(resultCode.getCode().equals("200")){
                    shLv.setAdapter(new ItemProduceAdapter(getContext(),resultBeanList));
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
                map.put("state", type);
                return map;
            }
        };
        mQueue.add(stringRequest);
    }

    //加载listview的数据
    private void initSearchItems(final String str){
        hud.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, searchUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hud.dismiss();
                ProduceBean resultCode = new Gson().fromJson(response.toString(),ProduceBean.class);
                resultBeanList = resultCode.getResult();

                Log.d("生产列表", resultCode.getResult().toString());

                if(resultCode.getCode().equals("200")){
                    Log.d("刷新列表", "resultCode");
                    shLv.setAdapter(new ItemProduceAdapter(getContext(),resultBeanList));
                    searchPoup.dismiss();
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
                map.put("field", str);
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
        spinner.setItems(itemsArray);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                type = itemText.get(item);
                initItems();
            }
        });
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
    @Event(value={R.id.imageView53})
    private void getEvent(View view) {
        switch (view.getId()){
            case R.id.imageView53:
                String shareData = "";
                List<String> skills = Arrays.asList(shareData.split(","));

                String shareHotData ="已下单,已报工,已完成";

                List<String> skillHots = Arrays.asList(shareHotData.split(","));
                searchPoup = new SearchPoup(getActivity(), null, skillHots, skills, new Callback.Callable<String>() {
                    @Override
                    public void call(String result) {
                        initSearchItems(result);
                    }
                });
                searchPoup.setBackgroundDrawable(null);
                //显示窗口
                searchPoup.showAtLocation(getActivity().findViewById(R.id.homeview), Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                //当弹出Popupwindow时，背景变半透明
                params.alpha=0.5f;
                getActivity().getWindow().setAttributes(params);
                searchPoup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams params1 = getActivity().getWindow().getAttributes();
                        params1.alpha=1f;
                        getActivity().getWindow().setAttributes(params1);
                    }
                });
                break;
        }
    }
    protected void initData() {
        itemText.put("系统所有工单","0");
        itemText.put("已下达工单","已下达");
        itemText.put("已报工工单","已报工");
        itemText.put("已完成工单","已完成");
        mDatas = new ArrayList();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initLv() {


    }
}
