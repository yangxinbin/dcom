package com.mango.leo.dcom.scan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;

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
                break;
            case R.id.imageView_scan:
                break;
        }
    }
}
