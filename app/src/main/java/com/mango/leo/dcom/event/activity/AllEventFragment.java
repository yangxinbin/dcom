package com.mango.leo.dcom.event.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.event.adapter.EventAdapter;
import com.mango.leo.dcom.event.bean.EventBean;
import com.mango.leo.dcom.event.bean.ListEventBean;
import com.mango.leo.dcom.event.presenter.EventPresenter;
import com.mango.leo.dcom.event.presenter.EventPresenterImpl;
import com.mango.leo.dcom.event.view.EventView;
import com.mango.leo.dcom.util.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/7/10.
 */

@SuppressLint("ValidFragment")
class AllEventFragment extends android.support.v4.app.Fragment implements EventView {
    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;
    private LinearLayoutManager mLayoutManager;
    private EventAdapter adapter;
    private ArrayList<ListEventBean> mData, mDataAll;
    private int page = 0;
    private EventPresenter eventPresenter;
    private final int TYPE = 1;
    private EventBean eventBean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.recycle_view, container, false);
        eventPresenter = new EventPresenterImpl(this);
        ButterKnife.bind(this, view);
        initRecycle();
        initHeader();
        initSwipeRefreshLayout();
        eventBean = new EventBean();
        eventPresenter.visitProjects(getActivity(), TYPE, eventBean, page);
        return view;
    }

    private void initRecycle() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycle.setLayoutManager(mLayoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new EventAdapter(getActivity().getApplicationContext());
        adapter.setOnEventClickListener(mOnItemClickListener);
        recycle.addOnScrollListener(mOnScrollListener);
        //recycle.setAdapter(adapter);
        recycle.removeAllViews();
        recycle.setAdapter(adapter);
    }

    private EventAdapter.OnEventClickListener mOnItemClickListener = new EventAdapter.OnEventClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            Log.v("oooooooo", adapter.getItem(position) + "---true---" + position);
            if (mData.size() <= 0) {
                return;
            }
/*            Intent intent = new Intent(getActivity(), ZhaoShanDetailActivity.class);
            intent.putExtra("FavouriteId", adapter.getItem(position).getResponseObject().getContent().get(position%20).getId());
            startActivity(intent);*/
        }
    };
    private int lastVisibleItem;
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();//可见的最后一个item
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            Log.v("zzzzzzzzz", adapter.getItemCount() + "---" + (lastVisibleItem + 1) + "---" + (newState == RecyclerView.SCROLL_STATE_IDLE) + "===" + (lastVisibleItem + 1 == adapter.getItemCount()) + "-------?-----" + adapter.isShowFooter());
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == adapter.getItemCount()
                    && adapter.isShowFooter() && lastVisibleItem - 1 > 8) {//加载判断条件 手指离开屏幕 到了footeritem
                page++;
                Log.v("zzzzzzzzz", "-------2-------" + page);
                eventPresenter.visitProjects(getActivity(), TYPE, eventBean, page);
                Log.v("yyyy", "***onScrollStateChanged******");
            }
        }
    };

    public void initSwipeRefreshLayout() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mDataAll != null) {
                            mDataAll.clear();
                        }
                        if (mData != null) {
                            mData.clear();
                        }
                        refresh.setRefreshing(false);
                        page = 0;
                        eventPresenter.visitProjects(getActivity(), TYPE, eventBean, page);//请求刷新
                    }
                }, 2000);
            }
        });
        refresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void initHeader() {
        //渲染header布局
        ConstraintLayout h = new ConstraintLayout(getActivity());
        ConstraintLayout.LayoutParams layoutParam = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(1.0f));
        layoutParam.setMargins(0, 0, 0, 20);
        h.setLayoutParams(layoutParam);
        adapter.setHeaderView(h);
    }

    private int dp2px(float v) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, dm);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void addEventSuccess(final List<ListEventBean> eventBeans) {
        Log.v("zzzzzzzzz", page + "-------3------" + eventBeans.size());
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (eventBeans == null || eventBeans.size() == 0) {
                    //AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
                    adapter.hasMore(false);//显示没有更多
                    return;
                }
                if (mData == null && mDataAll == null) {
                    mData = new ArrayList<ListEventBean>();
                    mDataAll = new ArrayList<ListEventBean>();
                }
                if (mDataAll != null) {
                    mDataAll.clear();
                }
                mDataAll.addAll(eventBeans);
                if (page == 0) {
                    for (int i = 0; i < mDataAll.size(); i++) {//
                        mData.add(mDataAll.get(i)); //一次显示page= ? 20条数据
                    }
                    Log.v("zzzzzzzzz", "----4---------" + mData.size());
                    adapter.setmDate(mData);
                    if (mDataAll.size() < 8){
                        adapter.hasMore(false);
                    }
                } else {
                    if (mDataAll != null) {
                        //加载更多
                        int i;
                        for (i = 0; i < mDataAll.size(); i++) {
                            if (mDataAll == null) {
                                return;//一开始断网报空指针的情况
                            }
                            adapter.addItem(mDataAll.get(i));//addItem里面记得要notifyDataSetChanged 否则第一次加载不会显示数据
                        }
                    }
                }
                adapter.isShowFooter(true);
            }
        });

    }

    @Override
    public void addEventMes(final String s) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getActivity(), s);
            }
        });
    }

    @Override
    public void addEventFail(final String e) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getActivity(), e);
            }
        });
    }
}
