package com.mango.leo.dcom.change.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.change.bean.MethodBeans;
import com.mango.leo.dcom.util.AppUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MethodItemActivity extends BaseActivity {

    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.tv_which)
    TextView tvWhich;
    @Bind(R.id.et_method)
    EditText etMethod;
    private MethodBeans methodBeans;
    private MethodBeans.MethodItem methodItem;
    private int position;
    private List<MethodBeans.MethodItem> beans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_item);
        ButterKnife.bind(this);
        methodBeans = new MethodBeans();
        methodItem = new MethodBeans.MethodItem();
        beans = new ArrayList<>();
        if (beans != null) {
            beans.clear();
        }
        position = getIntent().getIntExtra("position", 0);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventMethodBeans(MethodBeans bean) {
        beans = bean.getMethodItems();
        Log.v("qqqqqqqqq", beans.size()+"==" + position);
        tvWhich.setText("方案" + (position+1) + "：");
        if (beans.size() == position) {
            return;//创建重新编辑
        }
        StringBuffer stringBufferL = new StringBuffer();
        etMethod.setText(bean.getMethodItems().get(position).getDetail());
    }

    private void initDate() {
        methodItem.setStep(position+1);
        methodItem.setDetail(etMethod.getText().toString());
        if (beans.size() == position) {
            beans.add(position, methodItem);//第几个修改第几个
        } else {
            beans.set(position, methodItem);//第几个修改第几个
        }
        Log.v("222222222222", "--initDate--" + beans.toString());
        methodBeans.setMethodItems(beans);
    }

    @OnClick({R.id.imageView_back, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_back:
                finish();
                break;
            case R.id.save:
                initDate();
                if (!etMethod.getText().toString().startsWith("请输入") && methodBeans != null) {
                    EventBus.getDefault().postSticky(methodBeans);
                    EventBus.getDefault().unregister(this);
                    finish();
                } else {
                    AppUtils.showToast(this, "请描述解决方案");
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
