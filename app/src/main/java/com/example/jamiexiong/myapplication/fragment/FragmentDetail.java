package com.example.jamiexiong.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.Util.UrlUtil;
import com.example.jamiexiong.myapplication.activity.ActivityDetailLineChart;
import com.example.jamiexiong.myapplication.bean.DevideDetailBean;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mylhyl.circledialog.CircleDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jamiexiong on 2017/6/7.
 */

@ContentView(R.layout.fragment_detail)
public class FragmentDetail extends BaseFragment {

    @ViewInject(R.id.textView322)
    TextView zqText;

    @ViewInject(R.id.footview)
    View footView;

    @ViewInject(R.id.imageView52)
    View backButton;

    @ViewInject(R.id.textView323)
    TextView rqText;

    @ViewInject(R.id.textView332)
    TextView jbText;

    @ViewInject(R.id.textView392)
    TextView wxRqText;

    @ViewInject(R.id.textView330)
    TextView wxRyText;

    @ViewInject(R.id.textView331)
    TextView wxYyText;

    @ViewInject(R.id.textView2)
    TextView nameText;

    @ViewInject(R.id.textView92)
    TextView nameText1;

    @ViewInject(R.id.textView5)
    TextView xhText;

    @ViewInject(R.id.textView30)
    TextView xhText1;

    @ViewInject(R.id.textView31)
    TextView statusText;

    @ViewInject(R.id.circleImageView)
    CircleImageView statusImage;

    @ViewInject(R.id.imageView47)
    ImageView rigth1;

    @ViewInject(R.id.imageView44)
    ImageView down1;

    @ViewInject(R.id.jibenview)
    View jibenview;

    @ViewInject(R.id.imageView48)
    ImageView rigth2;

    @ViewInject(R.id.imageView144)
    ImageView down2;

    @ViewInject(R.id.ssview)
    View ssview;

    @ViewInject(R.id.imageView244)
    ImageView rigth3;

    @ViewInject(R.id.imageView49)
    ImageView down3;

    @ViewInject(R.id.lsview)
    View lsview;

    @ViewInject(R.id.imageView344)
    ImageView rigth4;

    @ViewInject(R.id.imageView50)
    ImageView down4;

    @ViewInject(R.id.whview)
    View whview;

    private RequestQueue mQueue;

    private KProgressHUD hud;

    private int press1,press2,press3,press4 =0;
    private final String detailUrl = "http://"+UrlUtil.url1+"/request?rname=i_plc.Page.mobile.machq.machInfo.machMaintenace";

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        mQueue = Volley.newRequestQueue(getContext());
        hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);
        initDate();

    }

    private void initDate(){
        initPageDate();
    }

    private void initPageDate(){
        hud.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, detailUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hud.dismiss();
                DevideDetailBean result = new Gson().fromJson(response.toString(),DevideDetailBean.class);
                DevideDetailBean.DetailResultBean resultBean = result.getResult();

                zqText.setText(resultBean.getMaintenanceperiod());
                rqText.setText(resultBean.getMaintenanceday());
                jbText.setText(resultBean.getMaintenancetype());
                wxRqText.setText(resultBean.getRepairday());
                wxRyText.setText(resultBean.getPerson());
                wxYyText.setText(resultBean.getRepairreason());
                nameText.setText(getActivity().getIntent().getStringExtra("name")+"：");
                nameText1.setText(getActivity().getIntent().getStringExtra("name"));
                xhText.setText(getActivity().getIntent().getStringExtra("code"));
                xhText1.setText(getActivity().getIntent().getStringExtra("code"));
                
                String status = getActivity().getIntent().getStringExtra("status");
                if (status.equals("online")) {
                    statusText.setText("正常运行");
                    //statusImage.setImageResource(Color.GREEN);
                } else if (status.equals("offline")) {
                    statusText.setText("正常停机");
                    //statusImage.setImageResource(R.color.BLACK);
                } else if (status.equals("badoff")) {
                    statusText.setText("故障停机");
                    //statusImage.setImageResource(R.color.RED);
                } else if (status.equals("badch")) {
                    statusText.setText("试制故障运行");
                    //statusImage.setImageResource(R.color.YELLOW);
                } else if (status.equals("badon")) {
                    statusText.setText("持续故障运行");
                    //statusImage.setImageResource(R.color.YELLOW);
                } else if (status.equals("chmod")) {
                    statusText.setText("更换模具");
                    //statusImage.setImageResource(R.color.BLUE);
                } else if (status.equals("repair")) {
                    statusText.setText("设备维修");
                    //statusImage.setImageResource(R.color.DKGRAY);
                }

                Log.d("详情页面：", response.toString());

                if(result.getCode() == 200){

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
                map.put("machCode", getActivity().getIntent().getStringExtra("code"));
                return map;
            }
        };
        mQueue.add(stringRequest);
    }

    @Event(value={R.id.jb1,R.id.ss1,R.id.ls1,R.id.wh1,R.id.imageView52,R.id.footview})
    private void getEvent(View view) {
        Log.d("点中", view.getId()+"");
        switch (view.getId()) {
            case R.id.imageView52:
                getActivity().finish();
                break;
            case R.id.jb1:
                if(press1 == 0){
                    press1 = 1;
                    jibenview.setVisibility(View.VISIBLE);
                    rigth1.setVisibility(View.INVISIBLE);
                    down1.setVisibility(View.VISIBLE);
                }else{
                    press1 = 0;
                    jibenview.setVisibility(View.GONE);
                    rigth1.setVisibility(View.VISIBLE);
                    down1.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.ss1:
                if(press2 == 0){
                    press2 = 1;
                    ssview.setVisibility(View.VISIBLE);
                    rigth2.setVisibility(View.INVISIBLE);
                    down2.setVisibility(View.VISIBLE);
                }else{
                    press2 = 0;
                    ssview.setVisibility(View.GONE);
                    rigth2.setVisibility(View.VISIBLE);
                    down2.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.ls1:
                if(press3 == 0){
                    press3 = 1;
                    lsview.setVisibility(View.VISIBLE);
                    rigth3.setVisibility(View.INVISIBLE);
                    down3.setVisibility(View.VISIBLE);
                }else{
                    press3 = 0;
                    lsview.setVisibility(View.GONE);
                    rigth3.setVisibility(View.VISIBLE);
                    down3.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.wh1:
                if(press4 == 0){
                    press4 = 1;
                    whview.setVisibility(View.VISIBLE);
                    rigth4.setVisibility(View.INVISIBLE);
                    down4.setVisibility(View.VISIBLE);
                }else{
                    press4 = 0;
                    whview.setVisibility(View.GONE);
                    rigth4.setVisibility(View.VISIBLE);
                    down4.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.footview:
                startActivity(new Intent(getContext(),ActivityDetailLineChart.class));
                break;
        }
    }

}
