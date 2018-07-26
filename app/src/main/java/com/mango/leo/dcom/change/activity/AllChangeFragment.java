package com.mango.leo.dcom.change.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.change.adapter.SmartChangeAdapter;
import com.mango.leo.dcom.change.bean.ChangeBean;
import com.mango.leo.dcom.change.bean.ListChangeBean;
import com.mango.leo.dcom.change.presenter.ChangePresenter;
import com.mango.leo.dcom.change.presenter.ChangePresenterImpl;
import com.mango.leo.dcom.change.view.ChangeView;
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
class AllChangeFragment extends Fragment implements ChangeView {
    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.refresh)
    SmartRefreshLayout refresh;
    private LinearLayoutManager mLayoutManager;
    private SmartChangeAdapter adapter;
    private ArrayList<ListChangeBean> mData, mDataAll;
    private int page = 0;
    private ChangePresenter changePresenter;
    private final int TYPE = 1;
    private ChangeBean changeBean;
    private boolean isFirstEnter = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.smart_recycle, container, false);
        changePresenter = new ChangePresenterImpl(this);
        ButterKnife.bind(this, view);
        initRecycle();
        changeBean = new ChangeBean();
        changePresenter.visitProjects(getActivity(), TYPE, changeBean, page);
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
                        changePresenter.visitProjects(getActivity(), TYPE, changeBean, page);
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
                        changePresenter.visitProjects(getActivity(), TYPE, changeBean, page);
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
        adapter = new SmartChangeAdapter(getActivity().getApplicationContext());
        adapter.setOnChangeClickListener(mOnItemClickListener);
        //recycle.setAdapter(adapter);
        recycle.removeAllViews();
        recycle.setAdapter(adapter);
    }

    private SmartChangeAdapter.OnChangeClickListener mOnItemClickListener = new SmartChangeAdapter.OnChangeClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Log.v("wwwwwwww", adapter.getItem(position) + "---true---" + position + "===" + adapter.getItem(position).getList().get(position % 20).getId());
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), ChangeDetailActivity.class);
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
    public void addChangeSuccess(final List<ListChangeBean> changeBeans) {
        Log.v("zzzzzzzzz", page + "-------3------" + changeBeans.size());
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (changeBeans == null || changeBeans.size() == 0) {
                    AppUtils.showToast(getActivity(), "数据全部加载完毕");
                    return;
                }
                if (mData == null && mDataAll == null) {
                    mData = new ArrayList<ListChangeBean>();
                    mDataAll = new ArrayList<ListChangeBean>();
                }
                if (mDataAll != null) {
                    mDataAll.clear();
                }
                mDataAll.addAll(changeBeans);
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
    public void addChangeMes(final String s) {
        if (getActivity() != null)
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), s);
                }
            });
    }

    @Override
    public void addChangeFail(final String e) {
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
