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
import com.mango.leo.dcom.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class EventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnEventClickListener mOnEventClickListener;//自注册的接口给调用者用于点击逻辑
    private List<ListEventBean> mData;
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_HEADER = 2;
    private boolean mShowFooter = true;
    private boolean mShowHeader = true;
    private View mHeaderView;
    private boolean hasMore = true;
    private boolean fadeTips = false; // 变量，是否隐藏了底部的提示
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

    public void setHeaderView(View headerView) {//add header
        mHeaderView = headerView;
    }

    /**
     * 添加列表项     * @param item
     */
    public void addItem(ListEventBean bean) {
        isShowFooter(false);
        if (mData != null) {
            mData.add(bean);
            hasMore(true);
        }
        this.notifyDataSetChanged();
    }

    public void hasMore(Boolean b) {
        this.hasMore = b;
        this.notifyDataSetChanged();
    }

    public EventAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {//add header
            return new ItemViewHolder(mHeaderView);
        }
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
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
                    ((ItemViewHolder) holder).textView_stage.setText("关闭");
                }
            }

        } else {
            // 之所以要设置可见，是因为我在没有更多数据时会隐藏了这个footView
            if (hasMore == true) {
                // 不隐藏footView提示
                Log.v("rrrrrrrrr", "--???--");
                // 如果查询数据发现增加之后，就显示正在加载更多
                ((EventAdapter.FooterViewHolder) holder).footTv.setVisibility(View.VISIBLE);
                ((EventAdapter.FooterViewHolder) holder).footTv.setText("正在加载...");
            } else {
                // 如果查询数据发现并没有增加时，就显示没有更多数据了
                ((EventAdapter.FooterViewHolder) holder).footTv.setVisibility(View.VISIBLE);
                ((EventAdapter.FooterViewHolder) holder).footTv.setText("没有更多数据了");
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 隐藏提示条
                        ((EventAdapter.FooterViewHolder) holder).footTv.setVisibility(View.INVISIBLE);
                    }
                }, 2000);
            }
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
            if (v == mHeaderView)
                return;
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
