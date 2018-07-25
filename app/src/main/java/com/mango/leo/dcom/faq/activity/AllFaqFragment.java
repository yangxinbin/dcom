package com.mango.leo.dcom.faq.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.mango.leo.dcom.faq.adapter.SmartFaqAdapter;
import com.mango.leo.dcom.faq.bean.FaqBean;
import com.mango.leo.dcom.faq.bean.ListFaqBean;
import com.mango.leo.dcom.faq.presenter.FaqPresenter;
import com.mango.leo.dcom.faq.presenter.FaqPresenterImpl;
import com.mango.leo.dcom.faq.view.FaqView;
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
class AllFaqFragment extends Fragment implements FaqView {
    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.refresh)
    SmartRefreshLayout refresh;
    private final int TYPE = 1;
    private FaqPresenter faqPresenter;
    private FaqBean faqBean;
    private int page = 0;
    private LinearLayoutManager mLayoutManager;
    private SmartFaqAdapter adapter;
    private ArrayList<ListFaqBean> mData, mDataAll;
    private boolean isFirstEnter = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.smart_recycle, container, false);
        faqPresenter = new FaqPresenterImpl(this);
        ButterKnife.bind(this, view);
        initRecycle();
        faqBean = new FaqBean();
        faqPresenter.visitProjects(getActivity(), TYPE, faqBean, page);
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
                        faqPresenter.visitProjects(getActivity(), TYPE, faqBean, page);
                        refreshLayout.finishRefresh();
                    }
                }, 2000);
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
                        faqPresenter.visitProjects(getActivity(), TYPE, faqBean, page);
                        refreshLayout.finishLoadMore();

                    }
                }, 2000);
            }
        });
        refresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        refresh.setHeaderHeight(60);

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
        adapter = new SmartFaqAdapter(getActivity().getApplicationContext());
        adapter.setOnFaqClickListener(mOnItemClickListener);
        recycle.removeAllViews();
        recycle.setAdapter(adapter);
    }

    private SmartFaqAdapter.OnFaqClickListener mOnItemClickListener = new SmartFaqAdapter.OnFaqClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Log.v("wwwwwwww", adapter.getItem(position) + "---true---" + position + "===" + adapter.getItem(position).getList().get(position % 20).getId());
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), FaqDetailActivity.class);
            intent.putExtra("id", adapter.getItem(position).getList().get(position % 20).getId() + "");
            startActivity(intent);
        }
    };

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
                    AppUtils.showToast(getActivity(), "数据全部加载完毕");
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
            }
        });
    }

    @Override
    public void addFaqMes(final String s) {
        if (getActivity() != null)
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), s);
                }
            });
    }

    @Override
    public void addFaqFail(final String e) {
        if (getActivity() != null)
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), e);
                }
            });
    }
}
