package com.mango.leo.dcom;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.change.activity.ChangeActivity;
import com.mango.leo.dcom.event.activity.EventActivity;
import com.mango.leo.dcom.faq.activity.FaqActivity;
import com.mango.leo.dcom.scan.EditScanActivity;
import com.mango.leo.dcom.user.UserActivity;
import com.mango.leo.dcom.zxing.activity.CaptureActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DcomActivity extends BaseActivity {

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
                intent = new Intent(this, EventActivity.class);
                startActivity(intent);
                finish();
                //showDailog("DCOM", "敬请期待");
                break;
            case R.id.faq:
                intent = new Intent(this, FaqActivity.class);
                startActivity(intent);
                finish();
                //showDailog("DCOM", "敬请期待");
                break;
            case R.id.change:
                intent = new Intent(this, ChangeActivity.class);
                startActivity(intent);
                finish();
                //showDailog("DCOM", "敬请期待");
                break;
            case R.id.scan:
                intent = new Intent(this, CaptureActivity.class);
                startActivity(intent);
                finish();
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
    private void showDailog(String s1, final String s2) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)//设置标题的图片
                .setTitle(s1)//设置对话框的标题
                .setMessage(s2)//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
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
