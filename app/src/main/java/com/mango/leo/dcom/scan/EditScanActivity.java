package com.mango.leo.dcom.scan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.rotor.BasicActivity;
import com.mango.leo.dcom.zxing.activity.CaptureActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditScanActivity extends AppCompatActivity {

    @Bind(R.id.imageView_exit)
    ImageView imageViewExit;
    @Bind(R.id.imageView_sure)
    ImageView imageViewSure;
    @Bind(R.id.imageView_scan)
    ImageView imageViewScan;
    @Bind(R.id.editText_xlhao)
    EditText editTextXlhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_scan);
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
                intent = new Intent(this, BasicActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_scan:
                intent = new Intent(this, CaptureActivity.class);
                startActivity(intent);
                finish();
                break;
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
