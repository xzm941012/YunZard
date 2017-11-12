package com.example.jamiexiong.myapplication.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.jamiexiong.myapplication.activity.ActivityHome;
import com.example.jamiexiong.myapplication.adapter.ItemHomeAdapter1;
import com.example.jamiexiong.myapplication.bean.DeviceTypeBean;
import com.example.jamiexiong.myapplication.bean.DevideDetailBean;
import com.example.jamiexiong.myapplication.bean.HomeBean;
import com.example.jamiexiong.myapplication.bean.HomeBean1;
import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mylhyl.circledialog.CircleDialog;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;
import com.scu.miomin.shswiperefresh.view.SHListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jamiexiong on 2017/6/6.
 */

@ContentView(R.layout.fragment_home1)
public class FragmentHome1 extends BaseFragment {

    private List<String> mDatas;

    @ViewInject(R.id.swipeRefreshLayout)
    private SHSwipeRefreshLayout swipeRefreshLayout;

    @ViewInject(R.id.shLv)
    private SHListView shLv;
    private KProgressHUD hud;
    private RequestQueue mQueue;
    //private final String url = "http://"+ UrlUtil.url1+"/request?rname=i_plc.Page.mobile.production.workorder.indexAlertInfo";
    private final String url = "http://"+ UrlUtil.url1+"/request?rname=i_plc.Page.mobile.routime.routimelist.routimeListInfo";

    private View view;

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        mQueue = Volley.newRequestQueue(getContext());
        hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);

        initLayout();
        initData();

        initSwipeRefreshLayout();
        initLv();

    }



    private void initLayout(){

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
        hud.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hud.dismiss();
                HomeBean1 resultBean = null;

                try {
                    resultBean = new Gson().fromJson(response.toString(),HomeBean1.class);
                }catch(Exception e){
                    Log.e("TAG", e.getMessage(), e);

                    new CircleDialog.Builder(getActivity())
                            .setTitle("提示")
                            .setText("网络发生异常")
                            .setPositive("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    getActivity().finish();
                                }
                            })
                            .show();
                }
                if(resultBean ==null){

                    new CircleDialog.Builder(getActivity())
                            .setTitle("提示")
                            .setText("网络发生异常")
                            .setPositive("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    getActivity().finish();
                                }
                            })
                            .show();

                    return;
                }

                Log.d("设备分类：", response.toString());

                if(resultBean.getCode()==200){

                    List<HomeBean1.HomeListBean> homeResults = resultBean.getResult();
                    shLv.setAdapter(new ItemHomeAdapter1(getContext(),homeResults));

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
    @Event(value={R.id.imageView67})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.imageView67:
                //Toast.makeText(getContext(),"1243",Toast.LENGTH_SHORT).show();
                ((ActivityHome)getActivity()).openDrawer();
                break;
        }
    }

}
