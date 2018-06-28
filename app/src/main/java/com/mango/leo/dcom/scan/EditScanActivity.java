package com.mango.leo.dcom.scan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.login.bean.UserMessageBean;
import com.mango.leo.dcom.rotor.BasicActivity;
import com.mango.leo.dcom.rotor.bean.RotorBean;
import com.mango.leo.dcom.user.UserActivity;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.HttpUtils;
import com.mango.leo.dcom.util.NetUtil;
import com.mango.leo.dcom.util.ProjectsJsonUtils;
import com.mango.leo.dcom.util.Urls;
import com.mango.leo.dcom.zxing.activity.CaptureActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

public class EditScanActivity extends BaseActivity {

    @Bind(R.id.imageView_exit)
    ImageView imageViewExit;
    @Bind(R.id.imageView_sure)
    ImageView imageViewSure;
    @Bind(R.id.imageView_scan)
    ImageView imageViewScan;
    @Bind(R.id.editText_xlhao)
    EditText editTextXlhao;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_scan);
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_exit, R.id.imageView_sure, R.id.imageView_scan})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_exit:
                intent = new Intent(this, DcomActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_sure:
                if (!NetUtil.isNetConnect(this)) {
                    AppUtils.showToast(this, "请连接网络");
                    return;
                }
                loadBasic();
                break;
            case R.id.imageView_scan:
                intent = new Intent(this, CaptureActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void loadBasic() {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("token", sharedPreferences.getString("token", ""));
        mapParams.put("userId", sharedPreferences.getString("id", ""));
        mapParams.put("assetSn", editTextXlhao.getText().toString());
        HttpUtils.doPost(Urls.HOST_ASSET, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    Message m = mHandler.obtainMessage();
                    RotorBean bean = ProjectsJsonUtils.readJsonRotorBeanBeans(response.body().string());//data是json字段获得data的值即对象
                    m.obj = bean;
                    m.what = 0;
                    m.sendToTarget();
                } else {
                    Log.v("yyyyyyyyyy", response.body().string() + "---" + response.code());
                    mHandler.sendEmptyMessage(1);
                }
            }
        });
    }
    private final EditScanActivity.MyHandler mHandler = new EditScanActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<EditScanActivity> mActivity;

        public MyHandler(EditScanActivity activity) {
            mActivity = new WeakReference<EditScanActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            EditScanActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "请求成功");
                        RotorBean bean = (RotorBean) msg.obj;
                        EventBus.getDefault().postSticky(bean);
                        Intent intent = new Intent(activity, BasicActivity.class);
                        editor.putString("assetSn", editTextXlhao.getText().toString())
                                .commit();
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        AppUtils.showToast(activity, "请求失败");
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, DcomActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
