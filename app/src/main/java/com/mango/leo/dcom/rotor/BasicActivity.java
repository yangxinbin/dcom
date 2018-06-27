package com.mango.leo.dcom.rotor;

import android.Manifest;
import android.app.AlertDialog;
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
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.rotor.adapter.BasicAdapter;
import com.mango.leo.dcom.rotor.bean.RotorBean;
import com.mango.leo.dcom.scan.EditScanActivity;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.CircleProgressBar;
import com.mango.leo.dcom.util.HttpUtils;
import com.mango.leo.dcom.util.PhotoUtils;
import com.mango.leo.dcom.util.ProjectsJsonUtils;
import com.mango.leo.dcom.util.Urls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BasicActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.recycle_ro)
    RecyclerView recycleRo;
    @Bind(R.id.refresh_ro)
    SwipeRefreshLayout refreshRo;
    private LinearLayoutManager mLayoutManager;
    private BasicAdapter adapter;
    private ArrayList<RotorBean.LogBean> mData, mDataAll;
    private int page = 0;
    private RotorBean rotorBean;
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
    private static final int OUTPUT_Y = 480;
    private String TAG = "BasicActivity";
    private ImageView imageView_pic;
    private SharedPreferences sharedPreferences;
    private int newSize;
    private View header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        ButterKnife.bind(this);
        if (getIntent().getStringExtra("refresh") != null && getIntent().getStringExtra("refresh").equals("yes")){
            loadBasic();
        }else {
            EventBus.getDefault().register(this);
            initRecycle();
            initHeader();
            initSwipeRefreshLayout();
            loadHistoryLog(rotorBean);
        }

    }
    private void loadBasic() {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("token", sharedPreferences.getString("token", ""));
        mapParams.put("userId", sharedPreferences.getString("id", ""));
        mapParams.put("assetSn", getIntent().getStringExtra("assetSn"));
        HttpUtils.doPost(Urls.HOST_ASSET, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               // mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    Message m = mHandler.obtainMessage();
                    RotorBean bean = ProjectsJsonUtils.readJsonRotorBeanBeans(response.body().string());//data是json字段获得data的值即对象
                    m.obj = bean;
                    m.what = 2;
                    m.sendToTarget();
                } else {
                    Log.v("yyyyyyyyyy", response.body().string() + "---" + response.code());
                   // mHandler.sendEmptyMessage(1);
                }
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userRotorBus(RotorBean bean) {
        if (bean == null) {
            return;
        }
        rotorBean = bean;
    }

    private void initRecycle() {
        mLayoutManager = new LinearLayoutManager(this);
        recycleRo.setLayoutManager(mLayoutManager);
        recycleRo.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        //recycleRo.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));//左滑
        adapter = new BasicAdapter(getApplicationContext());
        adapter.setOnRotorClickListener(mOnItemClickListener);
        recycleRo.addOnScrollListener(mOnScrollListener);
        //recycleRo.setAdapter(adapter);
        recycleRo.removeAllViews();
        recycleRo.setAdapter(adapter);
    }

    private BasicAdapter.OnRotorClickListener mOnItemClickListener = new BasicAdapter.OnRotorClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            Log.v("oooooooo", adapter.getItem(position) + "---true---" + position);
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getBaseContext(), XunJianActivity.class);
            RotorBean.LogBean logBean = rotorBean.getLog().get(position);
            EventBus.getDefault().postSticky(logBean);
            startActivity(intent);
            finish();
        }
    };
    private int lastVisibleItem;
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();//可见的最后一个item
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            Log.v("zzzzzzzzz", (newState == RecyclerView.SCROLL_STATE_IDLE) + "===" + (lastVisibleItem + 1 == adapter.getItemCount()) + "-------?-----" + adapter.isShowFooter());
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == adapter.getItemCount()
                    && adapter.isShowFooter()) {//加载判断条件 手指离开屏幕 到了footeritem
/*                int count = adapter.getItemCount();
                int i;
                for (i = count; i < count + 5; i++) {
                    if (mDataAll != null && i >= newSize) {//到最后
                        adapter.isShowFooter(false);
                        noMoreMsg();
                        break;
                    }
                    if (mDataAll == null) {
                        break;//一开始断网报空指针的情况
                    }
                    if (i >= newSize) {//比如一共30条新闻 这个条件当为29时还是可以把30那条新闻加上去的
                        noMoreMsg();
                        break;
                    }
                    adapter.addItem(mDataAll.get(i));//addItem里面记得要notifyDataSetChanged 否则第一次加载不会显示数据
                }*/
            }
        }
    };
    public void noMoreMsg() {
        adapter.isShowFooter(false);
        AppUtils.showToast(this, getResources().getString(R.string.no_more));
    }
    private void initHeader() {
        //渲染header布局
        header = LayoutInflater.from(this).inflate(R.layout.header, null);
        LinearLayout h = (LinearLayout) header.findViewById(R.id.header);
        ImageView imageView_back = (ImageView) header.findViewById(R.id.imageView_back);
        TextView textView_xunjian = (TextView) header.findViewById(R.id.textView_xunjian);
        imageView_pic = (ImageView) header.findViewById(R.id.imageView_pic);

        TextView textView1 = (TextView) header.findViewById(R.id.textView_1);
        TextView textView2 = (TextView) header.findViewById(R.id.textView_2);
        TextView textView3 = (TextView) header.findViewById(R.id.textView_3);
        TextView textView4 = (TextView) header.findViewById(R.id.textView_4);
        TextView textView5 = (TextView) header.findViewById(R.id.textView_5);
        TextView textView6 = (TextView) header.findViewById(R.id.textView_6);
        TextView textView7 = (TextView) header.findViewById(R.id.textView_7);
        TextView textView8 = (TextView) header.findViewById(R.id.textView_8);

        CircleProgressBar circleProgressBar0 = (CircleProgressBar) header.findViewById(R.id.circle_bar0);
        CircleProgressBar circleProgressBar1 = (CircleProgressBar) header.findViewById(R.id.circle_bar1);
        CircleProgressBar circleProgressBar2 = (CircleProgressBar) header.findViewById(R.id.circle_bar2);

        TextView textView_pe = (TextView) header.findViewById(R.id.textView_pe);
        TextView textView_phone = (TextView) header.findViewById(R.id.textView_phone);


        ImageView imageView_edit = (ImageView) header.findViewById(R.id.imageView_edit);
        ConstraintLayout.LayoutParams layoutParam = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParam.setMargins(0, 0, 0, 20);
        h.setLayoutParams(layoutParam);

        textView1.setText(rotorBean.getName());
        textView2.setText(rotorBean.getIp());
        textView3.setText(rotorBean.getSn());
        textView4.setText(rotorBean.getCpu());
        textView5.setText(rotorBean.getRam());
        textView6.setText(rotorBean.getStorageSize());
        textView7.setText(rotorBean.getUseFor());
        textView8.setText((CharSequence) rotorBean.getOs());
        Glide.with(this).load("http://dcom.hopesen.com.cn" + rotorBean.getPhoto().getUrl()).into(imageView_pic);

        if (rotorBean.getStatusCpu() != 0){
            circleProgressBar0.setProgress(rotorBean.getStatusCpu());
        }else {
            circleProgressBar0.setProgress(0);
        }
        if (rotorBean.getStatusRAM() != 0){
            circleProgressBar1.setProgress(rotorBean.getStatusRAM());
        }else {
            circleProgressBar1.setProgress(0);
        }
        if (rotorBean.getStatusStorage() != 0){
            circleProgressBar2.setProgress(rotorBean.getStatusStorage());
        }else {
            circleProgressBar2.setProgress(0);
        }


        textView_pe.setText(rotorBean.getSupervisor());
        textView_phone.setText(rotorBean.getSupervisorPhone());

        imageView_back.setOnClickListener(this);
        textView_xunjian.setOnClickListener(this);
        imageView_edit.setOnClickListener(this);
        adapter.setHeaderView(h);
    }

    private void initSwipeRefreshLayout() {
        refreshRo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mData != null && mDataAll != null) {
                            mDataAll.clear();//一定要加上否则会报越界异常 不执行代码加载的if判断
                            mData.clear();
                        }
                        refreshRo.setRefreshing(false);
                        loadBasic();//请求刷新
                    }
                }, 2000);
            }
        });
        refreshRo.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void loadHistoryLog(RotorBean rotorBean) {
        if (mData != null || mDataAll !=null){
            mData.clear();
           // mDataAll.clear();
        }
        newSize = rotorBean.getLog().size();
        //adapter.isShowFooter(true);//不能屏蔽 滑动监听条件，加载使用
        if (mData == null && mDataAll == null) {
           // mDataAll = new ArrayList<RotorBean.LogBean>();
            mData = new ArrayList<RotorBean.LogBean>();
        }
        for (int i = 0; i < newSize; i++) {
            mData.add(rotorBean.getLog().get(i));
        }
/*        if ((lastVisibleItem != (newSize - 2)) || (lastVisibleItem != (newSize - 1))) {
            adapter.setmDate(mData);//防止加了缓存越界报错
        }*/
            adapter.setmDate(mData);//防止加了缓存越界报错
    }

