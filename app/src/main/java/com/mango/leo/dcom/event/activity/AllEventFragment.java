package com.mango.leo.dcom.event.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.event.adapter.EventAdapter;
import com.mango.leo.dcom.event.adapter.SmartEventAdapter;
import com.mango.leo.dcom.event.bean.EventBean;
import com.mango.leo.dcom.event.bean.ListEventBean;
import com.mango.leo.dcom.event.presenter.EventPresenter;
import com.mango.leo.dcom.event.presenter.EventPresenterImpl;
import com.mango.leo.dcom.event.view.EventView;
import com.mango.leo.dcom.util.AppUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
    SmartRefreshLayout refresh;
    private LinearLayoutManager mLayoutManager;
    private SmartEventAdapter adapter;
    private ArrayList<ListEventBean> mData, mDataAll;
    private int page = 0;
    private EventPresenter eventPresenter;
    private final int TYPE = 1;
    private EventBean eventBean;
    private boolean isFirstEnter = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.smart_recycle, container, false);
        eventPresenter = new EventPresenterImpl(this);
        ButterKnife.bind(this, view);
        initRecycle();
       /* initHeader();
        initSwipeRefreshLayout();*/
        eventBean = new EventBean();
        eventPresenter.visitProjects(getActivity(), TYPE, eventBean, page);
        refreshAndLoadMore();
        return view;
    }
    private void refreshAndLoadMore() {
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mDataAll != null) {
                            mDataAll.clear();
                        }
                        if (mData != null) {
                            mData.clear();
                        }
                        page = 0;
                        Log.v("zzzzzzzzz", "-------onRefresh-------" + page);
                        eventPresenter.visitProjects(getActivity(), TYPE, eventBean, page);//请求刷新
                        refreshLayout.finishRefresh();
                    }
                }, 500);
            }
        });
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Log.v("zzzzzzzzz", "-------onLoadMore-------" + page);
                        eventPresenter.visitProjects(getActivity(), TYPE, eventBean, page);
                        refreshLayout.finishLoadMore();

                    }
                }, 500);
            }
        });
        refresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        refresh.setHeaderHeight(50);

        //触发自动刷新
        if (isFirstEnter) {
            isFirstEnter = false;
            //refresh.autoRefresh();
        } else {
            //mAdapter.refresh(initData());
        }
    }
    private void initRecycle() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycle.setLayoutManager(mLayoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new SmartEventAdapter(getActivity().getApplicationContext());
        adapter.setOnEventClickListener(mOnItemClickListener);
        //recycle.setAdapter(adapter);
        recycle.removeAllViews();
        recycle.setAdapter(adapter);
    }

    private SmartEventAdapter.OnEventClickListener mOnItemClickListener = new SmartEventAdapter.OnEventClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Log.v("wwwwwwww", adapter.getItem(position) + "---true---" + position + "===" + adapter.getItem(position).getList().get(position % 20).getId());
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), EventDetailActivity.class);
            intent.putExtra("id", adapter.getItem(position).getList().get(position % 20).getId() + "");
            startActivity(intent);
            //getActivity().finish();
        }
    };

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
                    AppUtils.showToast(getActivity(), "数据全部加载完毕");
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
                } else {
                    Log.v("zzzzzzzzz", "---- mDataAll.size()---------" +  mDataAll.size());
                    if (mDataAll != null && mDataAll.size() != 0) {
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
            }
        });

    }

    @Override
    public void addEventMes(final String s) {
        if (getActivity() != null)
            getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getActivity(), s);
            }
        });
    }

    @Override
    public void addEventFail(final String e) {
        if (getActivity() != null)
            getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getActivity(), e);
            }
        });
    }
/*    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button
                    // 处理fragment的返回事件
                    Intent intent = new Intent(getActivity(), DcomActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }*/
}
