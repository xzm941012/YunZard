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
import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.Util.RequestCode;
import com.example.jamiexiong.myapplication.Util.ResultCode;
import com.example.jamiexiong.myapplication.Util.UrlUtil;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by jamiexiong on 2017/6/7.
 */

@ContentView(R.layout.activity_register2)
public class ActivityRegister2 extends FragmentActivity {

    @ViewInject(R.id.editText1)
    private EditText nameText;

    private RequestQueue mQueue;

    @ViewInject(R.id.editText12)
    private EditText passwordText1;

    @ViewInject(R.id.editText)
    private EditText passwordText2;

    @ViewInject(R.id.button2)
    private Button nextButton;

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

    }
    @Event(value={R.id.button2})
    private void getEvent(View view) {
        final String name = nameText.getText().toString();
        final String password1 = passwordText1.getText().toString();
        final String password2 = passwordText2.getText().toString();

        switch (view.getId()) {
            case R.id.button2:
                if(name.equals("")||password1.equals("")||password2.equals("")){
                    Toast.makeText(ActivityRegister2.this,"请填写完整资料",Toast.LENGTH_SHORT).show();
                }else if(!password1.equals(password2)){
                    Toast.makeText(ActivityRegister2.this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                }else if(password1.length()<6){
                    Toast.makeText(ActivityRegister2.this,"密码最少为6位",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(ActivityRegister2.this,ActivityRegister3.class);
                    intent.putExtra("nickname",name);
                    intent.putExtra("password",password1);
                    intent.putExtras(getIntent());
                    startActivityForResult(intent, RequestCode.REGISTERSTEP3);
                }

                break;

        }
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

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 500);
    }
}
