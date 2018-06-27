package com.mango.leo.dcom.rotor.adapter;

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
import com.mango.leo.dcom.rotor.bean.RotorBean;
import com.mango.leo.dcom.util.DateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by admin on 2018/5/11.
 */

public class BasicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnRotorClickListener mOnRotorClickListener;//自注册的接口给调用者用于点击逻辑
    private List<RotorBean.LogBean> mData;
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_HEADER = 2;
    private boolean mShowFooter = true;
    private boolean mShowHeader = true;
    private View mHeaderView;
    private boolean hasMore;
    private boolean fadeTips = false; // 变量，是否隐藏了底部的提示
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler

    public void setmDate(List<RotorBean.LogBean> data) {
        //Collections.reverse(data);//倒序
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void reMove() {
        List<RotorBean.LogBean> m = new ArrayList<RotorBean.LogBean>();
        this.mData = m;
        this.notifyDataSetChanged();
    }

    public void setHeaderView(View headerView) {//add header
        mHeaderView = headerView;
    }

    /**
     * 添加列表项     * @param item
     */
    public void addItem(RotorBean.LogBean bean) {
        isShowFooter(false);
        if (mData != null) {
            mData.add(bean);
            hasMore = true;

        }
        this.notifyDataSetChanged();
    }

    public BasicAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {//add header
            return new ItemViewHolder(mHeaderView);
        }
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ro_item, parent, false);
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

            if (((ItemViewHolder) holder) != null && mData.get(pos) != null) {
                ((ItemViewHolder) holder).t_n.setText(mData.get(pos).getInspectionBy());
                ((ItemViewHolder) holder).t_t.setText(DateUtil.getDateToString(mData.get(pos).getInspectionOn(), "yyyy-MM-dd HH:mm:ss"));
                if (mData.get(pos).getPowerLineStatus().equals("normal")) {
                    ((ItemViewHolder) holder).t_s1.setText("正常");
                    ((ItemViewHolder) holder).t_s1.setTextColor(context.getResources().getColor(R.color.green));
                } else {
                    ((ItemViewHolder) holder).t_s1.setText("异常");
                    ((ItemViewHolder) holder).t_s1.setTextColor(context.getResources().getColor(R.color.red));
                }
                if (mData.get(pos).getNetworkCableStatus().equals("normal")) {
                    ((ItemViewHolder) holder).t_s2.setText("正常");
                    ((ItemViewHolder) holder).t_s2.setTextColor(context.getResources().getColor(R.color.green));
                } else {
                    ((ItemViewHolder) holder).t_s2.setText("异常");
                    ((ItemViewHolder) holder).t_s2.setTextColor(context.getResources().getColor(R.color.red));
                }
                if (mData.get(pos).getIndicatorStatus().equals("normal")) {
                    ((ItemViewHolder) holder).t_s3.setText("正常");
                    ((ItemViewHolder) holder).t_s3.setTextColor(context.getResources().getColor(R.color.green));
                } else {
                    ((ItemViewHolder) holder).t_s3.setText("异常");
                    ((ItemViewHolder) holder).t_s3.setTextColor(context.getResources().getColor(R.color.red));
                }
                if (mData.get(pos).getPowerLineStatus().equals("normal") && mData.get(pos).getNetworkCableStatus().equals("normal") && mData.get(pos).getIndicatorStatus().equals("normal")) {
                    ((ItemViewHolder) holder).t_s.setText("正常");
                    ((ItemViewHolder) holder).t_s.setTextColor(context.getResources().getColor(R.color.green));
                } else {
                    ((ItemViewHolder) holder).t_s.setText("异常");
                    ((ItemViewHolder) holder).t_s.setTextColor(context.getResources().getColor(R.color.red));
                }

            }
        } else {
            //if (mData.size() > 0) {
            // 如果查询数据发现并没有增加时，就显示没有更多数据了
            ((BasicAdapter.FooterViewHolder) holder).footTv.setText("没有更多数据了");

            // 然后通过延时加载模拟网络请求的时间，在500ms后执行
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 隐藏提示条
                    ((BasicAdapter.FooterViewHolder) holder).footTv.setVisibility(View.INVISIBLE);
                    // 将fadeTips设置true
                    fadeTips = true;
                    // hasMore设为true是为了让再次拉到底时，会先显示正在加载更多
                    hasMore = true;
                }
            }, 2000);
            //}
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

    public void setOnRotorClickListener(OnRotorClickListener onItemnewsClickListener) {
        this.mOnRotorClickListener = onItemnewsClickListener;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public TextView footTv;

        public FooterViewHolder(View view) {
            super(view);
            footTv = (TextView) itemView.findViewById(R.id.more_data_msg);
        }
    }

    public RotorBean.LogBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public interface OnRotorClickListener {
        public void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView t_n, t_s, t_t, t_s1, t_s2, t_s3;

        public ItemViewHolder(View v) {
            super(v);
            if (v == mHeaderView)
                return;
            t_n = (TextView) v.findViewById(R.id.textView_n);
            t_s = (TextView) v.findViewById(R.id.textView_state);
            t_t = (TextView) v.findViewById(R.id.textView_time);
            t_s1 = (TextView) v.findViewById(R.id.textView_state1);
            t_s2 = (TextView) v.findViewById(R.id.textView_state2);
            t_s3 = (TextView) v.findViewById(R.id.textView_state3);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnRotorClickListener != null) {
                mOnRotorClickListener.onItemClick(view, this.getLayoutPosition());
                Log.v("oooooooo", "---onb---" + this.getLayoutPosition());
            }
        }
    }
}
