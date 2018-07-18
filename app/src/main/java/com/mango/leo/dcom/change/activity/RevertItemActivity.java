package com.mango.leo.dcom.change.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.change.bean.RevertBeans;
import com.mango.leo.dcom.util.AppUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RevertItemActivity extends BaseActivity {

    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.tv_which)
    TextView tvWhich;
    @Bind(R.id.et_revert)
    EditText etRevert;
    private RevertBeans RevertBeans;
    private RevertBeans.RevertItem RevertItem;
    private int position;
    private List<RevertBeans.RevertItem> beans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revert_item);
        ButterKnife.bind(this);
        RevertBeans = new RevertBeans();
        RevertItem = new RevertBeans.RevertItem();
        beans = new ArrayList<>();
        if (beans != null) {
            beans.clear();
        }
        position = getIntent().getIntExtra("position", 0);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventRevertBeans(RevertBeans bean) {
        beans = bean.getRevertItems();
        Log.v("qqqqqqqqq", beans.size()+"==" + position);
        tvWhich.setText("计划" + (position+1) + "：");
        if (beans.size() == position) {
            return;//创建重新编辑
        }
        StringBuffer stringBufferL = new StringBuffer();
        etRevert.setText(bean.getRevertItems().get(position).getDetail());
    }

    private void initDate() {
        RevertItem.setStep(position+1);
        RevertItem.setDetail(etRevert.getText().toString());
        if (beans.size() == position) {
            beans.add(position, RevertItem);//第几个修改第几个
        } else {
            beans.set(position, RevertItem);//第几个修改第几个
        }
        Log.v("222222222222", "--initDate--" + beans.toString());
        RevertBeans.setRevertItems(beans);
    }

    @OnClick({R.id.imageView_back, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_back:
                finish();
                break;
            case R.id.save:
                initDate();
                if (!etRevert.getText().toString().startsWith("请输入") && RevertBeans != null) {
                    EventBus.getDefault().postSticky(RevertBeans);
                    EventBus.getDefault().unregister(this);
                    hintKeyBoard();
                    finish();
                } else {
                    AppUtils.showToast(this, "请描述解决方案");
                }
                break;
        }
    }
    public void hintKeyBoard() {
        //拿到InputMethodManager
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //如果window上view获取焦点 && view不为空
        if (imm.isActive() && getCurrentFocus() != null) {
            //拿到view的token 不为空
            if (getCurrentFocus().getWindowToken() != null) {
                //表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
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
