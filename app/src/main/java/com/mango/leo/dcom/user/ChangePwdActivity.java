package com.mango.leo.dcom.user;

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
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.HttpUtils;
import com.mango.leo.dcom.util.Urls;

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

public class ChangePwdActivity extends BaseActivity {

    @Bind(R.id.imageView_change_exit)
    ImageView imageViewChangeExit;
    @Bind(R.id.editText_old)
    EditText editTextOld;
    @Bind(R.id.editText_new)
    EditText editTextNew;
    @Bind(R.id.editText_new_ok)
    EditText editTextNewOk;
    @Bind(R.id.imageView_ok)
    ImageView imageViewOk;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_change_exit, R.id.imageView_ok})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_change_exit:
                intent = new Intent(this, UserActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_ok:
                if (editTextNew.getText().toString().equals(editTextNewOk.getText().toString())){
                    changePwd();
                }else {
                    AppUtils.showToast(this, "两次密码输入不相同");
                }
                break;
        }
    }

    private void changePwd() {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("token", sharedPreferences.getString("token", ""));
        mapParams.put("userId", sharedPreferences.getString("id", ""));
        mapParams.put("username", sharedPreferences.getString("username", ""));
        mapParams.put("oldPassword", editTextOld.getText().toString());
        mapParams.put("newPassword", editTextNewOk.getText().toString());
        Log.v("ccccc",sharedPreferences.getString("token", "")+"=="+sharedPreferences.getString("userId", "")+"=="+sharedPreferences.getString("username", "")+"=-="+editTextOld.getText().toString()+"=="+editTextNewOk.getText().toString());
        HttpUtils.doPost(Urls.HOST_CHANGEPASSWORD, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    mHandler.sendEmptyMessage(0);
                } else {
                    Log.v("yyyyyyyyyy", response.body().string()+"---" + response.code());
                    mHandler.sendEmptyMessage(1);
                }
            }
        });
    }
    private final ChangePwdActivity.MyHandler mHandler = new ChangePwdActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<ChangePwdActivity> mActivity;

        public MyHandler(ChangePwdActivity activity) {
            mActivity = new WeakReference<ChangePwdActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ChangePwdActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "修改密码成功");
                        Intent intent = new Intent(activity, UserActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        AppUtils.showToast(activity, "修改密码失败");
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
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
