package com.mango.leo.dcom.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.login.bean.UserMessageBean;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.HttpUtils;
import com.mango.leo.dcom.util.Urls;

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

public class UserChangeActivity extends AppCompatActivity {

    @Bind(R.id.exit_cx)
    ImageView exitCx;
    @Bind(R.id.editText_name)
    EditText editTextName;
    @Bind(R.id.editText_group)
    EditText editTextGroup;
    @Bind(R.id.editText_phone)
    EditText editTextPhone;
    @Bind(R.id.Change_ok)
    ImageView ChangeOk;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_change);
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userMessageEventBus(UserMessageBean bean) {
        if (bean == null) {
            return;
        }
        Log.v("ppppppp", "__rrrr__" + bean);
        //身份
        editTextName.setText(bean.getRealName());
        editTextGroup.setText(bean.getRoleName());
        if (bean.getPhone() == null || bean.getPhone() == ""){
            editTextPhone.setText("88888888");
        }else {
            editTextPhone.setText(bean.getPhone());
        }
    }
    @OnClick({R.id.exit_cx, R.id.Change_ok})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.exit_cx:
                intent = new Intent(this, UserActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Change_ok:
                changUser();
                break;
        }
    }

    private void changUser() {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("token", sharedPreferences.getString("token", ""));
        mapParams.put("userId", sharedPreferences.getString("id", ""));
        mapParams.put("realName", editTextName.getText().toString());
        HttpUtils.doPost(Urls.HOST_PROFILE, mapParams, new Callback() {
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

    private final UserChangeActivity.MyHandler mHandler = new UserChangeActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<UserChangeActivity> mActivity;

        public MyHandler(UserChangeActivity activity) {
            mActivity = new WeakReference<UserChangeActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            UserChangeActivity activity = mActivity.get();
            Intent intent;
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "修改成功");
                        intent = new Intent(activity, UserActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        AppUtils.showToast(activity, "修改失败");
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
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
