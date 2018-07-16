package com.mango.leo.dcom.faq.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
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
import com.mango.leo.dcom.faq.adapter.FaqAdapter;
import com.mango.leo.dcom.faq.bean.FaqBean;
import com.mango.leo.dcom.faq.bean.ListFaqBean;
import com.mango.leo.dcom.faq.presenter.FaqPresenter;
import com.mango.leo.dcom.faq.presenter.FaqPresenterImpl;
import com.mango.leo.dcom.faq.view.FaqView;
import com.mango.leo.dcom.util.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/7/10.
 */

@SuppressLint("ValidFragment")
class MyFaqFragment extends Fragment implements FaqView {
    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;
    private final int TYPE = 0;
    private FaqPresenter faqPresenter;
    private FaqBean faqBean;
    private int page = 0;
    private LinearLayoutManager mLayoutManager;
    private FaqAdapter adapter;
    private ArrayList<ListFaqBean> mData, mDataAll;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.recycle_view, container, false);
        faqPresenter = new FaqPresenterImpl(this);
        ButterKnife.bind(this, view);
        initRecycle();
        initHeader();
        initSwipeRefreshLayout();
        faqBean = new FaqBean();
        faqPresenter.visitProjects(getActivity(), TYPE, faqBean, page);
        return view;
    }
    private void initRecycle() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycle.setLayoutManager(mLayoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new FaqAdapter(getActivity().getApplicationContext());
        adapter.setOnFaqClickListener(mOnItemClickListener);
        recycle.addOnScrollListener(mOnScrollListener);
        //recycle.setAdapter(adapter);
        recycle.removeAllViews();
        recycle.setAdapter(adapter);
    }

    private FaqAdapter.OnFaqClickListener mOnItemClickListener = new FaqAdapter.OnFaqClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            Log.v("wwwwwwww", adapter.getItem(position) + "---true---" + position+"==="+adapter.getItem(position).getList().get(position%20).getId());
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), FaqDetailActivity.class);
            intent.putExtra("id", adapter.getItem(position).getList().get(position%20).getId()+"");
            startActivity(intent);
            //getActivity().finish();
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
                faqPresenter.visitProjects(getActivity(), TYPE, faqBean, page);
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
                        faqPresenter.visitProjects(getActivity(), TYPE, faqBean, page);//请求刷新
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
    public void addFaqSuccess(final List<ListFaqBean> faqBeans) {
        Log.v("zzzzzzzzz", page + "-------3------" + faqBeans.size());
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (faqBeans == null || faqBeans.size() == 0) {
                    //AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
                    adapter.hasMore(false);//显示没有更多
                    return;
                }
                if (mData == null && mDataAll == null) {
                    mData = new ArrayList<ListFaqBean>();
                    mDataAll = new ArrayList<ListFaqBean>();
                }
                if (mDataAll != null) {
                    mDataAll.clear();
                }
                mDataAll.addAll(faqBeans);
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
    public void addFaqMes(final String s) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getActivity(), s);
            }
        });
    }

    @Override
    public void addFaqFail(final String e) {
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
