package com.mango.leo.dcom.faq.activity;

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
import android.support.v7.app.AppCompatActivity;
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
import com.mango.leo.dcom.event.bean.ConfigBean;
import com.mango.leo.dcom.event.util.EventJsonUtils;
import com.mango.leo.dcom.faq.bean.FaqBean;
import com.mango.leo.dcom.faq.bean.ListFaqBean;
import com.mango.leo.dcom.faq.presenter.FaqPresenter;
import com.mango.leo.dcom.faq.presenter.FaqPresenterImpl;
import com.mango.leo.dcom.faq.view.FaqView;
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

public class AddFaqActivity extends BaseActivity implements FaqView, AdapterView.OnItemClickListener {
    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.editText_faq_flag)
    EditText editTextFaqFlag;
    @Bind(R.id.editText_faq_title)
    EditText editTextFaqTitle;
    @Bind(R.id.textView_faq_time)
    TextView textViewFaqTime;
    @Bind(R.id.linearLayout_faq_time)
    LinearLayout linearLayoutFaqTime;
    @Bind(R.id.textView_faq_overtime)
    TextView textViewFaqOvertime;
    @Bind(R.id.linearLayout_faq_overtime)
    LinearLayout linearLayoutFaqOvertime;
    @Bind(R.id.textView_faq_from)
    TextView textViewFaqFrom;
    @Bind(R.id.linearLayout_faq_from)
    LinearLayout linearLayoutFaqFrom;
    @Bind(R.id.textView_faq_type)
    TextView textViewFaqType;
    @Bind(R.id.linearLayout_faq_type)
    LinearLayout linearLayoutFaqType;
    @Bind(R.id.textView_faq_level)
    TextView textViewFaqLevel;
    @Bind(R.id.linearLayout_faq_level)
    LinearLayout linearLayoutFaqLevel;
    @Bind(R.id.textView_faq_system)
    TextView textViewFaqSystem;
    @Bind(R.id.linearLayout_faq_system)
    LinearLayout linearLayoutFaqSystem;
    @Bind(R.id.l_event)
    LinearLayout lEvent;
    @Bind(R.id.l_change)
    LinearLayout lChange;
    @Bind(R.id.l_config)
    LinearLayout lConfig;
    @Bind(R.id.editText_description)
    EditText editTextDescription;
    @Bind(R.id.b_save)
    Button bSave;
    @Bind(R.id.b_save_commit)
    Button bSaveCommit;
    @Bind(R.id.flow_event_layout)
    FlowTagLayout flowEventLayout;
    @Bind(R.id.flow_change_layout)
    FlowTagLayout flowChangeLayout;
    @Bind(R.id.flow_config_layout)
    FlowTagLayout flowConfigLayout;
    @Bind(R.id.imageView_pic_choose)
    ImageView imageViewPicChoose;
    @Bind(R.id.imageView_pic)
    RoundImageView imageViewPic;
    @Bind(R.id.imageViewP)
    ImageView imageViewP;
    @Bind(R.id.p)
    RelativeLayout p;
    @Bind(R.id.textView_eventlist)
    TextView textViewEventlist;
    @Bind(R.id.textView_changelist)
    TextView textViewChangelist;
    @Bind(R.id.textView_configlist)
    TextView textViewConfiglist;
    private FaqPresenter faqPresenter;
    private boolean flag;
    private TagAdapter tagAdapter;
    private FaqBean faqBean;
    private ConfigChooseBean bean1;
    private EventChooseBean bean_event;
    private ChangeChooseBean bean_change;
    private ConfigChooseBean bean_config;
    private SharedPreferences sharedPreferences;
    private List<String> list1, list2, list3, list4;
    private int currentPosition1 = -1, currentPosition2 = -1, currentPosition3 = -1, currentPosition4 = -1;
    private CustomDatePicker customDatePicker_1, customDatePicker_2;
    private ListAndGirdDownAdapter adapter;
    private Dialog dialog;
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
    private String TAG = "AddFaqActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faq);
        faqBean = new FaqBean();
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        ButterKnife.bind(this);
        faqPresenter = new FaqPresenterImpl(this);
        initView();
        initDateFromWeb();
        EventBus.getDefault().register(this);
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
                                            if (listC.get(i).getContent().get(i).getType().equals("pro_origin")) {
                                                list1 = toList(listC.get(i).getContent().get(i).getValue());
                                                continue;
                                            } else if (listC.get(i).getContent().get(i).getType().toString().equals("pro_class")) {
                                                list2 = toList(listC.get(i).getContent().get(i).getValue());
                                                continue;
                                            } else if (listC.get(i).getContent().get(i).getType().equals("pro_priority")) {
                                                list3 = toList(listC.get(i).getContent().get(i).getValue());
                                                continue;
                                            } else if (listC.get(i).getContent().get(i).getType().equals("pro_system")) {
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

    private void initView() {
        StringBuffer stringBuffer = new StringBuffer();
        String s = DateUtil.getDateToString(System.currentTimeMillis(), "yyyyMMddHHmm").substring(2, 12);
        stringBuffer.append(s);
        for (int i = 0; i < 3; i++) {
            stringBuffer.append((char) (Math.random() * 26 + 'A'));
        }
        editTextFaqFlag.setText(stringBuffer.toString());
        faqBean.setTag(stringBuffer.toString());
        faqBean.setTitle(editTextFaqTitle.getText().toString());
        faqBean.setOccurredOn(String.valueOf(DateUtil.getStringToDate(textViewFaqTime.getText().toString(), "yyyy-MM-dd HH:mm")));
        faqBean.setDeadline(String.valueOf(DateUtil.getStringToDate(textViewFaqOvertime.getText().toString(), "yyyy-MM-dd HH:mm")));
        faqBean.setDescription(editTextDescription.getText().toString());
    }

    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        //textViewEventTime.setText(now);
        customDatePicker_1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                textViewFaqTime.setText(time);
                textViewFaqTime.setTextColor(getResources().getColor(R.color.white));
            }
        }, "2018-01-01 00:00", "2040-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker_1.showSpecificTime(true); // 显示时和分
        customDatePicker_1.setIsLoop(true); // 允许循环滚动
        customDatePicker_2 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                textViewFaqOvertime.setText(time);
                textViewFaqOvertime.setTextColor(getResources().getColor(R.color.white));
            }
        }, "2018-01-01 00:00", "2040-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker_2.showSpecificTime(true); // 显示时和分
        customDatePicker_2.setIsLoop(true); // 允许循环滚动
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBus1(EventChooseBean bean) {
        tagAdapter = new TagAdapter(this);
        flowEventLayout.setAdapter(tagAdapter);
        if (bean == null || bean.getChooses().size() == 0) {
            textViewEventlist.setVisibility(View.VISIBLE);
            tagAdapter.onlyAddAll(bean.getChooses());
            return;
        }
        textViewEventlist.setVisibility(View.GONE);
        bean_event = bean;
        tagAdapter.onlyAddAll(bean.getChooses());
        faqBean.setRelatedEventTags(removeDuplicate(bean.getChooses()));
        if (flag) {
            EventBus.getDefault().removeStickyEvent(EventChooseBean.class);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBus2(ChangeChooseBean bean) {
        tagAdapter = new TagAdapter(this);
        flowChangeLayout.setAdapter(tagAdapter);
        if (bean == null || bean.getChooses().size() == 0){
            textViewChangelist.setVisibility(View.VISIBLE);
            tagAdapter.onlyAddAll(bean.getChooses());
            return;
        }
        textViewChangelist.setVisibility(View.GONE);
        bean_change = bean;
        tagAdapter.onlyAddAll(bean.getChooses());
        faqBean.setRelatedChangeTags(removeDuplicate(bean.getChooses()));
        if (flag) {
            EventBus.getDefault().removeStickyEvent(ChangeChooseBean.class);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBus3(ConfigChooseBean bean) {
        tagAdapter = new TagAdapter(this);
        flowConfigLayout.setAdapter(tagAdapter);
        if (bean == null || bean.getChooses().size() == 0){
            textViewConfiglist.setVisibility(View.VISIBLE);
            tagAdapter.onlyAddAll(bean.getChooses());
            return;
        }
        textViewConfiglist.setVisibility(View.GONE);
        bean_config = bean;
        tagAdapter.onlyAddAll(bean.getChooses());
        faqBean.setRelatedConfigSNs(removeDuplicate(bean.getChooses()));
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


    @Override
    public void addFaqSuccess(List<ListFaqBean> faqBeans) {

    }

    @Override
    public void addFaqMes(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getBaseContext(), s);
                if (s.equals("SUCCESS")) {
                    JumpTo();
                }
            }
        });
    }

    private void JumpTo() {
        Intent intent = new Intent(this, FaqActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void addFaqFail(final String e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getBaseContext(), e);
            }
        });
    }

    @OnClick({R.id.imageView_back, R.id.linearLayout_faq_time, R.id.linearLayout_faq_overtime, R.id.linearLayout_faq_from, R.id.linearLayout_faq_type, R.id.linearLayout_faq_level, R.id.linearLayout_faq_system, R.id.l_event, R.id.l_change, R.id.l_config, R.id.b_save, R.id.b_save_commit, R.id.imageView_pic_choose, R.id.imageView_pic, R.id.imageViewP})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                intent = new Intent(this, FaqActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.linearLayout_faq_time:
                initDatePicker();
                customDatePicker_1.show(DateUtil.getDateToString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"));
                break;
            case R.id.linearLayout_faq_overtime:
                initDatePicker();
                customDatePicker_2.show(DateUtil.getDateToString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"));
                break;
            case R.id.linearLayout_faq_from:
                if (list1 != null && list1.size() != 0) {
                    showPopupWindow(this, list1, 1);
                    adapter.setCheckItem(currentPosition1);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
                break;
            case R.id.linearLayout_faq_type:
                if (list2 != null && list2.size() != 0) {
                    showPopupWindow(this, list2, 2);
                    adapter.setCheckItem(currentPosition2);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
                break;
            case R.id.linearLayout_faq_level:
                if (list3 != null && list3.size() != 0) {
                    showPopupWindow(this, list3, 3);
                    adapter.setCheckItem(currentPosition3);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
                break;
            case R.id.linearLayout_faq_system:
                if (list4 != null && list4.size() != 0) {
                    showPopupWindow(this, list4, 4);
                    adapter.setCheckItem(currentPosition4);
                } else {
                    AppUtils.showToast(this, "配置项为空");
                }
                break;
            case R.id.l_event:
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
            case R.id.l_change:
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
            case R.id.l_config:
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
            case R.id.b_save:
                initView();
                Log.v("eeeeeeeee", editTextFaqTitle.getText().toString() + "===" + cropImageUri + "---" + textViewFaqTime.getText() + "-----" + faqBean.toString());
                if (!editTextFaqTitle.getText().toString().equals("") && !editTextFaqTitle.getText().toString().startsWith("请") && !textViewFaqTime.getText().toString().startsWith("请") && !textViewFaqOvertime.getText().toString().startsWith("请")) {
                    if (cropImageUri == null) {
                        AppUtils.showToast(this, "请上传图片");
                        return;
                    }
                    faqPresenter.visitProjects(this, 2, faqBean, -1);//保存状态2
                } else {
                    AppUtils.showToast(this, "请填写必填项");
                }
                break;
            case R.id.b_save_commit:
                initView();
                Log.v("eeeeeeeee", editTextFaqTitle.getText().toString() + "===" + cropImageUri + "---" + textViewFaqTime.getText() + "-----" + faqBean.toString());
                if (!editTextFaqTitle.getText().toString().equals("") && !editTextFaqTitle.getText().toString().startsWith("请") && !textViewFaqTime.getText().toString().startsWith("请") && !textViewFaqOvertime.getText().toString().startsWith("请")) {
                    if (cropImageUri == null) {
                        AppUtils.showToast(this, "请上传图片");
                        return;
                    }
                    faqPresenter.visitProjects(this, 2, faqBean, -1);//保存状态2
                } else {
                    AppUtils.showToast(this, "请填写必填项");
                }
                break;
            case R.id.imageView_pic_choose:
                showTypeDialog();
                break;
            case R.id.imageView_pic:
                showTypeDialog();
                break;
            case R.id.imageViewP:
                cropImageUri = null;
                p.setVisibility(View.GONE);
                imageViewPicChoose.setVisibility(View.VISIBLE);
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
                p.setVisibility(View.VISIBLE);
                imageViewPicChoose.setVisibility(View.GONE);
                //upLoadLog(cropImageUri);
                //这里上传文件
                if (bitmap != null) {
                    showImages(bitmap);
                }
                faqBean.setFile(getRealFile(this, cropImageUri));//设置图片
                break;
            default:
        }
    }

    private void showImages(Bitmap bitmap) {
        imageViewPic.setImageBitmap(bitmap);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, FaqActivity.class);
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
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case 1:
                currentPosition1 = position;
                textViewFaqFrom.setText(list1.get(position));
                textViewFaqFrom.setTextColor(getResources().getColor(R.color.white));
                faqBean.setOrigin(list1.get(position));
                dialog.dismiss();
                break;
            case 2:
                currentPosition2 = position;
                textViewFaqType.setText(list2.get(position));
                textViewFaqType.setTextColor(getResources().getColor(R.color.white));
                faqBean.setClassification(list2.get(position));
                dialog.dismiss();
                break;
            case 3:
                currentPosition3 = position;
                textViewFaqLevel.setText(list3.get(position));
                textViewFaqLevel.setTextColor(getResources().getColor(R.color.white));
                faqBean.setPriority(position + "");
                dialog.dismiss();
                break;
            case 4:
                currentPosition4 = position;
                textViewFaqSystem.setText(list4.get(position));
                textViewFaqSystem.setTextColor(getResources().getColor(R.color.white));
                faqBean.setSystem(list4.get(position));
                dialog.dismiss();
                break;
        }
    }
}
