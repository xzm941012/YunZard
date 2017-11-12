package com.example.jamiexiong.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appeaser.sublimenavigationviewlibrary.SublimeNavigationView;
import com.bumptech.glide.Glide;
import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.Util.RequestCode;
import com.example.jamiexiong.myapplication.Util.ResultCode;
import com.example.jamiexiong.myapplication.application.MApplication;
import com.example.jamiexiong.myapplication.fragment.FragmentDevice;
import com.example.jamiexiong.myapplication.fragment.FragmentDevice1;
import com.example.jamiexiong.myapplication.fragment.FragmentHome;
import com.example.jamiexiong.myapplication.fragment.FragmentHome1;
import com.example.jamiexiong.myapplication.fragment.FragmentMy;
import com.example.jamiexiong.myapplication.fragment.FragmentMy1;
import com.example.jamiexiong.myapplication.fragment.FragmentProduce;
import com.example.jamiexiong.myapplication.fragment.FragmentProduce1;
import com.yuyh.library.imgsel.ImgSelActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.List;


@ContentView(R.layout.activity_home2)
public class ActivityHome extends FragmentActivity{
//晚上资料只是确认自己的标签，更具标签选择活动标签

    Fragment fm1=null;
    Fragment fm2=new FragmentDevice1();
    Fragment fm3=null;
    FragmentMy1 fm4=new FragmentMy1();

    @ViewInject(R.id.navigation_view)
    SublimeNavigationView sublimeNavigationView;

    @ViewInject(R.id.navigation_view)
    View leftMenu;

    @ViewInject(R.id.homeview)
    DrawerLayout layout;

    @ViewInject(R.id.bt1)
    View bt1;

    @ViewInject(R.id.bt2)
    View bt2;

    @ViewInject(R.id.bt3)
    View bt3;

    @ViewInject(R.id.bt4)
    View bt4;

    @ViewInject(R.id.imageView126)
    ImageView bt1Image;

    @ViewInject(R.id.imageView128)
    ImageView bt2Image;

    @ViewInject(R.id.imageView127)
    ImageView bt3Image;

    @ViewInject(R.id.imageView130)
    ImageView bt4Image;

    @ViewInject(R.id.textView139)
    TextView bt1Text;

    @ViewInject(R.id.textView140)
    TextView bt2Text;

    @ViewInject(R.id.textView137)
    TextView bt3Text;

    @ViewInject(R.id.textView138)
    TextView bt4Text;



    int twoThouch=0;
    int i=0;

    public void openDrawer(){
        //Toast.makeText(this,"123",Toast.LENGTH_SHORT).show();
        layout.openDrawer(leftMenu);
        ((TextView)leftMenu.findViewById(R.id.tvNamePlate)).setText(MApplication.getUser().getResult().getNickname());

    }

    public void closeDrawer(){
        layout.closeDrawer(leftMenu);
    }

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
        initMessage();
        initUser();
        initLayout();
        initListener();
        /*
        FragmentTransaction ts=getSupportFragmentManager().beginTransaction();
        ts.replace(R.id.frameLayout, fm3) .commit();
        */
        bt2.performClick();
    }
    private void initUser(){

    }
    private void initLayout(){

    }
    private void initMessage(){

    }
    private void initListener(){

        /*
        findViewById(R.id.fabuhuodong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null){
                    startActivity(new Intent(MainActivity.this, com.example.topnewgrid.choosephotos.choosephotos.MainActivity.class));
                }else{
                    startActivityForResult(new Intent(MainActivity.this,Activity_login.class),1);
                }
            }
        });
        findViewById(R.id.textView88).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null){
                    startActivityForResult(new Intent(MainActivity.this, Activity_fabu_xuqiu.class),1);
                }else{
                    startActivityForResult(new Intent(MainActivity.this,Activity_login.class),1);
                }
            }
        });
        */
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("选择图片回调",requestCode+"");
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case RequestCode.REGISTER:
                Log.d("注册结果回调:", "成功");
                break;

            case RequestCode.SELECT_IMAGE:
                if(resultCode == RESULT_OK&&data != null){
                    List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
                    Log.d("选择的图片位置", pathList.toString());

                    if(pathList.size() != 0){
                       fm4.setTouxiangUrl(pathList.get(0));
                    }

                }
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




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Event(value={R.id.bt1,R.id.bt2,R.id.bt3,R.id.bt4})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                if(i!=1) {
                    i=1;
                    bt1Image.setImageDrawable(getResources().getDrawable(R.drawable.home1_dark));
                    bt1Text.setTextColor(getResources().getColor(R.color.bt));
                    bt2Image.setImageDrawable(getResources().getDrawable(R.drawable.dns1));
                    bt2Text.setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    bt3Image.setImageDrawable(getResources().getDrawable(R.drawable.tune1));
                    bt3Text.setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    bt4Image.setImageDrawable(getResources().getDrawable(R.drawable.accound1));
                    bt4Text.setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    FragmentTransaction ts = getSupportFragmentManager().beginTransaction();
                    ts.replace(R.id.frameLayout, fm1).commit();
                }
                break;
            case R.id.bt2:
                if(i!=2) {
                    i = 2;
                    bt1Image.setImageDrawable(getResources().getDrawable(R.drawable.home1));
                    bt1Text.setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    bt2Image.setImageDrawable(getResources().getDrawable(R.drawable.dns1_dark));
                    bt2Text.setTextColor(getResources().getColor(R.color.bt));
                    bt3Image.setImageDrawable(getResources().getDrawable(R.drawable.tune1));
                    bt3Text.setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    bt4Image.setImageDrawable(getResources().getDrawable(R.drawable.accound1));
                    bt4Text.setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    FragmentTransaction ts2 = getSupportFragmentManager().beginTransaction();
                    ts2.replace(R.id.frameLayout, fm2).commit();
                }
                break;
            case R.id.bt3:
                if(i!=3) {
                    i = 3;
                    bt1Image.setImageDrawable(getResources().getDrawable(R.drawable.home1));
                    bt1Text.setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    bt2Image.setImageDrawable(getResources().getDrawable(R.drawable.dns1));
                    bt2Text.setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    bt3Image.setImageDrawable(getResources().getDrawable(R.drawable.tune1_dark));
                    bt3Text.setTextColor(getResources().getColor(R.color.bt));
                    bt4Image.setImageDrawable(getResources().getDrawable(R.drawable.accound1));
                    bt4Text.setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    FragmentTransaction ts3 = getSupportFragmentManager().beginTransaction();
                    ts3.replace(R.id.frameLayout, fm3).commit();
                }
                break;
            case R.id.bt4:
                if(i!=4) {
                    i = 4;
                    bt1Image.setImageDrawable(getResources().getDrawable(R.drawable.home1));
                    bt1Text.setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    bt2Image.setImageDrawable(getResources().getDrawable(R.drawable.dns1));
                    bt2Text.setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    bt3Image.setImageDrawable(getResources().getDrawable(R.drawable.tune1));
                    bt3Text.setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    bt4Image.setImageDrawable(getResources().getDrawable(R.drawable.accound1_dark));
                    bt4Text.setTextColor(getResources().getColor(R.color.bt));
                    FragmentTransaction ts4 = getSupportFragmentManager().beginTransaction();
                    ts4.replace(R.id.frameLayout, fm4).commit();
                }
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            //do something...
            if(twoThouch==1){
                finish();
            }else{
                Toast.makeText(ActivityHome.this,"再次点击返回键退出",Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        twoThouch=1;
                        try {
                            Thread.sleep(3000);
                            twoThouch=0;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}