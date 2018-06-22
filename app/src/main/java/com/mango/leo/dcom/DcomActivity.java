package com.mango.leo.dcom;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.style.EasyEditSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mango.leo.dcom.scan.EditScanActivity;
import com.mango.leo.dcom.user.UserActivity;
import com.mango.leo.dcom.zxing.activity.CaptureActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DcomActivity extends AppCompatActivity {

    @Bind(R.id.event)
    ImageView event;
    @Bind(R.id.faq)
    ImageView faq;
    @Bind(R.id.change)
    ImageView change;
    @Bind(R.id.scan)
    ImageView scan;
    @Bind(R.id.rotor)
    ImageView rotor;
    @Bind(R.id.mine)
    ImageView mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcom);
        ButterKnife.bind(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }

    @OnClick({R.id.event, R.id.faq, R.id.change, R.id.scan, R.id.rotor, R.id.mine})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.event:
                break;
            case R.id.faq:
                break;
            case R.id.change:
                break;
            case R.id.scan:
                intent = new Intent(this, CaptureActivity.class);
                startActivity(intent);
                break;
            case R.id.rotor:
                intent = new Intent(this, EditScanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.mine:
                intent = new Intent(this, UserActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
