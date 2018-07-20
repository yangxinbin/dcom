package com.mango.leo.dcom.event.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
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

import com.bumptech.glide.Glide;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.adapter.ListAndGirdDownAdapter;
import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.change.activity.MethodItemActivity;
import com.mango.leo.dcom.change.adapter.MethodAdapter;
import com.mango.leo.dcom.change.bean.MethodBeans;
import com.mango.leo.dcom.event.adapter.MeasureAdapter;
import com.mango.leo.dcom.event.bean.EventDetailBean;
import com.mango.leo.dcom.event.bean.ListEventBean;
import com.mango.leo.dcom.event.bean.MeasureBeans;
import com.mango.leo.dcom.event.bean.PeopleBean;
import com.mango.leo.dcom.event.bean.TeamBean;
import com.mango.leo.dcom.event.util.EventJsonUtils;
import com.mango.leo.dcom.faq.bean.ListFaqBean;
import com.mango.leo.dcom.faq.util.FaqJsonUtils;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.DateUtil;
import com.mango.leo.dcom.util.HttpUtils;
import com.mango.leo.dcom.util.RoundImageView;
import com.mango.leo.dcom.util.Urls;
import com.mango.leo.dcom.util.flowview.FlowTagLayout;
import com.mango.leo.dcom.util.flowview.TagAdapter;
import com.mango.leo.dcom.util.relate.ChangeChooseBean;
import com.mango.leo.dcom.util.relate.ConfigChooseBean;
import com.mango.leo.dcom.util.relate.EventChooseBean;
import com.mango.leo.dcom.util.relate.ProblemChooseBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EventDetailActivity extends BaseActivity implements AdapterView.OnItemClickListener, MethodAdapter.OnMethodClickListener, MeasureAdapter.OnMeasureClickListener {

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
    @Bind(R.id.load_layout)
    LinearLayout loadLayout;
    @Bind(R.id.cardView_stage4)
    CardView cardViewStage4;
    @Bind(R.id.editText_description)
    EditText editTextDescription;
    @Bind(R.id.t_task_zu)
    TextView tTaskZu;
    @Bind(R.id.zu)
    LinearLayout zu;
    @Bind(R.id.t_task_ren)
    TextView tTaskRen;
    @Bind(R.id.ren)
    LinearLayout ren;
    @Bind(R.id.cardView_stage1)
    CardView cardViewStage1;
    @Bind(R.id.t_task_description3)
    TextView tTaskDescription3;
    @Bind(R.id.tc)
    TextView tc;
    @Bind(R.id.editText_event_cause)
    EditText editTextEventCause;
    @Bind(R.id.jia_method)
    ImageView jiaMethod;
    @Bind(R.id.e_measure)
    LinearLayout eMeasure;
    @Bind(R.id.jia_measure)
    ImageView jiaMeasure;
    @Bind(R.id.e_method)
    LinearLayout eMethod;
    @Bind(R.id.r)
    TextView r;
    @Bind(R.id.editText_method_check)
    EditText editTextMethodCheck;
    @Bind(R.id.t_task_stage3)
    TextView tTaskStage3;
    @Bind(R.id.stage3)
    LinearLayout stage3;
    @Bind(R.id.cardView_stage3)
    CardView cardViewStage3;
    @Bind(R.id.b_assign)
    Button bAssign;
    @Bind(R.id.b_accept)
    Button bAccept;
    @Bind(R.id.b_dealwith)
    Button bDealwith;
    @Bind(R.id.all)
    LinearLayout all;
    @Bind(R.id.cardView1)
    CardView cardView1;
    @Bind(R.id.b_commit)
    Button bCommit;
    private SharedPreferences sharedPreferences;
    private TagAdapter tagAdapter;
    private int eventId;
    private List<String> list1, list2,list3, listTeamId, listPeopleId;
    private int currentPosition1 = -1, currentPosition2 = -1,currentPosition3 = -1;
    private ListAndGirdDownAdapter adapter;
    private Dialog dialog;
    private String string_zu;
    private String string_ren;
    private String string_zuId = null;
    private String string_peopleId = null;
    private LinearLayoutManager mLayoutManager;
    private MethodBeans methodBean;
    private MethodAdapter adapter1;
    private MeasureBeans measureBean;
    private MeasureAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        listTeamId = new ArrayList<>();
        listPeopleId = new ArrayList<>();
        ButterKnife.bind(this);
        Log.v("wwwwwwww", "!!!" + getIntent().getStringExtra("id"));
        loadDetail(getIntent().getStringExtra("id"));
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventMethodBeans(MethodBeans bean) {
        eMethod.removeAllViews();
/*        if (bean != null) {
            adapter1.removeData();
            methodBean = bean;
        }*/
        methodBean = bean;
        View item1 = LayoutInflater.from(this).inflate(R.layout.change_carditem, null);
        eMethod.addView(item1);
        RecyclerView recyclerView = item1.findViewById(R.id.recycle);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        Log.v("qqqqqqqqq", "==" + bean.getMethodItems());
        adapter1 = new MethodAdapter(this, bean.getMethodItems());
        recyclerView.setAdapter(adapter1);
        adapter1.setOnMethodClickListener(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventMeasureBeans(MeasureBeans bean) {
        eMeasure.removeAllViews();
/*        if (bean != null) {
            adapter1.removeData();
            methodBean = bean;
        }*/
        measureBean = bean;
        View item1 = LayoutInflater.from(this).inflate(R.layout.change_carditem, null);
        eMeasure.addView(item1);
        RecyclerView recyclerView = item1.findViewById(R.id.recycle);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        Log.v("qqqqqqqqq", "==" + bean.getMeasureItems());
        adapter2 = new MeasureAdapter(this, bean.getMeasureItems());
        recyclerView.setAdapter(adapter2);
        adapter2.setOnMeasureClickListener(this);
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

    @OnClick({R.id.zu, R.id.ren, R.id.jia_method, R.id.jia_measure, R.id.stage3, R.id.b_assign, R.id.b_accept, R.id.b_dealwith, R.id.b_commit})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.zu:
                loadTeam();
                break;
            case R.id.ren:
                if (list2 != null && list2.size() != 0) {
                    showPopupWindow(this, list2, 2);
                    adapter.setCheckItem(currentPosition2);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
                break;
            case R.id.jia_method:
                if (methodBean == null) {
                    intent = new Intent(this, MethodItemActivity.class);
                    startActivity(intent);
                    return;
                }
                //EventBus.getDefault().postSticky(methodBean);
                //EventBus.getDefault().removeStickyEvent(MethodBeans.class);
                intent = new Intent(this, MethodItemActivity.class);
                Log.v("qqqqqqqqq", "adapter1.getItemCount()==" + adapter1.getItemCount());
                intent.putExtra("position", adapter1.getItemCount());//第一个独立出来
                startActivity(intent);
                //finish();
                break;
            case R.id.jia_measure:
                if (measureBean == null) {
                    intent = new Intent(this, MeasureItemActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(this, MeasureItemActivity.class);
                Log.v("qqqqqqqqq", "adapter2.getItemCount()==" + adapter2.getItemCount());
                intent.putExtra("position", adapter2.getItemCount());//第一个独立出来
                startActivity(intent);
                break;
            case R.id.stage3:
                list3.clear();
                list3.add("已解决");
                list3.add("待解决");
                list3.add("等待批准");
                if (list3 != null && list3.size() != 0) {
                    showPopupWindow(this, list3, 3);
                    adapter.setCheckItem(currentPosition3);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
                break;
            case R.id.b_commit:
                commitEvent();
                break;
            case R.id.b_assign:
                if (!editTextDescription.getText().toString().startsWith("请") && string_zuId != null && string_peopleId != null) {
                    assignEvent();
                } else {
                    AppUtils.showToast(this, "请完善指派信息");
                }
                break;
            case R.id.b_accept:
                break;
            case R.id.b_dealwith:
                dealWith();
                break;
        }
    }

    private void dealWith() {
        final HashMap<String, String> mapParams = new HashMap<String, String>();
        mapParams.clear();
        mapParams.put("eventId", eventId + "");//待定
        mapParams.put("token", sharedPreferences.getString("token", ""));
        mapParams.put("cause", editTextEventCause.getText().toString());
        mapParams.put("solutions", buildArrayJson_Method(list1));
        mapParams.put("preMeasures", buildArrayJson_Measure(list2));
        mapParams.put("review", editTextMethodCheck.getText().toString());
        mapParams.put("status", tTaskStage3.getText().toString());

        HttpUtils.doPost(Urls.HOST + "/api/secure/event/solve", mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AppUtils.showToast(getBaseContext(), "指派失败");
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
                            AppUtils.showToast(getBaseContext(), "指派成功");
                        }
                    });
                } else {
                    Log.v("doPostAll", response.body().string() + "^^^^^onFailure^^^^^" + response.code());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AppUtils.showToast(getBaseContext(), "指派失败");
                        }
                    });
                }
            }
        });
    }
    public String buildArrayJson_Method(List<String> list1) {
        JSONArray json = new JSONArray();
        try {
            for (int i = 0; i < list1.size(); i++) {
                JSONObject jsonObj = new JSONObject();//一定要new对象
                //jsonObj.put("step", list1.get(i)+"");
                //jsonObj.put("detail", methodBeans.getMethodItems().get(i).getDetail().toString());
                json.put(i, jsonObj);
                continue;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //把每个数据当作一对象添加到数组里
        Log.v("uuuuuuuu","-m---"+json.toString());
        return json.toString();
    }
    public String buildArrayJson_Measure(List<String> list1) {
        JSONArray json = new JSONArray();
        try {
            for (int i = 0; i < list1.size(); i++) {
                JSONObject jsonObj = new JSONObject();//一定要new对象
                //jsonObj.put("step", list1.get(i)+"");
                //jsonObj.put("detail", methodBeans.getMethodItems().get(i).getDetail().toString());
                json.put(i, jsonObj);
                continue;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //把每个数据当作一对象添加到数组里
        Log.v("uuuuuuuu","-m---"+json.toString());
        return json.toString();
    }
    private void assignEvent() {
        final HashMap<String, String> mapParams = new HashMap<String, String>();
        mapParams.clear();
        mapParams.put("eventId", eventId + "");//待定
        mapParams.put("token", sharedPreferences.getString("token", ""));
        mapParams.put("teamId", string_zuId);
        mapParams.put("claimedBy", string_peopleId);
        mapParams.put("description", editTextDescription.getText().toString());
        HttpUtils.doPost(Urls.HOST + "/api/secure/event/assign", mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AppUtils.showToast(getBaseContext(), "指派失败");
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
                            AppUtils.showToast(getBaseContext(), "指派成功");
                        }
                    });
                } else {
                    Log.v("doPostAll", response.body().string() + "^^^^^onFailure^^^^^" + response.code());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AppUtils.showToast(getBaseContext(), "指派失败");
                        }
                    });
                }
            }
        });
    }

    private void loadTeam() {
        list1.clear();
        listTeamId.clear();
        HttpUtils.doGet(Urls.HOST + "/api/common/list/teams?token=" + sharedPreferences.getString("token", ""), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(3);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    List<TeamBean> teamBeans = EventJsonUtils.readTeamBean(response.body().string());//data是json字段获得data的值即对象数组
                    Log.v("tttttttttt", "====" + teamBeans.size());
                    Message message = mHandler.obtainMessage();
                    message.obj = teamBeans;
                    message.what = 2;
                    message.sendToTarget();
                } else {
                    Log.v("doPostAll", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                    mHandler.sendEmptyMessage(3);
                }
            }
        });
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

    private void commitEvent() {
        final HashMap<String, String> mapParams = new HashMap<String, String>();
        mapParams.clear();
        mapParams.put("eventId", eventId + "");//待定
        HttpUtils.doPost(Urls.HOST + "/api/secure/event/apply", mapParams, new Callback() {
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case 1:
                currentPosition1 = position;
                tTaskZu.setText(list1.get(position));
                tTaskZu.setTextColor(getResources().getColor(R.color.white));
                string_zu = list1.get(position);
                string_zuId = listTeamId.get(position);
                loadPeople(string_zuId);
                dialog.dismiss();
                break;
            case 2:
                currentPosition2 = position;
                tTaskRen.setText(list2.get(position));
                tTaskRen.setTextColor(getResources().getColor(R.color.white));
                string_ren = list2.get(position);
                string_peopleId = listPeopleId.get(position);
                dialog.dismiss();
                break;
            case 3:
                currentPosition3 = position;
                tTaskStage3.setText(list3.get(position));
                tTaskStage3.setTextColor(getResources().getColor(R.color.white));
                dialog.dismiss();
                break;
        }
    }

    private void loadPeople(String string_zuId) {
        list2.clear();
        listPeopleId.clear();
        HttpUtils.doGet(Urls.HOST + "/api/common/list/members?team=" + string_zuId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(3);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    List<PeopleBean> peopleBeans = EventJsonUtils.readPeopleBean(response.body().string());//data是json字段获得data的值即对象数组
                    Log.v("tttttttttt", "====" + peopleBeans.size());
                    Message message = mHandler.obtainMessage();
                    message.obj = peopleBeans;
                    message.what = 4;
                    message.sendToTarget();
                } else {
                    Log.v("doPostAll", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                    mHandler.sendEmptyMessage(3);
                }
            }
        });
    }

    @Override
    public void onMethodClickEdit(View view, int position) {
        //EventBus.getDefault().postSticky(methodBean);
        Intent intent = new Intent(this, MethodItemActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
        //finish();
    }

    @Override
    public void onMethodClickDelete(View view, int position) {
        methodBean.getMethodItems().remove(position);
        EventBus.getDefault().postSticky(methodBean);
    }

    @Override
    public void onMeasureClickEdit(View view, int position) {
        Intent intent = new Intent(this, MeasureItemActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void onMeasureClickDelete(View view, int position) {
        measureBean.getMeasureItems().remove(position);
        EventBus.getDefault().postSticky(measureBean);
    }

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
                        loadLayout.setVisibility(View.GONE);
                        cardView1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        AppUtils.showToast(getBaseContext(), "请求失败");
                        break;
                    case 2:
                        //AppUtils.showToast(getBaseContext(), "请求失败");
                        List<TeamBean> teamBeans = (List<TeamBean>) msg.obj;
                        for (int i = 0; i < teamBeans.size(); i++) {
                            list1.add(teamBeans.get(i).getName());
                            listTeamId.add(teamBeans.get(i).getId() + "");
                        }
                        if (list1 != null && list1.size() != 0) {
                            showPopupWindow(activity, list1, 1);
                            adapter.setCheckItem(currentPosition1);
                        } else {
                            AppUtils.showToast(activity, "配置项为空");
                        }
                        break;
                    case 3:
                        AppUtils.showToast(getBaseContext(), "获取失败");
                        break;
                    case 4:
                        List<PeopleBean> peopleBeans = (List<PeopleBean>) msg.obj;
                        for (int i = 0; i < peopleBeans.size(); i++) {
                            list2.add(peopleBeans.get(i).getRealName());
                            listPeopleId.add(peopleBeans.get(i).getId() + "");
                        }
/*                        if (list2 != null && list2.size() != 0) {
                            showPopupWindow(activity, list2, 2);
                            adapter.setCheckItem(currentPosition2);
                        } else {
                            AppUtils.showToast(activity, "配置项为空");
                        }*/
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
        if (eventDetailBean.getStage() == 0) {
            bCommit.setVisibility(View.VISIBLE);
        } else if (eventDetailBean.getStage() == 1) {
            cardViewStage1.setVisibility(View.VISIBLE);
            bAssign.setVisibility(View.VISIBLE);
        } else if (eventDetailBean.getStage() == 2) {
            bAccept.setVisibility(View.VISIBLE);
        } else if (eventDetailBean.getStage() == 3) {
            cardViewStage3.setVisibility(View.VISIBLE);
            bDealwith.setVisibility(View.VISIBLE);
        } else if (eventDetailBean.getStage() == 4) {

        } else if (eventDetailBean.getStage() == 5) {

        }
        eventId = eventDetailBean.getId();
        tTag.setText(eventDetailBean.getTag() + "");
        tT.setText(eventDetailBean.getTitle() + "");
        tP.setText(eventDetailBean.getCreatedBy().getRealName() + "");
        tTime.setText(DateUtil.getDateToString(eventDetailBean.getOccurredOn(), "yyyy-MM-dd HH:mm:ss") + "");
        if (eventDetailBean.getClaimedBy() != null)
            tC.setText(eventDetailBean.getClaimedBy().getRealName() + "");
        tCTime.setText(DateUtil.getDateToString(eventDetailBean.getCreatedOn(), "yyyy-MM-dd HH:mm:ss") + "");
        tFrom.setText(eventDetailBean.getOrigin() + "");
        tType.setText(eventDetailBean.getType() + "");
        tLevel.setText("级别-" + eventDetailBean.getLevel() + "");
        tSe.setText(eventDetailBean.getSeverity() + "");
        tRange.setText(eventDetailBean.getScope() + "");
        tContent.setText(eventDetailBean.getDescription() + "");
        tagAdapter = new TagAdapter(this);
        flowLayout.setAdapter(tagAdapter);
        tagAdapter.onlyAddAll(eventDetailBean.getAssetConfigSNs());
        if (eventDetailBean.getAttachments() != null && eventDetailBean.getAttachments().get(0) != null) {
            imageViewP.setVisibility(View.VISIBLE);
            Glide.with(this).load("http://dcom.hopesen.com.cn" + eventDetailBean.getAttachments().get(0).getUrl()).into(imageViewP);
        } else {
            imageViewP.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.imageView_back)
    public void onViewClicked() {
        JumpToList();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.v("yyyyy", "d::::");
            JumpToList();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void JumpToList() {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
