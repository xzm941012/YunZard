package com.example.jamiexiong.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.bean.DeviceItemBean;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by jamiexiong on 2017/6/7.
 */

public class ItemDeviceAdapter extends BaseAdapter {

    private List<DeviceItemBean.DeviceResultBean> datas;
    private Context context;

    // 构造器
    public ItemDeviceAdapter(Context context, List<DeviceItemBean.DeviceResultBean> datas) {
        super();
        this.datas = datas;
        this.context = context;
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
            convertView = View.inflate(context, R.layout.item_device, null);
            holder = new viewHolder();
            x.view().inject(holder,convertView);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }

        DeviceItemBean.DeviceResultBean item = getItem(position);
        holder.name.setText(item.getZtName());
        holder.xh.setText(item.getCode());
        holder.dl.setText("今日电量："+item.getDiffData());
        holder.cl.setText("今日产量："+item.getOuts());
        if(item.getMachAttention().equals("0")){
            holder.foucus.setImageDrawable(context.getResources().getDrawable(R.mipmap.star_outline));
        }else{
            holder.foucus.setImageDrawable(context.getResources().getDrawable(R.mipmap.star_dark));
        }

        if (item.getStatus().equals("online")) {
            holder.status.setText("正常运行");
            holder.status.setTextColor(Color.GREEN);
        } else if (item.getStatus().equals("offline")) {
            holder.status.setText("正常停机");
            holder.status.setTextColor(Color.BLACK);
        } else if (item.getStatus().equals("badoff")) {
            holder.status.setText("故障停机");
            holder.status.setTextColor(Color.RED);
        } else if (item.getStatus().equals("badch")) {
            holder.status.setText("试制故障运行");
            holder.status.setTextColor(Color.YELLOW);
        } else if (item.getStatus().equals("badon")) {
            holder.status.setText("持续故障运行");
            holder.status.setTextColor(Color.YELLOW);
        } else if (item.getStatus().equals("chmod")) {
            holder.status.setText("更换模具");
            holder.status.setTextColor(Color.BLUE);
        } else if (item.getStatus().equals("repair")) {
            holder.status.setText("设备维修");
            holder.status.setTextColor(Color.DKGRAY);
        }
        
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
    }
}
