package com.example.jamiexiong.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.Util.RelativeDateFormat;
import com.example.jamiexiong.myapplication.bean.HomeBean;
import com.example.jamiexiong.myapplication.bean.HomeBean1;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.ParseException;
import java.util.List;

/**
 * Created by jamiexiong on 2017/6/7.
 */

public class ItemHomeAdapter1 extends BaseAdapter {

    private List<HomeBean1.HomeListBean> datas;
    private Context context;

    // 构造器
    public ItemHomeAdapter1(Context context, List<HomeBean1.HomeListBean> datas) {
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
    public HomeBean1.HomeListBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.list_item_home2, null);
            holder = new viewHolder();
            x.view().inject(holder,convertView);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        HomeBean1.HomeListBean result = getItem(position);
        holder.name2.setText(result.getRouTimeContent());
        if(result.getRouTimestatus().equals("1")){
            holder.name1.setText("告警");
            holder.ztImage.setImageDrawable(context.getResources().getDrawable(R.drawable.message1));
        }else if(result.getRouTimestatus().equals("2")){
            holder.name1.setText("维修");
            holder.ztImage.setImageDrawable(context.getResources().getDrawable(R.drawable.message2));
        }else if(result.getRouTimestatus().equals("3")){
            holder.name1.setText("保养");
            holder.ztImage.setImageDrawable(context.getResources().getDrawable(R.drawable.message3));
        }
        try {
            holder.timetext.setText(RelativeDateFormat.format(result.getUpdateTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(result.getStatus().equals("1")){
            holder.ztView.setVisibility(View.VISIBLE);
            holder.ztText.setVisibility(View.VISIBLE);
        }else{
            holder.ztView.setVisibility(View.INVISIBLE);
            holder.ztText.setVisibility(View.INVISIBLE);
        }

        //holder.id_num.setText("" + datas.get(position));
        return convertView;
    }

    class viewHolder {
        @ViewInject(R.id.imageView29)
        private ImageView ztImage;

        @ViewInject(R.id.status1)
        private TextView name1;

        @ViewInject(R.id.textView27)
        private TextView timetext;

        @ViewInject(R.id.textView34)
        private TextView name2;

        @ViewInject(R.id.textView35)
        private TextView ztText;

        @ViewInject(R.id.imageView65)
        private ImageView ztView;
    }
}
