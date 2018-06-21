package com.mango.leo.dcom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mango.leo.dcom.user.UserActivity;

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
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcom);
        ButterKnife.bind(this);
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
                break;
            case R.id.rotor:
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
}
