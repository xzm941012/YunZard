package com.example.jamiexiong.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.czp.searchmlist.mSearchLayout;
import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.Util.RequestCode;
import com.example.jamiexiong.myapplication.Util.ResultCode;
import com.example.jamiexiong.myapplication.Util.UrlUtil;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jamiexiong on 2017/6/7.
 */

@ContentView(R.layout.activity_search)
public class ActivitySearch extends FragmentActivity {

    private RequestQueue mQueue;

    @ViewInject(R.id.msearchlayout)
    private mSearchLayout msearchLy;


    private KProgressHUD hud;

    private final String url = "http://"+ UrlUtil.url1+"/mobile/getinfo/loginf/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        x.view().inject(this);
        init();
    }

    private void init(){
        mQueue = Volley.newRequestQueue(this);
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);
        String shareData = "澳洲美食,长沙美食,韩国料理,日本料理,舌尖上的中国,意大利餐,山西菜";
        List<String> skills = Arrays.asList(shareData.split(","));

        String shareHotData ="粤菜,浙菜,苏菜";
        List<String> skillHots = Arrays.asList(shareHotData.split(","));

        msearchLy.initData(skills, skillHots, new mSearchLayout.setSearchCallBackListener() {
            @Override
            public void Search(String str) {
                //进行或联网搜索
                Toast.makeText(ActivitySearch.this,str,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void Back() {
                finish();
            }

            @Override
            public void ClearOldData() {
                //清除历史搜索记录  更新记录原始数据
            }
            @Override
            public void SaveOldData(ArrayList<String> AlloldDataList) {
                //保存所有的搜索记录
            }
        });
    }
    /*@Event(value={R.id.button2})
    private void getEvent(View view) {

    }*/

  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case ResultCode.REGISTER_SUCCESS:
                setResult(ResultCode.REGISTER_SUCCESS);
                finish();
                break;
        }

    }*/

}
