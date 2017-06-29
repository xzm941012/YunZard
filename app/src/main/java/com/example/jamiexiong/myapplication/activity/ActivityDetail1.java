package com.example.jamiexiong.myapplication.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
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
import com.example.jamiexiong.myapplication.adapter.MyFragmentPagerAdapter;
import com.example.jamiexiong.myapplication.bean.DevideDetailBean;
import com.example.jamiexiong.myapplication.fragment.FragmentDetail;
import com.example.jamiexiong.myapplication.fragment.FragmentDetailLineChart;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mylhyl.circledialog.CircleDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jamiexiong on 2017/6/7.
 */

@ContentView(R.layout.activity_detail1)
public class ActivityDetail1 extends FragmentActivity {

    @ViewInject(R.id.textView2)
    TextView nameText;

    @ViewInject(R.id.tab)
    NavigationTabStrip navigationTabStrip;

    @ViewInject(R.id.textView5)
    TextView xhText;

    @ViewInject(R.id.viewpager)
    private ViewPager viewPager1;

    private RequestQueue mQueue;

    private KProgressHUD hud;

    private List<Fragment> listfragment ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mQueue = Volley.newRequestQueue(this);
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);
        x.view().inject(this);
        initDate();

    }

    private void initDate(){
        initTab();
        initPageDate();
    }

    private void initTab(){
        navigationTabStrip.setTitles("详细信息", "图表查看");
        navigationTabStrip.setTabIndex(0, true);
        navigationTabStrip.setTitleSize(45);
        navigationTabStrip.setStripColor(Color.RED);
        navigationTabStrip.setStripWeight(6);
        navigationTabStrip.setStripFactor(2);
        navigationTabStrip.setStripType(NavigationTabStrip.StripType.LINE);
        navigationTabStrip.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
        navigationTabStrip.setTypeface("fonts/typeface.ttf");
        navigationTabStrip.setCornersRadius(3);
        navigationTabStrip.setAnimationDuration(300);
        navigationTabStrip.setInactiveColor(Color.LTGRAY);
        navigationTabStrip.setActiveColor(Color.WHITE);
        navigationTabStrip.setStripColor(Color.WHITE);
        navigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                Log.d("onStartTabSelected", index+"");
                viewPager1.setCurrentItem(index);
            }

            @Override
            public void onEndTabSelected(String title, int index) {
                Log.d("onEndTabSelected", index+"");
            }
        });
    }
    private void initPageDate(){

        nameText.setText(getIntent().getStringExtra("name")+"：");
        xhText.setText(getIntent().getStringExtra("code"));
        listfragment=new ArrayList<Fragment>(); //new一个List<Fragment>
        Fragment f1 = new FragmentDetail();
        Fragment f2 = new FragmentDetailLineChart();
        listfragment.add(f1);
        listfragment.add(f2);

        FragmentManager fm=getSupportFragmentManager();
        MyFragmentPagerAdapter mfpa =new MyFragmentPagerAdapter(fm, listfragment); //new myFragmentPagerAdater记得带上两个参数

        viewPager1.setAdapter(mfpa);
        viewPager1.setCurrentItem(0); //设置当前页是第一页
    }
    @Event(value={R.id.imageView52})
    private void getEvent(View view) {
        Log.d("点中", view.getId() + "");
        switch (view.getId()) {
            case R.id.imageView52:
                finish();
                break;
        }
    }

}
