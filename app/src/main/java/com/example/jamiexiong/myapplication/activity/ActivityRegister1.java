package com.example.jamiexiong.myapplication.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.Util.MatcherUtil;
import com.example.jamiexiong.myapplication.Util.Md5Util;
import com.example.jamiexiong.myapplication.Util.RequestCode;
import com.example.jamiexiong.myapplication.Util.ResultCode;
import com.example.jamiexiong.myapplication.Util.UrlUtil;
import com.example.jamiexiong.myapplication.bean.DevideDetailBean;
import com.example.jamiexiong.myapplication.bean.ErCodeResult;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mylhyl.circledialog.CircleDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by jamiexiong on 2017/6/7.
 */

@ContentView(R.layout.activity_register1)
public class ActivityRegister1 extends FragmentActivity {

    @ViewInject(R.id.editText1)
    private EditText phoneText;

    private RequestQueue mQueue;

    @ViewInject(R.id.editText)
    private EditText codeText;

    @ViewInject(R.id.button2)
    private Button nextButton;

    @ViewInject(R.id.button)
    private Button codeButton;

    private KProgressHUD hud;

    private int codeThouch = 0;

    private String erCode = null;

    private String onlineCode = null;

    private final String codeUrl = "http://"+ UrlUtil.url1+"/request?rname=i_plc.Page.mobile.user.Register.AddUser1";

    /*EventHandler eh=new EventHandler(){

        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    Log.e("提交验证码成功", data.toString() );
                    hud.dismiss();
                    startActivityForResult(new Intent(ActivityRegister1.this,ActivityRegister2.class), RequestCode.REGISTERSTEP2);
                    finish();

                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //获取验证码成功
                    Log.e("获取验证码成功", data.toString() );
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表
                    Log.e("获取支持国家", data.toString() );
                }
            }else{
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    Toast.makeText(ActivityRegister1.this,"验证码错误！",Toast.LENGTH_SHORT).show();
                    hud.dismiss();
                }else{
                    Toast.makeText(ActivityRegister1.this,"网络出错！",Toast.LENGTH_SHORT).show();
                    hud.dismiss();
                }
                Log.e("event", event+"  "+ result);
                ((Throwable)data).printStackTrace();
            }
        }
    };*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        x.view().inject(this);
        init();
    }

    private void init(){
        init_smssdk();
        mQueue = Volley.newRequestQueue(this);
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);
    }

    private void init_smssdk(){

        //SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case ResultCode.REGISTER_SUCCESS:
                setResult(ResultCode.REGISTER_SUCCESS);
                finish();
                break;
        }

    }

    @Event(value={R.id.button2,R.id.button})
    private void getEvent(View view) {
        final String phone;
        final String code;
        switch (view.getId()) {
            case R.id.button2:
                phone = phoneText.getText().toString();
                code = codeText.getText().toString();
                if (!code.equals("")&&!phone.equals("") && MatcherUtil.isPhone(phone)) {
                    //SMSSDK.submitVerificationCode("86", phone, code);
                    Log.d("Md5加密后的二维码", Md5Util.md5(code));
                    if(Md5Util.md5(code).equals(erCode)){
                        Intent intent = new Intent(ActivityRegister1.this,ActivityRegister2.class);
                        intent.putExtra("tel",phone);
                        intent.putExtra("erCode",onlineCode);

                        startActivityForResult(intent, RequestCode.REGISTERSTEP2);
                    }else{
                        Toast.makeText(ActivityRegister1.this,"验证码错误",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(ActivityRegister1.this,"请完善资料",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button:
                if(codeThouch == 0) {
                    phone = phoneText.getText().toString();
                    Log.d("Activitiregister1", "phone: "+phone);
                    if (!phone.equals("") && MatcherUtil.isPhone(phone)) {
                        //SMSSDK.getVerificationCode("86", phone);
                        getCode(phone);
                        new CodeTask().execute("");
                    }else{
                        Toast.makeText(ActivityRegister1.this,"手机号格式不正确！",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ActivityRegister1.this,"请于30秒后尝试！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //根据手机号码发送二维码
    private void getCode(final String tel){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, codeUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hud.dismiss();
                ErCodeResult resultCode = null;

                try {
                    resultCode = new Gson().fromJson(response.toString(),ErCodeResult.class);
                }catch(Exception e){
                    Log.e("ActivityRegister1", e.getMessage(), e);

                    new CircleDialog.Builder(ActivityRegister1.this)
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

                    new CircleDialog.Builder(ActivityRegister1.this)
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

                Log.d("TAG", response.toString());

                if(resultCode.getCode().equals("200")){
                    if(resultCode.getResult().getId().equals("have")){
                        codeThouch = 0;
                        codeButton.setTextColor(getResources().getColor(R.color.subscribe_tip_text));
                        Toast.makeText(ActivityRegister1.this,"该账号已注册",Toast.LENGTH_SHORT).show();
                    }else{
                        erCode = resultCode.getResult().getId();
                        onlineCode = resultCode.getResult().getOnlineCode();
                    }
                }else{
                    Toast.makeText(ActivityRegister1.this,"网络出错",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hud.dismiss();
                Toast.makeText(ActivityRegister1.this,"网络出错",Toast.LENGTH_SHORT).show();
                Log.e("TAG", error.getMessage(), error);

            }}) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("mobileNum", tel);
                return map;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(8000,1,1.0f));
        mQueue.add(stringRequest);
    }

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //SMSSDK.unregisterEventHandler(eh);
    }
    class CodeTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String result){
            codeThouch = 0;
            codeButton.setTextColor(getResources().getColor(R.color.subscribe_tip_text));

        }
        @Override
        protected void onPreExecute(){
            codeThouch = 1;
            codeButton.setTextColor(getResources().getColor(R.color.subscribe_item_pressed_stroke));

        }

    }
}

