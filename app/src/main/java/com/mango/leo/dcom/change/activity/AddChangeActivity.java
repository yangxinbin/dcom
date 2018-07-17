package com.mango.leo.dcom.change.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.adapter.ListAndGirdDownAdapter;
import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.change.bean.ChangeBean;
import com.mango.leo.dcom.change.bean.ListChangeBean;
import com.mango.leo.dcom.change.presenter.ChangePresenter;
import com.mango.leo.dcom.change.presenter.ChangePresenterImpl;
import com.mango.leo.dcom.change.view.ChangeView;
import com.mango.leo.dcom.event.bean.ConfigBean;
import com.mango.leo.dcom.event.util.EventJsonUtils;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.DateUtil;
import com.mango.leo.dcom.util.HttpUtils;
import com.mango.leo.dcom.util.PhotoUtils;
import com.mango.leo.dcom.util.RoundImageView;
import com.mango.leo.dcom.util.Urls;
import com.mango.leo.dcom.util.flowview.FlowTagLayout;
import com.mango.leo.dcom.util.flowview.TagAdapter;
import com.mango.leo.dcom.util.relate.ChangeChooseBean;
import com.mango.leo.dcom.util.relate.ConfigActivity;
import com.mango.leo.dcom.util.relate.ConfigChooseBean;
import com.mango.leo.dcom.util.relate.EventChooseBean;
import com.mango.leo.dcom.util.relate.ProblemChooseBean;
import com.mango.leo.dcom.util.widget.CustomDatePicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddChangeActivity extends BaseActivity implements ChangeView, AdapterView.OnItemClickListener {

    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.editText_change_flag)
    EditText editTextChangeFlag;
    @Bind(R.id.editText_change_title)
    EditText editTextChangeTitle;
    @Bind(R.id.textView_change_time)
    TextView textViewChangeTime;
    @Bind(R.id.linearLayout_change_time)
    LinearLayout linearLayoutChangeTime;
    @Bind(R.id.textView_change_overtime)
    TextView textViewChangeOvertime;
    @Bind(R.id.linearLayout_change_overtime)
    LinearLayout linearLayoutChangeOvertime;
    @Bind(R.id.editText_change_oa)
    EditText editTextChangeOa;
    @Bind(R.id.textView_change_type)
    TextView textViewChangeType;
    @Bind(R.id.linearLayout_change_type)
    LinearLayout linearLayoutChangeType;
    @Bind(R.id.textView_change_effect)
    TextView textViewChangeEffect;
    @Bind(R.id.linearLayout_change_effect)
    LinearLayout linearLayoutChangeEffect;
    @Bind(R.id.textView_change_degree)
    TextView textViewChangeDegree;
    @Bind(R.id.linearLayout_change_degree)
    LinearLayout linearLayoutChangeDegree;
    @Bind(R.id.textView_change_risk)
    TextView textViewChangeRisk;
    @Bind(R.id.linearLayout_change_risk)
    LinearLayout linearLayoutChangeRisk;
    @Bind(R.id.textView_event_faqlist)
    TextView textViewEventFaqlist;
    @Bind(R.id.linearLayout_event_faqlist)
    LinearLayout linearLayoutEventFaqlist;
    @Bind(R.id.flow_event_layout)
    FlowTagLayout flowEventLayout;
    @Bind(R.id.textView_change_faqlist)
    TextView textViewChangeFaqlist;
    @Bind(R.id.linearLayout_change_faqlist)
    LinearLayout linearLayoutChangeFaqlist;
    @Bind(R.id.flow_problem_layout)
    FlowTagLayout flowProblemLayout;
    @Bind(R.id.textView_change_changelist)
    TextView textViewChangeChangelist;
    @Bind(R.id.linearLayout_change_changelist)
    LinearLayout linearLayoutChangeChangelist;
    @Bind(R.id.flow_change_layout)
    FlowTagLayout flowChangeLayout;
    @Bind(R.id.textView_change_item)
    TextView textViewChangeItem;
    @Bind(R.id.linearLayout_change_item)
    LinearLayout linearLayoutChangeItem;
    @Bind(R.id.flow_config_layout)
    FlowTagLayout flowConfigLayout;
    @Bind(R.id.editText_description)
    EditText editTextDescription;
    @Bind(R.id.editText_content)
    EditText editTextContent;
    @Bind(R.id.b_save)
    Button bSave;
    @Bind(R.id.b_save_commit)
    Button bSaveCommit;
    @Bind(R.id.imageView_pic_choose)
    ImageView imageViewPicChoose;
    @Bind(R.id.imageView_pic)
    RoundImageView imageViewPic;
    @Bind(R.id.imageViewP)
    ImageView imageViewP;
    @Bind(R.id.p)
    RelativeLayout p;
    @Bind(R.id.e_method_way)
    EditText eMethodWay;
    @Bind(R.id.e_revert)
    EditText eRevert;
    @Bind(R.id.imageView_pic_choose1)
    ImageView imageViewPicChoose1;
    @Bind(R.id.imageView_pic1)
    RoundImageView imageViewPic1;
    @Bind(R.id.imageViewP1)
    ImageView imageViewP1;
    @Bind(R.id.p1)
    RelativeLayout p1;
    private ChangeBean changeBean;
    private SharedPreferences sharedPreferences;
    private ChangePresenter changePresenter;
    private CustomDatePicker customDatePicker_1, customDatePicker_2;
    private List<String> list1, list2, list3, list4;
    private int currentPosition1 = -1, currentPosition2 = -1, currentPosition3 = -1, currentPosition4 = -1;
    private ListAndGirdDownAdapter adapter;
    private Dialog dialog;
    private boolean flag = true;
    private TagAdapter tagAdapter;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private static final int OUTPUT_X = 480;
    private static final int OUTPUT_Y = 380;
    private String TAG = "AddChangeActivity";
    private int pic = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_change);
        changeBean = new ChangeBean();
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        ButterKnife.bind(this);
        changePresenter = new ChangePresenterImpl(this);
        initView();
        initDateFromWeb();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        StringBuffer stringBuffer = new StringBuffer();
        String s = DateUtil.getDateToString(System.currentTimeMillis(), "yyyyMMddHHmm").substring(2, 12);
        stringBuffer.append(s);
        for (int i = 0; i < 3; i++) {
            stringBuffer.append((char) (Math.random() * 26 + 'A'));
        }
        editTextChangeFlag.setText(stringBuffer.toString());
        changeBean.setTag(stringBuffer.toString());
        changeBean.setTitle(editTextChangeTitle.getText().toString());
        changeBean.setPlanningTime(String.valueOf(DateUtil.getStringToDate(textViewChangeTime.getText().toString(), "yyyy-MM-dd HH:mm")));
        changeBean.setDeadline(String.valueOf(DateUtil.getStringToDate(textViewChangeOvertime.getText().toString(), "yyyy-MM-dd HH:mm")));
        changeBean.setOaNumber(editTextChangeOa.getText().toString());
        changeBean.setCause(editTextDescription.getText().toString());
        changeBean.setContent(editTextContent.getText().toString());
        changeBean.setSolutions(eMethodWay.getText().toString());
        changeBean.setPlanBSolutions(eRevert.getText().toString());
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
                            final List<ConfigBean> listC = EventJsonUtils.readJsonConfigBean(response.body().string(), "content");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.v("ppppppppppp", "--listC--" + listC.size());
                                    for (int i = 0; i < listC.size(); i++) {
                                        if (listC.get(i).getContent() != null && listC.get(i).getContent().get(i).getType() != null) {
                                            if (listC.get(i).getContent().get(i).getType().equals("change_type")) {
                                                list1 = toList(listC.get(i).getContent().get(i).getValue());
                                                continue;
                                            } else if (listC.get(i).getContent().get(i).getType().toString().equals("change_impact_level")) {
                                                list2 = toList(listC.get(i).getContent().get(i).getValue());
                                                continue;
                                            } else if (listC.get(i).getContent().get(i).getType().equals("change_risk_level")) {
                                                list3 = toList(listC.get(i).getContent().get(i).getValue());
                                                continue;
                                            } else if (listC.get(i).getContent().get(i).getType().equals("release_test_status")) {
                                                list4 = toList(listC.get(i).getContent().get(i).getValue());
                                                continue;
                                            }
                                        }
                                    }
                                }
                            });
                        } catch (Exception e) {
                            //Log.v("ppppppppppp", "__eee_--" + response.body().string());
                        }
                    }
                });
            }
        }).start();
    }

    private List<String> toList(String value) {
        String[] arg = value.replace("[", "").replace("]", "").replaceAll("\"", "").split(",");
        List<String> list = Arrays.asList(arg);
/*        GsonJsonParser gson = new GsonJsonParser();
        List<String> list = (List<String>) gson.parse(value);*/
        return list;
    }

    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        //textViewEventTime.setText(now);
        customDatePicker_1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                textViewChangeTime.setText(time);
                textViewChangeTime.setTextColor(getResources().getColor(R.color.white));
            }
        }, "2018-01-01 00:00", "2040-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker_1.showSpecificTime(true); // 显示时和分
        customDatePicker_1.setIsLoop(true); // 允许循环滚动
        customDatePicker_2 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                textViewChangeOvertime.setText(time);
                textViewChangeOvertime.setTextColor(getResources().getColor(R.color.white));
            }
        }, "2018-01-01 00:00", "2040-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker_2.showSpecificTime(true); // 显示时和分
        customDatePicker_2.setIsLoop(true); // 允许循环滚动
    }

    @OnClick({R.id.imageView_back, R.id.linearLayout_change_time, R.id.linearLayout_change_overtime, R.id.linearLayout_change_type, R.id.linearLayout_change_effect, R.id.linearLayout_change_degree, R.id.linearLayout_change_risk, R.id.linearLayout_event_faqlist, R.id.linearLayout_change_faqlist, R.id.linearLayout_change_changelist, R.id.linearLayout_change_item, R.id.imageView_pic_choose, R.id.imageView_pic, R.id.imageViewP, R.id.imageView_pic_choose1, R.id.imageView_pic1, R.id.imageViewP1})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                intent = new Intent(this, ChangeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.linearLayout_change_time:
                initDatePicker();
                customDatePicker_1.show(DateUtil.getDateToString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"));
                break;
            case R.id.linearLayout_change_overtime:
                initDatePicker();
                customDatePicker_2.show(DateUtil.getDateToString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"));
                break;
            case R.id.linearLayout_change_type:
                if (list1 != null && list1.size() != 0) {
                    showPopupWindow(this, list1, 1);
                    adapter.setCheckItem(currentPosition1);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
                break;
            case R.id.linearLayout_change_effect:
                if (list2 != null && list2.size() != 0) {
                    showPopupWindow(this, list2, 2);
                    adapter.setCheckItem(currentPosition2);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
                break;
            case R.id.linearLayout_change_degree:
                if (list3 != null && list3.size() != 0) {
                    showPopupWindow(this, list3, 3);
                    adapter.setCheckItem(currentPosition3);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
                break;
            case R.id.linearLayout_change_risk:
                if (list4 != null && list4.size() != 0) {
                    showPopupWindow(this, list4, 4);
                    adapter.setCheckItem(currentPosition4);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
                break;
            case R.id.linearLayout_event_faqlist:
                flag = false;
                intent = new Intent(this, ConfigActivity.class);
                intent.putExtra("config", "关联事件单");
                intent.putExtra("what", "event");
/*                if (bean1 != null) {
                    Log.v("ccccc", "ccc");
                    EventBus.getDefault().postSticky(bean1);
                }*/
                startActivity(intent);
                break;
            case R.id.linearLayout_change_faqlist:
                flag = false;
                intent = new Intent(this, ConfigActivity.class);
                intent.putExtra("config", "关联问题单");
                intent.putExtra("what", "problem");
/*                if (bean1 != null) {
                    Log.v("ccccc", "ccc");
                    EventBus.getDefault().postSticky(bean1);
                }*/
                startActivity(intent);
                break;
            case R.id.linearLayout_change_changelist:
                flag = false;
                intent = new Intent(this, ConfigActivity.class);
                intent.putExtra("config", "关联变更单");
                intent.putExtra("what", "change");
/*                if (bean1 != null) {
                    Log.v("ccccc", "ccc");
                    EventBus.getDefault().postSticky(bean1);
                }*/
                startActivity(intent);
                break;
            case R.id.linearLayout_change_item:
                flag = false;
                intent = new Intent(this, ConfigActivity.class);
                intent.putExtra("config", "关联配置项");
                intent.putExtra("what", "asset");
/*                if (bean1 != null) {
                    Log.v("ccccc", "ccc");
                    EventBus.getDefault().postSticky(bean1);
                }*/
                startActivity(intent);
                break;
            case R.id.imageView_pic_choose:
                pic = 1;
                showTypeDialog();
                break;
            case R.id.imageView_pic:
                pic = 1;
                showTypeDialog();
                break;
            case R.id.imageViewP:
                cropImageUri = null;
                p.setVisibility(View.GONE);
                imageViewPicChoose.setVisibility(View.VISIBLE);
                break;
            case R.id.imageView_pic_choose1:
                pic = 2;
                showTypeDialog();
                break;
            case R.id.imageView_pic1:
                pic = 2;
                showTypeDialog();
                break;
            case R.id.imageViewP1:
                cropImageUri = null;
                p1.setVisibility(View.GONE);
                imageViewPicChoose1.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBus1(EventChooseBean bean) {
        tagAdapter = new TagAdapter(this);
        flowEventLayout.setAdapter(tagAdapter);
        if (bean == null || bean.getChooses().size() == 0) {
            textViewEventFaqlist.setVisibility(View.VISIBLE);
            tagAdapter.onlyAddAll(bean.getChooses());
            return;
        }
        textViewEventFaqlist.setVisibility(View.GONE);
        tagAdapter.onlyAddAll(bean.getChooses());
        changeBean.setRelatedEventTags(removeDuplicate(bean.getChooses()));
        if (flag) {
            EventBus.getDefault().removeStickyEvent(EventChooseBean.class);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBus2(ProblemChooseBean bean) {
        tagAdapter = new TagAdapter(this);
        flowChangeLayout.setAdapter(tagAdapter);
        if (bean == null || bean.getChooses().size() == 0) {
            textViewChangeFaqlist.setVisibility(View.VISIBLE);
            tagAdapter.onlyAddAll(bean.getChooses());
            return;
        }
        textViewChangeFaqlist.setVisibility(View.GONE);
        tagAdapter.onlyAddAll(bean.getChooses());
        changeBean.setRelatedChangeTags(removeDuplicate(bean.getChooses()));
        if (flag) {
            EventBus.getDefault().removeStickyEvent(ChangeChooseBean.class);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBus3(ChangeChooseBean bean) {
        tagAdapter = new TagAdapter(this);
        flowConfigLayout.setAdapter(tagAdapter);
        if (bean == null || bean.getChooses().size() == 0) {
            textViewChangeChangelist.setVisibility(View.VISIBLE);
            tagAdapter.onlyAddAll(bean.getChooses());
            return;
        }
        textViewChangeChangelist.setVisibility(View.GONE);
        tagAdapter.onlyAddAll(bean.getChooses());
        changeBean.setRelatedConfigSNs(removeDuplicate(bean.getChooses()));
        if (flag) {
            EventBus.getDefault().removeStickyEvent(ConfigChooseBean.class);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBus4(ConfigChooseBean bean) {
        tagAdapter = new TagAdapter(this);
        flowConfigLayout.setAdapter(tagAdapter);
        if (bean == null || bean.getChooses().size() == 0) {
            textViewChangeItem.setVisibility(View.VISIBLE);
            tagAdapter.onlyAddAll(bean.getChooses());
            return;
        }
        textViewChangeItem.setVisibility(View.GONE);
        tagAdapter.onlyAddAll(bean.getChooses());
        changeBean.setRelatedConfigSNs(removeDuplicate(bean.getChooses()));
        if (flag) {
            EventBus.getDefault().removeStickyEvent(ConfigChooseBean.class);
        }
    }

    public List<String> removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, ChangeActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().removeStickyEvent(EventChooseBean.class);
        EventBus.getDefault().removeStickyEvent(ChangeChooseBean.class);
        EventBus.getDefault().removeStickyEvent(ConfigChooseBean.class);
        EventBus.getDefault().removeStickyEvent(ProblemChooseBean.class);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void addChangeSuccess(List<ListChangeBean> changeBeans) {

    }

    @Override
    public void addChangeMes(String s) {

    }

    @Override
    public void addChangeFail(String e) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case 1:
                currentPosition1 = position;
                textViewChangeType.setText(list1.get(position));
                textViewChangeType.setTextColor(getResources().getColor(R.color.white));
                changeBean.setType(list1.get(position));
                dialog.dismiss();
                break;
            case 2:
                currentPosition2 = position;
                textViewChangeEffect.setText(list2.get(position));
                textViewChangeEffect.setTextColor(getResources().getColor(R.color.white));
                changeBean.setImpactScope(list2.get(position));
                dialog.dismiss();
                break;
            case 3:
                currentPosition3 = position;
                textViewChangeDegree.setText(list3.get(position));
                textViewChangeDegree.setTextColor(getResources().getColor(R.color.white));
                changeBean.setImpactLevel(position + "");
                dialog.dismiss();
                break;
            case 4:
                currentPosition4 = position;
                textViewChangeRisk.setText(list4.get(position));
                textViewChangeRisk.setTextColor(getResources().getColor(R.color.white));
                changeBean.setRiskLevel(list4.get(position));
                dialog.dismiss();
                break;
        }
    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                autoObtainStoragePermission();
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                autoObtainCameraPermission();
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    /**
     * 动态申请sdcard读写权限
     */
    private void autoObtainStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
            }
        } else {
            PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
        }
    }

    /**
     * 申请访问相机权限
     */
    private void autoObtainCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    AppUtils.showToast(this, "您已经拒绝过一次");
                }
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
            } else {//有权限直接调用系统相机拍照
                if (hasSdcard()) {
                    imageUri = Uri.fromFile(fileUri);
                    //通过FileProvider创建一个content类型的Uri
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        imageUri = FileProvider.getUriForFile(this, "com.mango.leo.dcom", fileUri);
                    }
                    PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
                } else {
                    AppUtils.showToast(this, "设备没有SD卡！");
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //调用系统相机申请拍照权限回调
            case CAMERA_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            //通过FileProvider创建一个content类型的Uri
                            imageUri = FileProvider.getUriForFile(this, "com.mango.leo.dcom", fileUri);
                        }
                        PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        AppUtils.showToast(this, "设备没有SD卡！");
                    }
                } else {
                    AppUtils.showToast(this, "请允许打开相机！！");
                }
                break;
            }
            //调用系统相册申请Sdcard权限回调
            case STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
                } else {
                    AppUtils.showToast(this, "请允许打操作SDCard！！");
                }
                break;
            default:
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: requestCode: " + requestCode + "  resultCode:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            Log.e(TAG, "onActivityResult: resultCode!=RESULT_OK");
            return;
        }
        switch (requestCode) {
            //相机返回
            case CODE_CAMERA_REQUEST:
                cropImageUri = Uri.fromFile(fileCropUri);
                PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 4, 3, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                //upLoadMap(cropImageUri);
                break;
            //相册返回
            case CODE_GALLERY_REQUEST:
                if (hasSdcard()) {
                    //upLoadMap(cropImageUri);
                    cropImageUri = Uri.fromFile(fileCropUri);
                    Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        newUri = FileProvider.getUriForFile(this, "com.mango.leo.dcom", new File(newUri.getPath()));
                    }
                    PhotoUtils.cropImageUri(this, newUri, cropImageUri, 4, 3, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                } else {
                    AppUtils.showToast(this, "设备没有SD卡！");
                }
                break;
            //裁剪返回
            case CODE_RESULT_REQUEST:
                Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                //upLoadLog(cropImageUri);
                //这里上传文件
                if (pic == 1) {
                    p.setVisibility(View.VISIBLE);
                    imageViewPicChoose.setVisibility(View.GONE);
                    if (bitmap != null) {
                        showImages(imageViewPic, bitmap);
                    }
                    changeBean.setFile(getRealFile(this, cropImageUri));//设置图片
                    Log.v("ppppppppppppppp","===1="+cropImageUri);
                } else if (pic == 2) {
                    p1.setVisibility(View.VISIBLE);
                    imageViewPicChoose1.setVisibility(View.GONE);
                    if (bitmap != null) {
                        showImages(imageViewPic1, bitmap);
                    }
                    changeBean.setSolutionAttachment(getRealFile(this, cropImageUri));//设置图片
                    Log.v("ppppppppppppppp","===2="+cropImageUri);
                }

                break;
            default:
        }
    }

    private void showImages(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static File getRealFile(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        File f = new File(data);
        return f;
    }
}
