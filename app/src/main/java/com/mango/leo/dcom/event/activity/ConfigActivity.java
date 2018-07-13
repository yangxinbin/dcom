package com.mango.leo.dcom.event.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.event.adapter.ChooseAdapter;
import com.mango.leo.dcom.event.adapter.GirdAdapter;
import com.mango.leo.dcom.event.bean.ConfigChooseBean;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.HttpUtils;
import com.mango.leo.dcom.util.Urls;
import com.mango.leo.dcom.util.flowview.TagAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
    RecyclerView choose;
    @Bind(R.id.choose_list)
    RecyclerView chooseList;
    private LinearLayoutManager mLayoutManager;
    private ChooseAdapter adapter;
    private List<String> mData, mDataAll;
    private SharedPreferences sharedPreferences;
    private GirdAdapter adapter1;
    private GridLayoutManager mGridLayoutManager;
    private ConfigChooseBean configChooseBean;
    private ConfigChooseBean bean1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        ButterKnife.bind(this);
        configChooseBean = new ConfigChooseBean();
        initRecycle();
        initGird();
        initHeader();
        EventBus.getDefault().register(this);
        loadAsset("");
        search();
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBus(ConfigChooseBean bean) {
        if (bean == null)
            return;
        Log.v("ccccc","!!??!!");
        adapter1.setmDate(bean.getChooses());
        //EventBus.getDefault().removeStickyEvent(ConfigChooseBean.class);
    }
    private void initGird() {
        choose.removeAllViews();
        mGridLayoutManager = new GridLayoutManager(getBaseContext(), 2);
        choose.setLayoutManager(mGridLayoutManager);
        adapter1 = new GirdAdapter(this);
        adapter1.setOnGirdClickListener(mOnDeleteClickListener);
        choose.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        choose.setAdapter(adapter1);
    }

    private GirdAdapter.OnGirdClickListener mOnDeleteClickListener = new GirdAdapter.OnGirdClickListener() {
        @Override
        public void onItemGirdClick(View view, int position) {
            adapter1.reMoveItem(position);
        }
    };

    private void search() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loadAsset(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void loadAsset(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                HttpUtils.doGet(Urls.HOST + "/api/common/list/tags?type=asset" + "&token=" + sharedPreferences.getString("token", "") + "&prefix=" + s, new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        mHandler.sendEmptyMessage(1);
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        try {
                            Message message = mHandler.obtainMessage();
                            message.obj = response.body().string();
                            message.what = 0;
                            message.sendToTarget();
                        } catch (final Exception e) {
                            mHandler.sendEmptyMessage(1);
                        }
                    }
                });
            }
        });
    }

    private final ConfigActivity.MyHandler mHandler = new ConfigActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<ConfigActivity> mActivity;

        public MyHandler(ConfigActivity activity) {
            mActivity = new WeakReference<ConfigActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ConfigActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        //AppUtils.showToast(getBaseContext(), "搜索成功");
                        String s = (String) msg.obj;
                        if (s.equals("[]")) {
                            adapter.reMove();
                            return;
                        }
                        mData = new ArrayList<>();
                        mDataAll = new ArrayList<>();
                        mData = toList(s);
                        adapter.setmDate(toList(s));
                        break;
                    case 1:
                        AppUtils.showToast(getBaseContext(), "搜索失败:");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private List<String> toList(String value) {
        String[] arg = value.replace("[", "").replace("]", "").replaceAll("\"", "").split(",");
        List<String> list = Arrays.asList(arg);
        return list;
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
            if (mData.size() <= 0) {
                return;
            }
            //mDataAll.add(adapter.getItem(position));
            adapter1.addItem(adapter.getItem(position));
            mDataAll.add(adapter.getItem(position));
            Log.v("oooooooo", adapter.getItem(position) + "---true---");
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

/*    private void initHeaderGird() {
        //渲染header布局
        ConstraintLayout h = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams layoutParam = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(1.0f));
        layoutParam.setMargins(0, 0, 0, 20);
        h.setLayoutParams(layoutParam);
        adapter1.setHeaderView(h);
    }*/

    private int dp2px(float v) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, dm);
    }

    @OnClick({R.id.et_back, R.id.et_delete, R.id.state})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.et_back:
                intent = new Intent(this, AddEventActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.et_delete:
                etSearch.setText("");
                break;
            case R.id.state:
                intent = new Intent(this, AddEventActivity.class);
                configChooseBean.setChooses(adapter1.getmData());
                EventBus.getDefault().postSticky(configChooseBean);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, AddEventActivity.class);
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
