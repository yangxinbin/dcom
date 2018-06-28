package com.mango.leo.dcom.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.login.bean.UserMessageBean;
import com.mango.leo.dcom.rotor.bean.RotorBean;
import com.mango.leo.dcom.util.ACache;
import com.mango.leo.dcom.util.AppUtils;
import com.mango.leo.dcom.util.HttpUtils;
import com.mango.leo.dcom.util.NetUtil;
import com.mango.leo.dcom.util.ProjectsJsonUtils;
import com.mango.leo.dcom.util.Urls;

import org.greenrobot.eventbus.EventBus;

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

public class LoginActivity extends BaseActivity {

    @Bind(R.id.imageView_login_ok)
    ImageView imageViewLoginOk;
    @Bind(R.id.editText_name)
    EditText editTextName;
    @Bind(R.id.editText_pwd)
    EditText editTextPwd;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private UserMessageBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("DCOM", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        ButterKnife.bind(this);
        if (sharedPreferences.getString("isok","no").equals("yes")){
            ACache mCache = ACache.get(this);
            userBean = ProjectsJsonUtils.readJsonUserMessageBeans(mCache.getAsString("message"));
            EventBus.getDefault().postSticky(userBean);
            editor.putString("token", userBean.getToken())
                    .putString("id", String.valueOf(userBean.getId()))
                    .putString("tenantId", String.valueOf(userBean.getTenantId()))
                    .putString("username", String.valueOf(userBean.getUsername()))
                    .commit();
            Intent intent = new Intent(this, DcomActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.imageView_login_ok)
    public void onViewClicked() {
        if (!NetUtil.isNetConnect(this)) {
            AppUtils.showToast(this, "请连接网络");
            return;
        }
        loadAuthor();
       /* Intent intent = new Intent(this, DcomActivity.class);
        startActivity(intent);
        finish();*/
    }

    private void loadAuthor() {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("username", editTextName.getText().toString());
        mapParams.put("password", editTextPwd.getText().toString());
        HttpUtils.doPost(Urls.HOST_AUTH, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    Log.v("yyyyyyyyyy", "---" + response.code());
                    Message m = mHandler.obtainMessage();
                    UserMessageBean bean = ProjectsJsonUtils.readJsonUserMessageBeans(response.body().string(), getBaseContext());//data是json字段获得data的值即对象
                    m.obj = bean;
                    m.what = 0;
                    m.sendToTarget();
                } else {
                    Log.v("yyyyyyyyyy", "---" + response.code());
                    mHandler.sendEmptyMessage(1);
                }
            }
        });
    }

    private final MyHandler mHandler = new MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<LoginActivity> mActivity;

        public MyHandler(LoginActivity activity) {
            mActivity = new WeakReference<LoginActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LoginActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "登录成功");
                        UserMessageBean bean = (UserMessageBean) msg.obj;
                        EventBus.getDefault().postSticky(bean);
                        editor.putString("token", bean.getToken())
                                .putString("id", String.valueOf(bean.getId()))
                                .putString("tenantId", String.valueOf(bean.getTenantId()))
                                .putString("username", String.valueOf(bean.getUsername()))
                                .putString("isok", "yes")
                                .commit();
                        Intent intent = new Intent(activity, DcomActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        AppUtils.showToast(activity, "登录失败");
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
