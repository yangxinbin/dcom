package com.mango.leo.dcom.faq.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.event.activity.EventActivity;
import com.mango.leo.dcom.faq.bean.FaqDetailBean;
import com.mango.leo.dcom.faq.util.FaqJsonUtils;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.DateUtil;
import com.mango.leo.dcom.util.HttpUtils;
import com.mango.leo.dcom.util.RoundImageView;
import com.mango.leo.dcom.util.Urls;
import com.mango.leo.dcom.util.flowview.FlowTagLayout;
import com.mango.leo.dcom.util.flowview.TagAdapter;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FaqDetailActivity extends BaseActivity {

    @Bind(R.id.t_tag)
    TextView tTag;
    @Bind(R.id.t_t)
    TextView tT;
    @Bind(R.id.t_start_time)
    TextView tStartTime;
    @Bind(R.id.t_over_time)
    TextView tOverTime;
    @Bind(R.id.t_c)
    TextView tC;
    @Bind(R.id.t_from)
    TextView tFrom;
    @Bind(R.id.t_type)
    TextView tType;
    @Bind(R.id.t_state)
    TextView tState;
    @Bind(R.id.t_priority)
    TextView tPriority;
    @Bind(R.id.flow_event_layout)
    FlowTagLayout flowEventLayout;
    @Bind(R.id.flow_change_layout)
    FlowTagLayout flowChangeLayout;
    @Bind(R.id.flow_config_layout)
    FlowTagLayout flowConfigLayout;
    @Bind(R.id.t_content)
    TextView tContent;
    @Bind(R.id.imageView_p)
    RoundImageView imageViewP;
    @Bind(R.id.t_why)
    TextView tWhy;
    @Bind(R.id.t_method)
    TextView tMethod;
    @Bind(R.id.t_why_state)
    TextView tWhyState;
    @Bind(R.id.t_why_method)
    TextView tWhyMethod;
    @Bind(R.id.t_why_methodstate)
    TextView tWhyMethodstate;
    @Bind(R.id.t_suggestion)
    TextView tSuggestion;
    @Bind(R.id.t_general)
    TextView tGeneral;
    @Bind(R.id.cardView1)
    CardView cardView1;
    @Bind(R.id.cardView3)
    CardView cardView3;
    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.b_commit)
    Button bCommit;
    private SharedPreferences sharedPreferences;
    private TagAdapter tagAdapter1, tagAdapter2, tagAdapter3;
    private int problemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_detail);
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        ButterKnife.bind(this);
        AppUtils.createLoadDailog(this);
        Log.v("wwwwwwww", "!!!" + getIntent().getStringExtra("id"));
        loadDetail(getIntent().getStringExtra("id"));
    }

    private final MyHandler mHandler = new MyHandler(this);

    @OnClick({R.id.imageView_back, R.id.b_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_back:
                JumpToList();
                break;
            case R.id.b_commit:
                commitProblem();
                break;
        }
    }

    private void commitProblem() {
        final HashMap<String, String> mapParams = new HashMap<String, String>();
        mapParams.clear();
        mapParams.put("problemId", problemId + "");//待定
        mapParams.put("token", sharedPreferences.getString("token", ""));
        HttpUtils.doPost(Urls.HOST + "/api/secure/problem/apply", mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AppUtils.showToast(getBaseContext(), "提交失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            JumpToList();
                            AppUtils.showToast(getBaseContext(), "提交成功");
                        }
                    });
                } else {
                    Log.v("doPostAll", response.body().string() + "^^^^^onFailure^^^^^" + response.code());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AppUtils.showToast(getBaseContext(), "提交失败");
                        }
                    });
                }
            }
        });
    }
    private void JumpToList() {
        Intent intent = new Intent(this, FaqActivity.class);
        startActivity(intent);
        finish();
    }
    private class MyHandler extends Handler {
        private final WeakReference<FaqDetailActivity> mActivity;

        public MyHandler(FaqDetailActivity activity) {
            mActivity = new WeakReference<FaqDetailActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FaqDetailActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        //AppUtils.showToast(getBaseContext(), "搜索成功");
                        AppUtils.dissmissLoadDailog(activity);
                        cardView1.setVisibility(View.VISIBLE);
                        FaqDetailBean faqDetailBean = (FaqDetailBean) msg.obj;
                        initView(faqDetailBean);
                        break;
                    case 1:
                        AppUtils.showToast(getBaseContext(), "请求失败");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void initView(FaqDetailBean faqDetailBean) {
        if (faqDetailBean == null)
            return;
        if (faqDetailBean.getStage() == 0) {
            bCommit.setVisibility(View.VISIBLE);
        }
        problemId = faqDetailBean.getId();
        tTag.setText(faqDetailBean.getTag() + "");
        tT.setText(faqDetailBean.getTitle() + "");
        tStartTime.setText(DateUtil.getDateToString(faqDetailBean.getOccurredOn(), "yyyy-MM-dd HH:mm:ss") + "");
        tOverTime.setText(DateUtil.getDateToString(faqDetailBean.getDeadline(), "yyyy-MM-dd HH:mm:ss") + "");
        tC.setText(faqDetailBean.getCreatedBy().getRealName() + "");
        tFrom.setText(faqDetailBean.getOrigin() + "");
        tType.setText(faqDetailBean.getClassification() + "");
        tState.setText(faqDetailBean.getStage() + "");
        tPriority.setText(faqDetailBean.getPriority() + "");

        tagAdapter1 = new TagAdapter(this);
        flowEventLayout.setAdapter(tagAdapter1);
        tagAdapter1.onlyAddAll(faqDetailBean.getEventTickets());

        tagAdapter2 = new TagAdapter(this);
        flowChangeLayout.setAdapter(tagAdapter2);
        tagAdapter2.onlyAddAll(faqDetailBean.getChangeTickets());

        tagAdapter3 = new TagAdapter(this);
        flowConfigLayout.setAdapter(tagAdapter3);
        tagAdapter3.onlyAddAll(faqDetailBean.getAssetConfigSNs());

        tContent.setText(faqDetailBean.getDescription() + "");
        if (faqDetailBean.getAttachments() != null && faqDetailBean.getAttachments().get(0) != null) {
            imageViewP.setVisibility(View.VISIBLE);
            Glide.with(this).load("http://dcom.hopesen.com.cn" + faqDetailBean.getAttachments().get(0).getUrl()).into(imageViewP);
        }
    }

    private void loadDetail(String id) {
        Log.v("wwwwwwww", "" + Urls.HOST_QUERY_PROBLEM + "?problemId=" + id + "&token=" + sharedPreferences.getString("token", ""));
        HttpUtils.doGet(Urls.HOST_QUERY_PROBLEM + "?problemId=" + id + "&token=" + sharedPreferences.getString("token", ""), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    //Log.v("yyyyyyyyy","*****onResponse******"+response.body().string());
                    FaqDetailBean faqDetailBean = FaqJsonUtils.readDetailBean(response.body().string());//data是json字段获得data的值即对象数组
                    Message message = mHandler.obtainMessage();
                    message.obj = faqDetailBean;
                    message.what = 0;
                    message.sendToTarget();
                } catch (Exception e) {
                    mHandler.sendEmptyMessage(1);
                }
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, FaqActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
