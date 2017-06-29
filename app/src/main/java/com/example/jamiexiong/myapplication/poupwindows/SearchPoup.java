package com.example.jamiexiong.myapplication.poupwindows;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.czp.searchmlist.mSearchLayout;
import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.activity.ActivitySearch;
import com.example.jamiexiong.myapplication.application.MApplication;
import com.google.gson.Gson;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by jamiexiong on 2017/6/14.
 */

public class SearchPoup extends PopupWindow {


    private View mMenuView;

    private Gson gson = new Gson();

    public SearchPoup(final FragmentActivity context, View.OnClickListener itemsOnClick, List<String> hots, List<String> history,final Callback.Callable<String> callback) {
        super(context);

        mMenuView = LayoutInflater.from(context).inflate(R.layout.poup_search, null);
        mSearchLayout msearchLy = (mSearchLayout)mMenuView.findViewById(R.id.msearchlayout);
        msearchLy.initData(history, hots, new mSearchLayout.setSearchCallBackListener() {
            @Override
            public void Search(String str) {
                //进行或联网搜索
                callback.call(str);
            }
            @Override
            public void Back() {
                dismiss();
            }

            @Override
            public void ClearOldData() {
                //清除历史搜索记录  更新记录原始数据
                SharedPreferences sp = context.getSharedPreferences("produce",MODE_PRIVATE);
                //获取编辑对象
                SharedPreferences.Editor edit = sp.edit();
                //添加值
                edit.putString("historymenu","");
                //提交
                edit.commit();
            }
            @Override
            public void SaveOldData(ArrayList<String> AlloldDataList) {

                Log.d("保存的数据", gson.toJson(AlloldDataList));
                //保存所有的搜索记录
                SharedPreferences sp = context.getSharedPreferences("produce",MODE_PRIVATE);
                //获取编辑对象
                SharedPreferences.Editor edit = sp.edit();
                //添加值
                edit.putString("historymenu",gson.toJson(AlloldDataList));
                //提交
                edit.commit();
            }
        });

        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.take_photo_anim1);
        //实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        //this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.poupview1).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });

    }

}
