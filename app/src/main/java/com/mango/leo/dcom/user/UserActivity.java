package com.mango.leo.dcom.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends AppCompatActivity {

    @Bind(R.id.exit_x)
    ImageView exitX;
    @Bind(R.id.textView_changepwd)
    TextView textViewChangepwd;
    @Bind(R.id.editText_name)
    EditText editTextName;
    @Bind(R.id.editText_group)
    EditText editTextGroup;
    @Bind(R.id.editText_phone)
    EditText editTextPhone;
    @Bind(R.id.exit)
    ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.exit_x, R.id.textView_changepwd, R.id.exit})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.exit_x:
                intent = new Intent(this, DcomActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textView_changepwd:
                intent = new Intent(this, ChangePwdActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.exit:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
