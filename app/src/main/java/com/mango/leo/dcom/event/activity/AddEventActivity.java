package com.mango.leo.dcom.event.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.adapter.ListAndGirdDownAdapter;
import com.mango.leo.dcom.event.bean.ConfigBean;
import com.mango.leo.dcom.event.bean.EventBean;
import com.mango.leo.dcom.event.bean.ListEventBean;
import com.mango.leo.dcom.event.presenter.EventPresenter;
import com.mango.leo.dcom.event.presenter.EventPresenterImpl;
import com.mango.leo.dcom.event.util.EventJsonUtils;
import com.mango.leo.dcom.event.view.EventView;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.DateUtil;
import com.mango.leo.dcom.util.HttpUtils;
import com.mango.leo.dcom.util.JsonMap;
import com.mango.leo.dcom.util.JsonUtils;
import com.mango.leo.dcom.util.Urls;
import com.mango.leo.dcom.util.flowview.FlowTagLayout;
import com.mango.leo.dcom.util.widget.CustomDatePicker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddEventActivity extends AppCompatActivity implements EventView, AdapterView.OnItemClickListener {
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
    private ListAndGirdDownAdapter adapter;
    private Dialog dialog;
    private List<String> list1, list2, list3, list4;
    private int currentPosition1 = -1, currentPosition2 = -1, currentPosition3 = -1, currentPosition4 = -1;
    private EventBean eventBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        ButterKnife.bind(this);
        eventPresenter = new EventPresenterImpl(this);
        initView();
        initDatePicker();
        initDateFromWeb();
        eventBean = new EventBean();
        //eventPresenter.visitProjects();
    }

    private void initDateFromWeb() {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils.doGet(Urls.HOST + "/api/common/tenant/config" + "?token=" + sharedPreferences.getString("token", ""), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.v("ppppppppppp", "__IOException_--");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            //Log.v("ppppppppppp", "--listC--" +response.body().string());
                            final List<ConfigBean> listC = EventJsonUtils.readJsonConfigBean(response.body().string(), "content");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.v("ppppppppppp", "--listC--" + listC.size());
                                }
                            });
                        } catch (Exception e) {
                            //Log.v("ppppppppppp", "__eee_--" + response.body().string());
                        }
                    }
                });
            }
        }).start();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");

    }

    private void initView() {
        StringBuffer stringBuffer = new StringBuffer();
        String s = DateUtil.getDateToString(System.currentTimeMillis(), "yyyyMMddHHmm").substring(2, 12);
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
                if (list1 != null && list1.size() != 0) {
                    showPopupWindow(this, list1, 1);
                    adapter.setCheckItem(currentPosition1);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
                break;
            case R.id.linearLayout_event_type:
                if (list2 != null && list2.size() != 0) {
                    showPopupWindow(this, list2, 2);
                    adapter.setCheckItem(currentPosition2);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
                break;
            case R.id.linearLayout_event_level:
                if (list3 != null && list3.size() != 0) {
                    showPopupWindow(this, list3, 3);
                    adapter.setCheckItem(currentPosition3);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
                break;
            case R.id.linearLayout_event_grave:
                if (list4 != null && list4.size() != 0) {
                    showPopupWindow(this, list4, 4);
                    adapter.setCheckItem(currentPosition4);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
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

    private void showPopupWindow(Context context, List<String> listDate, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.listview_default_down, null);
        //此处可按需求为各控件设置属性
        ListView listView = view.findViewById(R.id.lv);
        adapter = new ListAndGirdDownAdapter(context, listDate);
        listView.setAdapter(adapter);
        listView.setId(i);
        listView.setOnItemClickListener(this);
        dialog = new Dialog(context, R.style.dialog);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出窗口大小
        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        //设置显示位置
        window.setGravity(Gravity.BOTTOM);
        //设置动画效果
        window.setWindowAnimations(R.style.AnimBottom);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case 1:
                currentPosition1 = position;
                textViewEventFrom.setText(list1.get(position));
                textViewEventFrom.setTextColor(getResources().getColor(R.color.white));
                eventBean.setOrigin(list1.get(position));
                dialog.dismiss();
                break;
            case 2:
                currentPosition2 = position;
                textViewEventType.setText(list2.get(position));
                textViewEventType.setTextColor(getResources().getColor(R.color.white));
                eventBean.setType(list2.get(position));
                dialog.dismiss();
                break;
            case 3:
                currentPosition3 = position;
                textViewEventLevel.setText(list3.get(position));
                textViewEventLevel.setTextColor(getResources().getColor(R.color.white));
                eventBean.setPriority(list3.get(position));
                dialog.dismiss();
                break;
            case 4:
                currentPosition4 = position;
                textViewEventGrave.setText(list4.get(position));
                textViewEventGrave.setTextColor(getResources().getColor(R.color.white));
                eventBean.setSeverity(list4.get(position));
                dialog.dismiss();
                break;
        }
    }
}
