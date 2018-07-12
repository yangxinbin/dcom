package com.mango.leo.dcom.event.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.event.bean.ListEventBean;
import com.mango.leo.dcom.event.presenter.EventPresenter;
import com.mango.leo.dcom.event.presenter.EventPresenterImpl;
import com.mango.leo.dcom.event.view.EventView;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.DateUtil;
import com.mango.leo.dcom.util.flowview.FlowTagLayout;
import com.mango.leo.dcom.util.widget.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddEventActivity extends AppCompatActivity implements EventView {
    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.editText_event_flag)
    EditText editTextEventFlag;
    @Bind(R.id.editText_event_title)
    EditText editTextEventTitle;
    @Bind(R.id.textView_event_time)
    TextView textViewEventTime;
    @Bind(R.id.linearLayout_event_time)
    LinearLayout linearLayoutEventTime;
    @Bind(R.id.editText_event_people)
    EditText editTextEventPeople;
    @Bind(R.id.textView_event_from)
    TextView textViewEventFrom;
    @Bind(R.id.linearLayout_event_from)
    LinearLayout linearLayoutEventFrom;
    @Bind(R.id.textView_event_type)
    TextView textViewEventType;
    @Bind(R.id.linearLayout_event_type)
    LinearLayout linearLayoutEventType;
    @Bind(R.id.textView_event_level)
    TextView textViewEventLevel;
    @Bind(R.id.linearLayout_event_level)
    LinearLayout linearLayoutEventLevel;
    @Bind(R.id.textView_event_grave)
    TextView textViewEventGrave;
    @Bind(R.id.linearLayout_event_grave)
    LinearLayout linearLayoutEventGrave;
    @Bind(R.id.editText_event_range)
    EditText editTextEventRange;
    @Bind(R.id.flow_layout)
    FlowTagLayout flowLayout;
    @Bind(R.id.editText_description)
    EditText editTextDescription;
    @Bind(R.id.imageView_pic)
    ImageView imageViewPic;
    @Bind(R.id.config)
    LinearLayout config;
    @Bind(R.id.b_save)
    Button bSave;
    @Bind(R.id.b_save_commit)
    Button bSaveCommit;
    private EventPresenter eventPresenter;
    private SharedPreferences sharedPreferences;
    private CustomDatePicker customDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        ButterKnife.bind(this);
        eventPresenter = new EventPresenterImpl(this);
        initView();
        initDatePicker();
        //eventPresenter.visitProjects();
    }

    private void initView() {
        StringBuffer stringBuffer = new StringBuffer();
        String s = DateUtil.getDateToString(System.currentTimeMillis(),"yyyyMMddHHmm").substring(2,12);
        stringBuffer.append(s);
        for (int i = 0; i < 3; i++) {
            stringBuffer.append((char) (Math.random() * 26 + 'A'));
        }
        editTextEventFlag.setText(stringBuffer.toString());
        editTextEventPeople.setText(sharedPreferences.getString("realname", ""));
    }
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        //textViewEventTime.setText(now);
        customDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                textViewEventTime.setText(time);
                textViewEventTime.setTextColor(getResources().getColor(R.color.white));
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker.showSpecificTime(true); // 显示时和分
        customDatePicker.setIsLoop(true); // 允许循环滚动
    }
    @Override
    public void addEventSuccess(List<ListEventBean> eventBeans) {

    }

    @Override
    public void addEventMes(String s) {
        AppUtils.showToast(this, s);
    }

    @Override
    public void addEventFail(String e) {

    }

    @OnClick({R.id.imageView_back, R.id.linearLayout_event_time, R.id.linearLayout_event_from, R.id.linearLayout_event_type, R.id.linearLayout_event_level, R.id.linearLayout_event_grave, R.id.imageView_pic, R.id.config, R.id.b_save, R.id.b_save_commit})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                intent = new Intent(this, EventActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.linearLayout_event_time:
                // 日期格式为yyyy-MM-dd HH:mm
                //customDatePicker.show(textViewEventTime.getText().toString());
                customDatePicker.show("1990-01-01 00:00");
                break;
            case R.id.linearLayout_event_from:
                break;
            case R.id.linearLayout_event_type:
                break;
            case R.id.linearLayout_event_level:
                break;
            case R.id.linearLayout_event_grave:
                break;
            case R.id.imageView_pic:
                break;
            case R.id.config:
                break;
            case R.id.b_save:
                break;
            case R.id.b_save_commit:
                break;
        }
    }
}
