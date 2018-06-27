package com.mango.leo.dcom.rotor;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.rotor.bean.RotorBean;
import com.mango.leo.dcom.scan.EditScanActivity;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.HttpUtils;
import com.mango.leo.dcom.util.PhotoUtils;
import com.mango.leo.dcom.util.ProjectsJsonUtils;
import com.mango.leo.dcom.util.RoundImageView;
import com.mango.leo.dcom.util.Urls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XunJianActivity extends BaseActivity {

    @Bind(R.id.imageView_b)
    ImageView imageViewB;
    @Bind(R.id.imageView_p)
    RoundImageView imageViewP;
    @Bind(R.id.imageView_delete)
    ImageView imageViewDelete;
    @Bind(R.id.radioButton1)
    Switch radioButton1;
    @Bind(R.id.radioButton2)
    Switch radioButton2;
    @Bind(R.id.radioButton3)
    Switch radioButton3;
    @Bind(R.id.textView_s1)
    TextView textViewS1;
    @Bind(R.id.textView_s2)
    TextView textViewS2;
    @Bind(R.id.textView_s3)
    TextView textViewS3;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    @Bind(R.id.imageView_choose)
    ImageView imageViewChoose;
    @Bind(R.id.editText)
    EditText editText;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private static final int OUTPUT_X = 480;
    private static final int OUTPUT_Y = 480;
    private String TAG = "XunJianActivity";
    private String s1 = "true";
    private String s2 = "true";
    private String s3 = "true";
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xun_jian);
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        ButterKnife.bind(this);
        initSwitch();
    }

    private void initSwitch() {
        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("NewApi")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textViewS1.setText("正常");
                    s1 = "true";
                    textViewS1.setTextColor(getResources().getColor(R.color.green));
                } else {
                    textViewS1.setText("异常");
                    s1 = "false";
                    textViewS1.setTextColor(getResources().getColor(R.color.red));
                }
            }
        });
        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textViewS2.setText("正常");
                    s2 = "true";
                    textViewS2.setTextColor(getResources().getColor(R.color.green));
                } else {
                    textViewS2.setText("异常");
                    s2 = "false";
                    textViewS2.setTextColor(getResources().getColor(R.color.red));
                }
            }
        });
        radioButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textViewS3.setText("正常");
                    s3 = "true";
                    textViewS3.setTextColor(getResources().getColor(R.color.green));
                } else {
                    textViewS3.setText("异常");
                    s3 = "false";
                    textViewS3.setTextColor(getResources().getColor(R.color.red));
                }
            }
        });
    }

    @OnClick({R.id.imageView_b, R.id.imageView_choose, R.id.imageView_delete, R.id.sure})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_b:
                intent = new Intent(this, BasicActivity.class);
                intent.putExtra("refresh","yes");
                intent.putExtra("assetSn",getIntent().getStringExtra("assetSn"));
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_choose:
                showTypeDialog();
                break;
            case R.id.imageView_delete:
                cropImageUri = null;
                imageViewDelete.setVisibility(View.GONE);
                imageViewChoose.setVisibility(View.VISIBLE);
                imageViewP.setVisibility(View.INVISIBLE);
                break;
            case R.id.sure:
                upLoadLog(cropImageUri);
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
                imageViewDelete.setVisibility(View.VISIBLE);
                imageViewChoose.setVisibility(View.GONE);
                imageViewP.setVisibility(View.VISIBLE);
                //upLoadLog(cropImageUri);
                //这里上传文件
                if (bitmap != null) {
                    showImages(bitmap);
                }
                break;
            default:
        }
    }

    private void upLoadLog(Uri cropImageUri) {
        File realFilePath = null;
        if (cropImageUri == null) {
            AppUtils.showToast(this,"请上传图片");
            return;
        }
/*        if (editText == null || editText.getText().toString().startsWith("请描述") || editText.getText().toString().equals("")) {
            AppUtils.showToast(this,"请填写巡检说明");
            return;
        }*/
        realFilePath = getRealFilePath(this, cropImageUri);
        final Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.clear();
        final File finalRealFilePath = realFilePath;
        Log.v("xxxxxx", "==" + s1 + " " + s2 + " " + s3 + " " + editText.getText().toString() + " " + sharedPreferences.getString("username", ""));
        new Thread(new Runnable() {
            @Override
            public void run() {
                mapParams.put("token", sharedPreferences.getString("token", ""));
                mapParams.put("userId", sharedPreferences.getString("id", ""));
                mapParams.put("tenant", sharedPreferences.getString("tenantId", ""));
                mapParams.put("assetSn", getIntent().getStringExtra("assetSn"));
                mapParams.put("isIndicatorNormal", s3);
                mapParams.put("isPowerLineNormal", s1);
                mapParams.put("isNetworkCableNormal", s2);
                mapParams.put("inspectionRemarks", ""/*editText.getText().toString()*/);
                //mapParams.put("attachments", "");
                if (finalRealFilePath != null) {
                    mapParams.put("base64", AppUtils.GetImageStr(finalRealFilePath));
                    mapParams.put("imageType", "jpeg");
                }
                HttpUtils.doPost(Urls.HOST_INSPECT, mapParams, new Callback() {
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

    private final MyHandler mHandler = new MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<XunJianActivity> mActivity;

        public MyHandler(XunJianActivity activity) {
            mActivity = new WeakReference<XunJianActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            XunJianActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "巡检保存成功");
                        Intent intent = new Intent(activity, BasicActivity.class);
                        intent.putExtra("refresh","yes");
                        intent.putExtra("assetSn",getIntent().getStringExtra("assetSn"));
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        AppUtils.showToast(activity, "巡检保存失败");
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void showImages(Bitmap bitmap) {
        imageViewP.setImageBitmap(bitmap);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, BasicActivity.class);
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
