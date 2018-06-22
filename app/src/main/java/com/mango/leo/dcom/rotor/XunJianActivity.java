package com.mango.leo.dcom.rotor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.scan.EditScanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XunJianActivity extends AppCompatActivity {

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
        }
        return super.onKeyDown(keyCode, event);
    }
}
