package com.mango.leo.dcom.rotor;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.rotor.adapter.BasicAdapter;
import com.mango.leo.dcom.rotor.bean.RotorBean;
import com.mango.leo.dcom.scan.EditScanActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class BasicActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.recycle_ro)
    RecyclerView recycleRo;
    @Bind(R.id.refresh_ro)
    SwipeRefreshLayout refreshRo;
    private LinearLayoutManager mLayoutManager;
    private BasicAdapter adapter;
    private ArrayList<RotorBean.LogBean> mData,mDataAll;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        ButterKnife.bind(this);
        initRecycle();
        initHeader();
        initSwipeRefreshLayout();
    }

    private void initRecycle() {
        mLayoutManager = new LinearLayoutManager(this);
        recycleRo.setLayoutManager(mLayoutManager);
        recycleRo.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        //recycleRo.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));//左滑
        adapter = new BasicAdapter(getApplicationContext());
        adapter.setOnRotorClickListener(mOnItemClickListener);
        recycleRo.addOnScrollListener(mOnScrollListener);
        //recycleRo.setAdapter(adapter);
        recycleRo.removeAllViews();
        recycleRo.setAdapter(adapter);
    }
    private BasicAdapter.OnRotorClickListener mOnItemClickListener = new BasicAdapter.OnRotorClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            Log.v("oooooooo",adapter.getItem(position)+"---true---"+position);
            if (mData.size() <= 0) {
                return;
            }
            //Intent intent = new Intent(this, ZhaoShanDetailActivity.class);
            //intent.putExtra("FavouriteId", adapter.getItem(position).getResponseObject().getContent().get(position%20).getId());
            //startActivity(intent);
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
            Log.v("zzzzzzzzz",(newState == RecyclerView.SCROLL_STATE_IDLE)+"==="+(lastVisibleItem + 1 == adapter.getItemCount())+"-------?-----"+adapter.isShowFooter());
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == adapter.getItemCount()
                    && adapter.isShowFooter()) {//加载判断条件 手指离开屏幕 到了footeritem
                page++;
                Log.v("zzzzzzzzz","-------2-------"+page);
                loadHistoryLog();
                Log.v("yyyy", "***onScrollStateChanged******");
            }
        }
    };
    private void initHeader() {
        //渲染header布局
        View header = LayoutInflater.from(this).inflate(R.layout.header, null);
        LinearLayout h = (LinearLayout) header.findViewById(R.id.header);
        ImageView imageView_back = (ImageView) header.findViewById(R.id.imageView_back);
        TextView textView_xunjian = (TextView) header.findViewById(R.id.textView_xunjian);
        ImageView imageView_edit = (ImageView) header.findViewById(R.id.imageView_edit);
        ConstraintLayout.LayoutParams layoutParam = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParam.setMargins(0, 0, 0, 20);
        h.setLayoutParams(layoutParam);
        imageView_back.setOnClickListener(this);
        textView_xunjian.setOnClickListener(this);
        imageView_edit.setOnClickListener(this);
        adapter.setHeaderView(h);
    }
    private void initSwipeRefreshLayout() {
        refreshRo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mData != null && mDataAll != null) {
                            mDataAll.clear();//一定要加上否则会报越界异常 不执行代码加载的if判断
                            mData.clear();
                        }
                        refreshRo.setRefreshing(false);
                        loadHistoryLog();//请求刷新
                    }
                }, 2000);
            }
        });
        refreshRo.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void loadHistoryLog() {
    }

/*    @OnClick({R.id.imageView_back, R.id.textView_xunjian, R.id.imageView_edit})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                intent = new Intent(this, EditScanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textView_xunjian:
                intent = new Intent(this, XunJianActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_edit:
                break;
        }
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, EditScanActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.imageView_back:
                intent = new Intent(this, EditScanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textView_xunjian:
                intent = new Intent(this, XunJianActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_edit:
                intent = new Intent(this, XunJianActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
