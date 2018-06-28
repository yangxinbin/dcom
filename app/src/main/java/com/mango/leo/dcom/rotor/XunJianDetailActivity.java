package com.mango.leo.dcom.rotor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.rotor.bean.RotorBean;
import com.mango.leo.dcom.util.DateUtil;
import com.mango.leo.dcom.util.RoundImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XunJianDetailActivity extends BasicActivity {

    @Bind(R.id.imageView_b)
    ImageView imageViewB;
    @Bind(R.id.editText_d)
    EditText editText;
    @Bind(R.id.imageView_p)
    RoundImageView imageViewP;
    @Bind(R.id.textView_s1)
    TextView textViewS1;
    @Bind(R.id.textView_s2)
    TextView textViewS2;
    @Bind(R.id.textView_s3)
    TextView textViewS3;
    @Bind(R.id.textView_t)
    TextView textViewT;
    @Bind(R.id.textView_p)
    TextView textViewP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xun_jian_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userLogBus(RotorBean.LogBean bean) {
        if (bean == null) {
            return;
        }
        textViewP.setText(bean.getInspectionBy());
        textViewT.setText(DateUtil.getDateToString(bean.getInspectionOn(), "yyyy-MM-dd HH:mm:ss"));
        if (bean.getPowerLineStatus().equals("normal")) {
            textViewS1.setText("正常");
            textViewS1.setTextColor(getResources().getColor(R.color.green));
        } else {
            textViewS1.setText("异常");
            textViewS1.setTextColor(getResources().getColor(R.color.red));
        }
        if (bean.getNetworkCableStatus().equals("normal")) {
            textViewS2.setText("正常");
            textViewS2.setTextColor(getResources().getColor(R.color.green));
        } else {
            textViewS2.setText("异常");
            textViewS2.setTextColor(getResources().getColor(R.color.red));
        }
        if (bean.getIndicatorStatus().equals("normal")) {
            textViewS3.setText("正常");
            textViewS3.setTextColor(getResources().getColor(R.color.green));
        } else {
            textViewS3.setText("异常");
            textViewS3.setTextColor(getResources().getColor(R.color.red));
        }
        if (bean.getInspectionRemarks() != null){
            editText.setText(bean.getInspectionRemarks().toString());
        }
        Log.v("dddddddddd",""+"http://dcom.hopesen.com.cn" + bean.getAttachments().get(0).getUrl());
        Glide.with(this).load("http://dcom.hopesen.com.cn" + bean.getAttachments().get(0).getUrl()).into(imageViewP);
    }

    @OnClick(R.id.imageView_b)
    public void onViewClicked() {
        Intent intent = new Intent(this, BasicActivity.class);
        intent.putExtra("refresh","yes");
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, BasicActivity.class);
            intent.putExtra("refresh","yes");
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
