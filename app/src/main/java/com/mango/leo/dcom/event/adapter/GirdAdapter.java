package com.mango.leo.dcom.event.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mango.leo.dcom.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.internal.operators.flowable.FlowableOnErrorReturn;

/**
 * Created by admin on 2018/5/11.
 */

public class GirdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnGirdClickListener mOnGirdClickListener;//自注册的接口给调用者用于点击逻辑
    private List<String> mData = new ArrayList<>();
    public static final int TYPE_ITEM = 0;
    private boolean hasMore = true;
    private boolean fadeTips = false; // 变量，是否隐藏了底部的提示
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler

    public void setmDate(List<String> data) {
        Log.v("uuuuuuuu", "" + data.size());
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void reMove() {
        List<String> m = new ArrayList<String>();
        this.mData = m;
        this.notifyDataSetChanged();
    }

    public void reMoveItem(int position) {
        if (mData != null) {
            mData.remove(position);
            for (int i = 0; i < mData.size(); i++) {
                Log.v("ooooooo", "---addItem--" + mData.get(i));
            }
        }
        this.notifyDataSetChanged();
    }

    public List<String> getmData() {
        return mData;
    }

    /**
     * 添加列表项     * @param item
     */
    public void addItem(String bean) {
        if (mData != null) {
            mData.add(bean);
            for (int i = 0; i < mData.size(); i++) {
                Log.v("ooooooo", "---addItem--" + mData.get(i));
            }
        }
        this.notifyDataSetChanged();
    }

    public GirdAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gird, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int pos = getRealPosition(holder);
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).tv.setText(mData.get(pos));
        } else {
            ((GirdAdapter.FooterViewHolder) holder).footTv.setVisibility(View.GONE);
        }
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return position;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnGirdClickListener(OnGirdClickListener onItemnewsClickListener) {
        this.mOnGirdClickListener = onItemnewsClickListener;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public TextView footTv;

        public FooterViewHolder(View view) {
            super(view);
            footTv = (TextView) itemView.findViewById(R.id.more_data_msg);
        }
    }

    public String getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public interface OnGirdClickListener {
        public void onItemGirdClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv;
        private ImageView imageView_delete;

        public ItemViewHolder(View v) {
            super(v);
            tv = (TextView) v.findViewById(R.id.tv_c);
            imageView_delete = (ImageView) v.findViewById(R.id.imageView_delete_c);
            imageView_delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnGirdClickListener != null) {
                mOnGirdClickListener.onItemGirdClick(view, this.getLayoutPosition());
            }
        }
    }
}
