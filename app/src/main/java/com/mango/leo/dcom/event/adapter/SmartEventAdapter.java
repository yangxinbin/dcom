package com.mango.leo.dcom.event.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.event.bean.ListEventBean;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class SmartEventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnEventClickListener mOnEventClickListener;//自注册的接口给调用者用于点击逻辑
    private List<ListEventBean> mData = new ArrayList<>();
    public static final int TYPE_ITEM = 0;
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler

    public void setmDate(List<ListEventBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void reMove() {
        List<ListEventBean> m = new ArrayList<ListEventBean>();
        this.mData = m;
        this.notifyDataSetChanged();
    }
    /**
     * 添加列表项     * @param item
     */
    public void addItem(ListEventBean bean) {
        if (mData != null) {
            mData.add(bean);
        }
        this.notifyDataSetChanged();
    }


    public SmartEventAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;

    }

    @Override
    public int getItemViewType(int position) {
            return TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int pos = position;
        if (holder instanceof ItemViewHolder) {
            if (((ItemViewHolder) holder) != null && mData.get(pos).getList() != null) {
                Log.v("yyyyy", "====pos======" + pos % 20);//
                ((ItemViewHolder) holder).textView_title.setText(mData.get(pos).getList().get(pos % 20).getTitle());
                ((ItemViewHolder) holder).textView_time.setText(DateUtil.getDateToString(mData.get(pos).getList().get(pos % 20).getCreatedOn(),"yyyy-MM-dd HH:mm:ss"));
                if (mData.get(pos).getList().get(pos % 20).getStage() == 0){
                    ((ItemViewHolder) holder).textView_stage.setText("未提交");
                }else if (mData.get(pos).getList().get(pos % 20).getStage() == 1){
                    ((ItemViewHolder) holder).textView_stage.setText("已提交");
                }else if (mData.get(pos).getList().get(pos % 20).getStage() == 2){
                    ((ItemViewHolder) holder).textView_stage.setText("已指派");
                }else if (mData.get(pos).getList().get(pos % 20).getStage() == 3){
                    ((ItemViewHolder) holder).textView_stage.setText("处理中");
                }else if (mData.get(pos).getList().get(pos % 20).getStage() == 4){
                    ((ItemViewHolder) holder).textView_stage.setText("已处理");
                }else if (mData.get(pos).getList().get(pos % 20).getStage() == 5){
                    ((ItemViewHolder) holder).textView_stage.setText("已关闭");
                }
            }

        }
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnEventClickListener(OnEventClickListener onItemnewsClickListener) {
        this.mOnEventClickListener = onItemnewsClickListener;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public TextView footTv;

        public FooterViewHolder(View view) {
            super(view);
            footTv = (TextView) itemView.findViewById(R.id.more_data_msg);
        }
    }

    public ListEventBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public interface OnEventClickListener {
        public void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView_title, textView_time, textView_stage;

        public ItemViewHolder(View v) {
            super(v);
            textView_title = (TextView) v.findViewById(R.id.textView_title);
            textView_time = (TextView) v.findViewById(R.id.textView_time);
            textView_stage = (TextView) v.findViewById(R.id.textView_stage);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnEventClickListener != null) {
                mOnEventClickListener.onItemClick(view, this.getLayoutPosition());
            }
        }
    }
}
