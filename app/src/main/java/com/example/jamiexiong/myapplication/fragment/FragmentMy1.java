package com.example.jamiexiong.myapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.Util.RequestCode;
import com.example.jamiexiong.myapplication.Util.ResultCode;
import com.example.jamiexiong.myapplication.activity.ActivityFocus;
import com.example.jamiexiong.myapplication.activity.ActivityLogin;
import com.example.jamiexiong.myapplication.application.MApplication;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigButton;
import com.mylhyl.circledialog.callback.ConfigDialog;
import com.mylhyl.circledialog.params.ButtonParams;
import com.mylhyl.circledialog.params.DialogParams;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jamiexiong on 2017/6/6.
 */

@ContentView(R.layout.fragment_my2)
public class FragmentMy1 extends Fragment {

    @ViewInject(R.id.textView16)
    private TextView logon;

    @ViewInject(R.id.circleImageView2)
    private CircleImageView touxiangView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //x.view().inject(getActivity());
    }

    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            // TODO 在这边可以自定义图片加载库来加载ImageView，例如Glide、Picasso、ImageLoader等
            Glide.with(context).load(path).into(imageView);
        }
    };

    final String[] items = {"从相册选择"};

    CircleDialog circleDialog;

    // 自由配置选项
    ImgSelConfig config;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return x.view().inject(this, inflater, container);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        initLayout();
    }

    private void initLayout(){
        logon.setText(MApplication.getUser().getResult().getNickname());
        config = new ImgSelConfig.Builder(getContext(), loader)
                // 是否多选, 默认true
                .multiSelect(false)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(Color.GRAY)
                // “确定”按钮文字颜色
                .btnTextColor(Color.BLUE)
                // 使用沉浸式状态栏
                .statusBarColor(Color.parseColor("#3F51B5"))
                // 返回图标ResId
                .backResId(R.mipmap.chevron_left)
                // 标题
                .title("图片")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(Color.parseColor("#3F51B5"))
                // 裁剪大小。needCrop为true的时候配置
                .cropSize(1, 1, 200, 200)
                .needCrop(true)
                // 第一个是否显示相机，默认true
                .needCamera(true)
                // 最大选择图片数量，默认9
                .maxNum(1)
                .build();
    }
    @Event(value={R.id.textView16,R.id.circleImageView2,R.id.relativeLayout3,R.id.relativeLayout23,R.id.relativeLayout31})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.relativeLayout3:
                //Toast.makeText(getContext(),"功能开发中",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relativeLayout23:
                //Toast.makeText(getContext(),"功能开发中",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relativeLayout31:
                startActivity(new Intent(getActivity(), ActivityFocus.class));
                break;
            case R.id.textView16:
                Intent intent = new Intent();
                startActivityForResult(new Intent(getContext(), ActivityLogin.class), RequestCode.LOGIN);
                break;
            case R.id.circleImageView2:
                new CircleDialog.Builder(getActivity())
                        .configDialog(new ConfigDialog() {
                            @Override
                            public void onConfig(DialogParams params) {
                                //增加弹出动画
                                //params.animStyle = R.style.dialogWindowAnim;
                            }
                        })
                        .setTitle("上传头像")
                        .setTitleColor(Color.BLUE)
                        .setItems(items, new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0){
                                    // 跳转到图片选择器
                                    ImgSelActivity.startActivity(getActivity(), config, RequestCode.SELECT_IMAGE);
                                }
                            }
                        })
                        .setNegative("取消", null)
                        .configNegative(new ConfigButton() {
                            @Override
                            public void onConfig(ButtonParams params) {
                                //取消按钮字体颜色
                                params.textColor = Color.RED;
                            }
                        });
                break;
        }
    }

    public void setTouxiangUrl(String url){
        //本地文件
        File file = new File(url);
        //加载图片
        Glide.with(this).load(file).into(touxiangView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case ResultCode.REGISTER_SUCCESS:
                Log.d("注册结果回调1:", "成功");
                break;
        }
    }
}
