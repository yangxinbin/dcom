package com.mango.leo.dcom.rotor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.rotor.bean.RotorBean;
import com.mango.leo.dcom.scan.EditScanActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XunJianActivity extends BaseActivity {

    @Bind(R.id.imageView_b)
    ImageView imageViewB;
    @Bind(R.id.imageView_p)
    ImageView imageViewP;
    @Bind(R.id.imageView_delete)
    ImageView imageViewDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xun_jian);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userLogBus(RotorBean.LogBean bean) {
        if (bean == null) {
            return;
        }

    }

    @OnClick({R.id.imageView_b, R.id.imageView_p, R.id.imageView_delete})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_b:
                intent = new Intent(this, BasicActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_p:
                break;
            case R.id.imageView_delete:
                break;
        }
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
