package com.mango.leo.dcom.change.activity;

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
import com.mango.leo.dcom.change.bean.ChangeDetailBean;
import com.mango.leo.dcom.change.util.ChangeJsonUtils;
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

public class ChangeDetailActivity extends BaseActivity {

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
    @Bind(R.id.t_type)
    TextView tType;
    @Bind(R.id.t_range)
    TextView tRange;
    @Bind(R.id.t_degree)
    TextView tDegree;
    @Bind(R.id.t_risk)
    TextView tRisk;
    @Bind(R.id.flow_event_layout)
    FlowTagLayout flowEventLayout;
    @Bind(R.id.flow_problem_layout)
    FlowTagLayout flowProblemLayout;
    @Bind(R.id.flow_change_layout)
    FlowTagLayout flowChangeLayout;
    @Bind(R.id.flow_config_layout)
    FlowTagLayout flowConfigLayout;
    @Bind(R.id.t_check_content)
    TextView tCheckContent;
    @Bind(R.id.t_content)
    TextView tContent;
    @Bind(R.id.imageView_p)
    RoundImageView imageViewP;
    /*    @Bind(R.id.t_solution)
        TextView tSolution;
        @Bind(R.id.t_back_solution)
        TextView tBackSolution;*/
    @Bind(R.id.t_check)
    TextView tCheck;
    @Bind(R.id.t_re_check)
    TextView tReCheck;
    @Bind(R.id.t_check_state)
    TextView tCheckState;
    @Bind(R.id.imageView_p1)
    RoundImageView imageViewP1;
    @Bind(R.id.t_general)
    TextView tGeneral;
    @Bind(R.id.cardView1)
    CardView cardView1;
    @Bind(R.id.cardView5)
    CardView cardView5;
    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.b_commit)
    Button bCommit;
    /*    @Bind(R.id.imageView_c)
        RoundImageView imageViewC;
        @Bind(R.id.solution)
        LinearLayout solution;
        @Bind(R.id.back_solution)
        LinearLayout backSolution;*/
    private SharedPreferences sharedPreferences;
    private TagAdapter tagAdapter1, tagAdapter2, tagAdapter3, tagAdapter4;
    private int changeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_detail);
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
                commitChange();
                break;
        }
    }
    private void commitChange() {
        final HashMap<String, String> mapParams = new HashMap<String, String>();
        mapParams.clear();
        mapParams.put("changeId", changeId + "");//待定
        mapParams.put("token", sharedPreferences.getString("token", ""));
        HttpUtils.doPost(Urls.HOST + "/api/secure/change/apply", mapParams, new Callback() {
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
        Intent intent = new Intent(this, ChangeActivity.class);
        startActivity(intent);
        finish();
    }

    private class MyHandler extends Handler {
        private final WeakReference<ChangeDetailActivity> mActivity;

        public MyHandler(ChangeDetailActivity activity) {
            mActivity = new WeakReference<ChangeDetailActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ChangeDetailActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        //AppUtils.showToast(getBaseContext(), "搜索成功");
                        AppUtils.dissmissLoadDailog(activity);
                        cardView1.setVisibility(View.VISIBLE);
                        ChangeDetailBean changeDetailBean = (ChangeDetailBean) msg.obj;
                        initView(changeDetailBean);
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

    private void loadDetail(String id) {
        Log.v("wwwwwwww", "" + Urls.HOST_QUERY_CHANGE + "?changeId=" + id + "&token=" + sharedPreferences.getString("token", ""));
        HttpUtils.doGet(Urls.HOST_QUERY_CHANGE + "?changeId=" + id + "&token=" + sharedPreferences.getString("token", ""), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    //Log.v("yyyyyyyyy","*****onResponse******"+response.body().string());
                    ChangeDetailBean changeDetailBean = ChangeJsonUtils.readDetailBean(response.body().string());//data是json字段获得data的值即对象数组
                    Message message = mHandler.obtainMessage();
                    message.obj = changeDetailBean;
                    message.what = 0;
                    message.sendToTarget();
                } catch (Exception e) {
                    mHandler.sendEmptyMessage(1);
                }
            }
        });
    }

    private void initView(ChangeDetailBean changeDetailBean) {
        if (changeDetailBean == null)
            return;
        if (changeDetailBean.getStage() == 0) {
            bCommit.setVisibility(View.VISIBLE);
        }
        changeId = changeDetailBean.getId();
        tTag.setText(changeDetailBean.getTag() + "");
        tT.setText(changeDetailBean.getTitle() + "");
        tStartTime.setText(DateUtil.getDateToString(changeDetailBean.getPlanningTime(), "yyyy-MM-dd HH:mm:ss") + "");
        tOverTime.setText(DateUtil.getDateToString(changeDetailBean.getDeadline(), "yyyy-MM-dd HH:mm:ss") + "");
        tC.setText(changeDetailBean.getRelatedOa() + "");
        tType.setText(changeDetailBean.getChangeType() + "");
        tRange.setText(changeDetailBean.getImpactScope() + "");
        tDegree.setText(changeDetailBean.getImpactLevel() + "");
        tRisk.setText(changeDetailBean.getRiskLevel() + "");

        tagAdapter1 = new TagAdapter(this);
        flowEventLayout.setAdapter(tagAdapter1);
        tagAdapter1.onlyAddAll(changeDetailBean.getEventTickets());

        tagAdapter2 = new TagAdapter(this);
        flowChangeLayout.setAdapter(tagAdapter2);
        tagAdapter2.onlyAddAll(changeDetailBean.getChangeTickets());

        tagAdapter3 = new TagAdapter(this);
        flowConfigLayout.setAdapter(tagAdapter3);
        tagAdapter3.onlyAddAll(changeDetailBean.getAssetConfigSNs());

        tagAdapter4 = new TagAdapter(this);
        flowProblemLayout.setAdapter(tagAdapter4);
        tagAdapter4.onlyAddAll(changeDetailBean.getProblemTickets());

        tCheckContent.setText(changeDetailBean.getCause() + "");
        tContent.setText(changeDetailBean.getContent() + "");
        if (changeDetailBean.getContentAttachments() != null && changeDetailBean.getContentAttachments().get(0) != null) {
            imageViewP.setVisibility(View.VISIBLE);
            Glide.with(this).load("http://dcom.hopesen.com.cn" + changeDetailBean.getContentAttachments().get(0).getUrl()).into(imageViewP);
        }
        //tSolution.setText(changeDetailBean.getSolution() + "");
/*        if (changeDetailBean.getSolution() != null)
            for (int i = 0; i < changeDetailBean.getSolution().size(); i++) {
                TextView textView = new TextView(this);
                textView.setText("方案" + (i + 1) + ":\n" + changeDetailBean.getSolution().get(i).getDetail());
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setTextSize(15);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, 10);//4个参数按顺序分别是左上右下
                textView.setLayoutParams(layoutParams);
                solution.addView(textView, i);
            }
        if (changeDetailBean.getSolutionAttachments() != null && changeDetailBean.getSolutionAttachments().get(0) != null) {
            imageViewC.setVisibility(View.VISIBLE);
            Glide.with(this).load("http://dcom.hopesen.com.cn" + changeDetailBean.getSolutionAttachments().get(0).getUrl()).into(imageViewC);
        }
        //tBackSolution.setText(changeDetailBean.getPlanBReviews() + "");
        if (changeDetailBean.getPlanB() != null)
            for (int j = 0; j < changeDetailBean.getPlanB().size(); j++) {
                TextView textView = new TextView(this);
                textView.setText("计划" + (j + 1) + ":\n" + changeDetailBean.getPlanB().get(j).getDetail());
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setTextSize(15);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, 10);//4个参数按顺序分别是左上右下
                textView.setLayoutParams(layoutParams);
                backSolution.addView(textView, j);
            }*/
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, ChangeActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
