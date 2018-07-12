package com.mango.leo.dcom.event.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.event.adapter.ChooseAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfigActivity extends AppCompatActivity {

    @Bind(R.id.et_back)
    ImageView etBack;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.et_delete)
    ImageView etDelete;
    @Bind(R.id.state)
    TextView state;
    @Bind(R.id.choose)
    GridView choose;
    @Bind(R.id.choose_list)
    RecyclerView chooseList;
    private LinearLayoutManager mLayoutManager;
    private ChooseAdapter adapter;
    private ArrayList<String> mData, mDataAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        ButterKnife.bind(this);
        initRecycle();
        initHeader();
    }
    private void initRecycle() {
        mLayoutManager = new LinearLayoutManager(this);
        chooseList.setLayoutManager(mLayoutManager);
        chooseList.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new ChooseAdapter(this);
        adapter.setOnChooseClickListener(mOnItemClickListener);
        chooseList.removeAllViews();
        chooseList.setAdapter(adapter);
    }
    private ChooseAdapter.OnChooseClickListener mOnItemClickListener = new ChooseAdapter.OnChooseClickListener() {
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
    private void initHeader() {
        //渲染header布局
        ConstraintLayout h = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams layoutParam = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(1.0f));
        layoutParam.setMargins(0, 0, 0, 20);
        h.setLayoutParams(layoutParam);
        adapter.setHeaderView(h);
    }
    private int dp2px(float v) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, dm);
    }
    @OnClick({R.id.et_back, R.id.et_delete, R.id.state})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_back:
                break;
            case R.id.et_delete:
                break;
            case R.id.state:
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, EventActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