/*    @OnClick({R.id.imageView_back, R.id.textView_xunjian, R.id.imageView_edit})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                intent = new Intent(this, BasicActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textView_xunjian:
                intent = new Intent(this, XunJianActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_edit:
                break;
        }
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, EditScanActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                intent = new Intent(this, EditScanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textView_xunjian:
                intent = new Intent(this, XunJianActivity.class);
                intent.putExtra("assetSn",getIntent().getStringExtra("assetSn"));
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_edit:
                showTypeDialog();
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

    //存放图片的地址，可以改动
    private Uri BitMap(Bitmap bitmap) {
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/Bmob");    //保存地址及命名
        if (!tmpDir.exists()) {
            tmpDir.mkdir(); //生成目录进行保存
        }
        File img = new File(tmpDir.getAbsolutePath() + "avatar.png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bitmap.compress(Bitmap.CompressFormat.PNG, 85, fos);  //参:压缩的格式，图片质量85，输出流
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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
                PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 3, 2, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
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
                    PhotoUtils.cropImageUri(this, newUri, cropImageUri, 3, 2, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                } else {
                    AppUtils.showToast(this, "设备没有SD卡！");
                }
                break;
            //裁剪返回
            case CODE_RESULT_REQUEST:
                Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                upLoadMap(getRealFilePath(this,cropImageUri));
                //这里上传文件
                if (bitmap != null) {
                    showImages(bitmap);
                }
                break;
            default:
        }
    }

    private void upLoadMap(final File realFilePath) {
        final Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mapParams.put("token", sharedPreferences.getString("token", ""));
                mapParams.put("userId", sharedPreferences.getString("id", ""));
                mapParams.put("assetSn", getIntent().getStringExtra("assetSn"));
                mapParams.put("base64", AppUtils.GetImageStr(realFilePath));
                mapParams.put("imageType", "png");
                HttpUtils.doPost(Urls.HOST_SAVEPHOTO, mapParams, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.sendEmptyMessage(1);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (String.valueOf(response.code()).startsWith("2")) {
                            mHandler.sendEmptyMessage(0);
                            Log.v("yyyyyyyyyy", "----");
                        } else {
                            Log.v("yyyyyyyyyy", response.body().string() + "---" + response.code());
                            mHandler.sendEmptyMessage(1);
                        }
                    }
                });                
            }
        }).start();
    }
    private final BasicActivity.MyHandler mHandler = new BasicActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<BasicActivity> mActivity;

        public MyHandler(BasicActivity activity) {
            mActivity = new WeakReference<BasicActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BasicActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "图片上传成功");
                        break;
                    case 1:
                        AppUtils.showToast(activity, "图片上传失败");
                        break;
                    case 2:
                        RotorBean bean = (RotorBean) msg.obj;
                        rotorBean = bean;
                        //AppUtils.showToast(activity, "刷新");
                        initRecycle();
                        initHeader();
                        initSwipeRefreshLayout();
                        loadHistoryLog(rotorBean);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);

    }
    private void showImages(Bitmap bitmap) {
        imageView_pic.setImageBitmap(bitmap);
    }
    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static File getRealFilePath(final Context context, final Uri uri) {
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
