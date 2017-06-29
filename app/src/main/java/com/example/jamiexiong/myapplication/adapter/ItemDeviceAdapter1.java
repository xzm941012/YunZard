package com.example.jamiexiong.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.example.jamiexiong.myapplication.bean.DeviceItemBean;
import com.example.jamiexiong.myapplication.bean.FocusReaultBean;
import com.google.gson.Gson;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jamiexiong on 2017/6/7.
 */

public class ItemDeviceAdapter1 extends BaseAdapter {

    private List<DeviceItemBean.DeviceResultBean> datas;
    private Context context;
    private RequestQueue mQueue;
    private String url = "http://"+ UrlUtil.url1+"/request?rname=i_plc.Page.mobile.machq.machAttention.machAttention";

    // 构造器
    public ItemDeviceAdapter1(Context context, List<DeviceItemBean.DeviceResultBean> datas) {
        super();
        this.datas = datas;
        this.context = context;
        mQueue = Volley.newRequestQueue(context);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public DeviceItemBean.DeviceResultBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // 保留一个item的控件
        viewHolder holder = null;

        if (convertView == null) {
            // 拿到ListViewItem的布局（一行，需要单独定义一个），转换为View类型的对象
            convertView = View.inflate(context, R.layout.item_device1, null);
            holder = new viewHolder();
            x.view().inject(holder,convertView);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }

        final DeviceItemBean.DeviceResultBean item = getItem(position);
        holder.name.setText(item.getZtName());
        holder.xh.setText(item.getCode());
        holder.dl.setText(" "+item.getDiffData());
        holder.cl.setText(" "+item.getOuts());
        if(item.getMachAttention().equals("0")){
            holder.foucus.setImageDrawable(context.getResources().getDrawable(R.drawable.nofocus));
        }else{
            holder.foucus.setImageDrawable(context.getResources().getDrawable(R.drawable.hadfocus));
        }

        if (item.getStatus().equals("online")) {
            holder.status.setText("正常运行");
            holder.statusimageview.setImageResource(R.color.green);
        } else if (item.getStatus().equals("offline")) {
            holder.status.setText("正常停机");
            holder.statusimageview.setImageResource(R.color.black);
        } else if (item.getStatus().equals("badoff")) {
            holder.status.setText("故障停机");
            holder.statusimageview.setImageResource(R.color.red);
        } else if (item.getStatus().equals("badch")) {
            holder.status.setText("试制故障运行");
            holder.statusimageview.setImageResource(R.color.yellow);
        } else if (item.getStatus().equals("badon")) {
            holder.status.setText("持续故障运行");
            holder.statusimageview.setImageResource(R.color.yellow);
        } else if (item.getStatus().equals("chmod")) {
            holder.status.setText("更换模具");
            holder.statusimageview.setImageResource(R.color.blue);
        } else if (item.getStatus().equals("repair")) {
            holder.status.setText("设备维修");
            holder.statusimageview.setImageResource(R.color.dkgray);
        }
        holder.foucus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String zt1 = item.getMachAttention();
                final String deviceid = item.getId();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        FocusReaultBean resultCode = new Gson().fromJson(response.toString(),FocusReaultBean.class);

                        Log.d("设备列表：", response.toString());

                        if(resultCode.getCode().equals("200")){
                            if(item.getMachAttention().equals("0")){
                                item.setMachAttention("1");
                                Toast.makeText(context,"关注成功",Toast.LENGTH_SHORT).show();
                            }else{
                                item.setMachAttention("0");
                                Toast.makeText(context,"取消关注",Toast.LENGTH_SHORT).show();
                            }
                            ItemDeviceAdapter1.this.notifyDataSetChanged();

                        }else{
                            Toast.makeText(context,"数据异常",Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                        Toast.makeText(context,"网络发生异常",Toast.LENGTH_SHORT).show();

                    }}) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("Attention", zt1);
                        map.put("machID", deviceid);
                        return map;
                    }
                };
                mQueue.add(stringRequest);
            }
        });
        
        //holder.id_num.setText("" + datas.get(position));
        return convertView;
    }

    class viewHolder {
        @ViewInject(R.id.textView10)
        private TextView name;

        @ViewInject(R.id.textView11)
        private TextView xh;

        @ViewInject(R.id.textView13)
        private TextView dl;

        @ViewInject(R.id.textView12)
        private TextView cl;

        @ViewInject(R.id.textView14)
        private TextView status;

        @ViewInject(R.id.imageView33)
        private ImageView foucus;

        @ViewInject(R.id.image12)
        private ImageView statusimageview;
    }
}
