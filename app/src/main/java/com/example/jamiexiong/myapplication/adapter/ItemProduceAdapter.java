package com.example.jamiexiong.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jamiexiong.myapplication.R;
import com.example.jamiexiong.myapplication.bean.ProduceBean;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by jamiexiong on 2017/6/7.
 */

public class ItemProduceAdapter extends BaseAdapter {

    private List<ProduceBean.ResultBean> datas;
    private Context context;

    // 构造器
    public ItemProduceAdapter(Context context, List datas) {
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
    public ProduceBean.ResultBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.item_produce, null);
            holder = new viewHolder();
            x.view().inject(holder,convertView);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }

        ProduceBean.ResultBean resultBean = getItem(position);
        holder.bm.setText(resultBean.getPOCode());
        holder.zt.setText(resultBean.getState());
        holder.xdsl.setText(resultBean.getQty());
        holder.cj.setText(resultBean.getZtName());
        holder.sb.setText(resultBean.getCMac());
        holder.gx.setText(resultBean.getCProcedureName());
        holder.sj1.setText(resultBean.getDPlanSTime());
        holder.sj2.setText(resultBean.getDPlanETime());
        holder.dd.setText(resultBean.getSalesOrder());


        //holder.id_num.setText("" + datas.get(position));
        return convertView;
    }

    class viewHolder {
        @ViewInject(R.id.bm)
        private TextView bm;

        @ViewInject(R.id.zt)
        private TextView zt;

        @ViewInject(R.id.cj)
        private TextView cj;

        @ViewInject(R.id.sb)
        private TextView sb;

        @ViewInject(R.id.gx)
        private TextView gx;

        @ViewInject(R.id.xdsl)
        private TextView xdsl;

        @ViewInject(R.id.dd)
        private TextView dd;

        @ViewInject(R.id.sj1)
        private TextView sj1;

        @ViewInject(R.id.sj2)
        private TextView sj2;
    }
}
