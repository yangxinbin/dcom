package com.mango.leo.dcom.change.adapter;

/**
 * Created by admin on 2018/5/28.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.change.bean.RevertBeans;

import java.util.List;

/**
 * Created by qzs on 2017/9/04.
 */
public class RevertAdapter extends RecyclerView.Adapter<RevertAdapter.MyViewHolder> {
    private Context context;
    private List<RevertBeans.RevertItem> list;
    private RevertAdapter.OnRevertClickListener mOnRevertClickListener;//自注册的接口给调用者用于点击逻辑

    public RevertAdapter(Context context, List<RevertBeans.RevertItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_method, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textView_title.setText("计划" + (position+1)+ ":");
        holder.textView_content.setText(list.get(position).getDetail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // 添加数据
    public void addData(int position) {
//   在list中添加数据，并通知条目加入一条
        //list.add(position, "我是" + position);
        //添加动画
        notifyItemInserted(position);
    }

    // 删除数据
    public void removeData() {
        list.clear();
        //删除动画
        notifyDataSetChanged();
    }

    public void setOnRevertClickListener(RevertAdapter.OnRevertClickListener onRevertClickListener) {
        this.mOnRevertClickListener = onRevertClickListener;
    }

    public interface OnRevertClickListener {
        public void onRevertClickEdit(View view, int position);

        public void onRevertClickDelete(View view, int position);

    }

    /**
     * ViewHolder的类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView_delete;
        TextView textView_title, textView_content;
        LinearLayout all;

        //因为删除有可能会删除中间条目，然后会造成角标越界，所以必须整体刷新一下！
        public MyViewHolder(View view) {
            super(view);
            all = view.findViewById(R.id.all);
            imageView_delete = view.findViewById(R.id.imageView_delete);
            textView_title = (TextView) view.findViewById(R.id.textView_title);
            textView_content = (TextView) view.findViewById(R.id.textView_content);
            all.setOnClickListener(this);
            imageView_delete.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.all:
                    mOnRevertClickListener.onRevertClickEdit(view, this.getLayoutPosition());
                    break;
                case R.id.imageView_delete:
                    mOnRevertClickListener.onRevertClickDelete(view, this.getLayoutPosition());
                    break;
            }
        }
    }
}

