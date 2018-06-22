package com.mango.leo.dcom.rotor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.scan.EditScanActivity;
import com.mango.leo.dcom.util.CircleProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasicActivity extends AppCompatActivity {


    @Bind(R.id.circle_bar0)
    CircleProgressBar circleBar0;
    @Bind(R.id.circle_bar1)
    CircleProgressBar circleBar1;
    @Bind(R.id.circle_bar2)
    CircleProgressBar circleBar2;
    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.textView_xunjian)
    TextView textViewXunjian;
    @Bind(R.id.imageView_edit)
    ImageView imageViewEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        ButterKnife.bind(this);
        circleBar0.setProgress(75);//circle百分比
        circleBar1.setProgress(55);//circle百分比
        circleBar2.setProgress(85);//circle百分比

    }

    @OnClick({R.id.imageView_back, R.id.textView_xunjian, R.id.imageView_edit})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                intent = new Intent(this, EditScanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textView_xunjian:
                intent = new Intent(this, XunJianActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_edit:
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, EditScanActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
