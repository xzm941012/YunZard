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
import com.example.jamiexiong.myapplication.bean.DeviceItemSS;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by jamiexiong on 2017/6/7.
 */

public class DeviceSSAdapter extends BaseAdapter {

    private List<DeviceItemSS.DeviceItemSSItem> datas;
    private Context context;

    // 构造器
    public DeviceSSAdapter(Context context, List<DeviceItemSS.DeviceItemSSItem> datas) {
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
    public DeviceItemSS.DeviceItemSSItem getItem(int position) {
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
            convertView = View.inflate(context, R.layout.item_device_shishi, null);
            holder = new viewHolder();
            x.view().inject(holder,convertView);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }

        DeviceItemSS.DeviceItemSSItem item = getItem(position);
        holder.name.setText(item.getOperationValue());
        holder.sj.setText(item.getOperationData() + "");
        
        //holder.id_num.setText("" + datas.get(position));
        return convertView;
    }

    class viewHolder {
        @ViewInject(R.id.textView115)
        private TextView name;

        @ViewInject(R.id.textView192)
        private TextView sj;


    }
}
