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
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_HEADER = 2;
    private boolean mShowFooter = true;
    private boolean mShowHeader = true;
    private View mHeaderView;
    private boolean hasMore = true;
    private boolean fadeTips = false; // 变量，是否隐藏了底部的提示
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler

    public void setmDate(List<String> data) {
        Log.v("ooooooooo", "" + data.size());
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void reMove() {
        List<String> m = new ArrayList<String>();
        this.mData = m;
        this.notifyDataSetChanged();
    }

    public void setHeaderView(View headerView) {//add header
        mHeaderView = headerView;
    }

    /**
     * 添加列表项     * @param item
     */
    public void addItem(String bean) {
        isShowFooter(false);
        if (mData != null) {
            mData.add(bean);
            for (int i=0;i<mData.size();i++){
                Log.v("ooooooo","---addItem--"+mData.get(i));
            }
        }
        this.notifyDataSetChanged();
    }

    public GirdAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {//add header
            return new ItemViewHolder(mHeaderView);
        }
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_gird, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (!mShowFooter && !mShowHeader) {
            return TYPE_ITEM;
        }
        if (position == 0) {
            return TYPE_HEADER;//add header
        }
        if ((position + 1 == getItemCount() || mHeaderView == null) && isShowFooter()) { //加载到最后不显示footter
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
        this.notifyDataSetChanged();
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    public void isShowHeader(boolean showHeader) {
        this.mShowHeader = showHeader;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;//add header
        final int pos = getRealPosition(holder);
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).tv.setText(mData.get(pos));
        } else {
            ((GirdAdapter.FooterViewHolder) holder).footTv.setVisibility(View.INVISIBLE);
        }
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        int isFooter = mShowFooter ? 1 : 0;
        int isHeader = mShowHeader ? 1 : 0;

        if (mData == null) {
            return isFooter + isHeader;
        }
        return mData.size() + isFooter + isHeader;
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
            if (v == mHeaderView)
                return;
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
