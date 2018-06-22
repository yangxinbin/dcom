package com.mango.leo.dcom.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePwdActivity extends AppCompatActivity {

    @Bind(R.id.imageView_change_exit)
    ImageView imageViewChangeExit;
    @Bind(R.id.editText_old)
    EditText editTextOld;
    @Bind(R.id.editText_new)
    EditText editTextNew;
    @Bind(R.id.editText_new_ok)
    EditText editTextNewOk;
    @Bind(R.id.imageView_ok)
    ImageView imageViewOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_change_exit, R.id.imageView_ok})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_change_exit:
                intent = new Intent(this, UserActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_ok:
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
