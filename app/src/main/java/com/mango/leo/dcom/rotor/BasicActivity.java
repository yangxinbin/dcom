package com.mango.leo.dcom.rotor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.util.CircleProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BasicActivity extends AppCompatActivity {


    @Bind(R.id.circle_bar0)
    CircleProgressBar circleBar0;
    @Bind(R.id.circle_bar1)
    CircleProgressBar circleBar1;
    @Bind(R.id.circle_bar2)
    CircleProgressBar circleBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        ButterKnife.bind(this);
        circleBar0.setProgress(75);//circle百分比
        circleBar1.setProgress(55);//circle百分比
        circleBar2.setProgress(85);//circle百分比

    }
}
