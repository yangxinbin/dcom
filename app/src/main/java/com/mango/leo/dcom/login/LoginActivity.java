package com.mango.leo.dcom.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.imageView_login_ok)
    ImageView imageViewLoginOk;
    @Bind(R.id.editText_name)
    EditText editTextName;
    @Bind(R.id.editText_pwd)
    EditText editTextPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageView_login_ok)
    public void onViewClicked() {
        Intent intent = new Intent(this, DcomActivity.class);
        startActivity(intent);
        finish();
    }
}
