package com.mango.leo.dcom.event.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.event.bean.EventDetailBean;
import com.mango.leo.dcom.event.util.EventJsonUtils;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.DateUtil;
import com.mango.leo.dcom.util.HttpUtils;
import com.mango.leo.dcom.util.RoundImageView;
import com.mango.leo.dcom.util.Urls;
import com.mango.leo.dcom.util.flowview.FlowTagLayout;
import com.mango.leo.dcom.util.flowview.TagAdapter;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EventDetailActivity extends BaseActivity {

    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.t_tag)
    TextView tTag;
    @Bind(R.id.t_t)
    TextView tT;
    @Bind(R.id.t_p)
    TextView tP;
    @Bind(R.id.t_time)
    TextView tTime;
    @Bind(R.id.t_c)
    TextView tC;
    @Bind(R.id.t_from)
    TextView tFrom;
    @Bind(R.id.t_type)
    TextView tType;
    @Bind(R.id.t_level)
    TextView tLevel;
    @Bind(R.id.t_se)
    TextView tSe;
    @Bind(R.id.t_range)
    TextView tRange;
    @Bind(R.id.flow_layout)
    FlowTagLayout flowLayout;
    @Bind(R.id.t_content)
    TextView tContent;
    @Bind(R.id.imageView_p)
    RoundImageView imageViewP;
    @Bind(R.id.t_task)
    TextView tTask;
    @Bind(R.id.t_task_time)
    TextView tTaskTime;
    @Bind(R.id.t_task_P)
    TextView tTaskP;
    @Bind(R.id.t_c_time)
    TextView tCTime;
    private SharedPreferences sharedPreferences;
    private TagAdapter tagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        ButterKnife.bind(this);
        Log.v("wwwwwwww", "!!!" + getIntent().getStringExtra("id"));
        loadDetail(getIntent().getStringExtra("id"));
    }

    private void loadDetail(String id) {
        Log.v("wwwwwwww", "" + Urls.HOST_QUERY_EVENT + "?eventId=" + id + "&token=" + sharedPreferences.getString("token", ""));
        HttpUtils.doGet(Urls.HOST_QUERY_EVENT + "?eventId=" + id + "&token=" + sharedPreferences.getString("token", ""), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    //Log.v("yyyyyyyyy","*****onResponse******"+response.body().string());
                    EventDetailBean eventDetailBean = EventJsonUtils.readDetailBean(response.body().string());//data是json字段获得data的值即对象数组
                    Message message = mHandler.obtainMessage();
                    message.obj = eventDetailBean;
                    message.what = 0;
                    message.sendToTarget();
                } catch (Exception e) {
                    mHandler.sendEmptyMessage(1);
                }
            }
        });
    }

    private final MyHandler mHandler = new MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<EventDetailActivity> mActivity;

        public MyHandler(EventDetailActivity activity) {
            mActivity = new WeakReference<EventDetailActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            EventDetailActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        //AppUtils.showToast(getBaseContext(), "搜索成功");
                        EventDetailBean eventDetailBean = (EventDetailBean) msg.obj;
                        initView(eventDetailBean);
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

    private void initView(EventDetailBean eventDetailBean) {
        if (eventDetailBean == null)
            return;
        tTag.setText(eventDetailBean.getTag() + "");
        tT.setText(eventDetailBean.getTitle() + "");
        tP.setText(eventDetailBean.getCreatedBy().getRealName() + "");
        tTime.setText(DateUtil.getDateToString(eventDetailBean.getOccurredOn(), "yyyy-MM-dd HH:mm:ss") + "");
        if(eventDetailBean.getClaimedBy() != null)
        tC.setText(eventDetailBean.getClaimedBy().getRealName() + "");
        tCTime.setText(DateUtil.getDateToString(eventDetailBean.getCreatedOn(), "yyyy-MM-dd HH:mm:ss") + "");
        tFrom.setText(eventDetailBean.getOrigin()+"");
        tType.setText(eventDetailBean.getType()+"");
        tLevel.setText("级别-"+eventDetailBean.getLevel()+"");
        tSe.setText(eventDetailBean.getSeverity()+"");
        tRange.setText(eventDetailBean.getScope()+"");
        tContent.setText(eventDetailBean.getDescription()+"");
        tagAdapter = new TagAdapter(this);
        flowLayout.setAdapter(tagAdapter);
        tagAdapter.onlyAddAll(eventDetailBean.getAssetConfigSNs());
        if (eventDetailBean.getAttachments() != null && eventDetailBean.getAttachments().get(0) != null){
            imageViewP.setVisibility(View.VISIBLE);
            Glide.with(this).load("http://dcom.hopesen.com.cn" + eventDetailBean.getAttachments().get(0).getUrl()).into(imageViewP);
        }else {
            imageViewP.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.imageView_back)
    public void onViewClicked() {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.v("yyyyy","d::::");
            Intent intent = new Intent(this, EventActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
