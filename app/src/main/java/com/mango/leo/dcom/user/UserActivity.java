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
import android.widget.TextView;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.login.bean.UserMessageBean;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.HttpUtils;
import com.mango.leo.dcom.util.ProjectsJsonUtils;
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

public class UserActivity extends AppCompatActivity {

    @Bind(R.id.exit_x)
    ImageView exitX;
    @Bind(R.id.textView_changepwd)
    TextView textViewChangepwd;
    @Bind(R.id.editText_name)
    EditText editTextName;
    @Bind(R.id.editText_group)
    EditText editTextGroup;
    @Bind(R.id.editText_phone)
    EditText editTextPhone;
    @Bind(R.id.exit)
    ImageView exit;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userMessageEventBus(UserMessageBean bean) {
        if (bean == null) {
            return;
        }
        Log.v("ppppppp", "__rrrr__" + bean.getPhone());
        //身份
        editTextName.setText(bean.getRealName());
        editTextGroup.setText(bean.getRoleName());
        if (bean.getPhone() == null || bean.getPhone().equals("")){
            editTextPhone.setText("88888888");
        }else {
            editTextPhone.setText(bean.getPhone());
        }
    }

    @OnClick({R.id.exit_x, R.id.textView_changepwd, R.id.exit, R.id.imageView_c})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.exit_x:
                intent = new Intent(this, DcomActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textView_changepwd:
                intent = new Intent(this, ChangePwdActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.exit:
                exitLogin();
                break;
            case R.id.imageView_c:
                intent = new Intent(this, UserChangeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void exitLogin() {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("username", sharedPreferences.getString("username", ""));
        mapParams.put("token", sharedPreferences.getString("token", ""));
        HttpUtils.doPost(Urls.HOST_LOGINOUT, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    mHandler.sendEmptyMessage(0);
                } else {
                    Log.v("yyyyyyyyyy", "---" + response.code());
                    mHandler.sendEmptyMessage(1);
                }
            }
        });
    }

    private final UserActivity.MyHandler mHandler = new UserActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<UserActivity> mActivity;

        public MyHandler(UserActivity activity) {
            mActivity = new WeakReference<UserActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            UserActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "退出成功");
                        finish();
                        break;
                    case 1:
                        AppUtils.showToast(activity, "退出失败");
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
