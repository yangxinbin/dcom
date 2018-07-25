package com.mango.leo.dcom.event.activity;

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
import com.mango.leo.dcom.event.bean.MeasureBeans;
import com.mango.leo.dcom.util.AppUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeasureItemActivity extends BaseActivity {

    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.et_Measure)
    EditText etMeasure;
    @Bind(R.id.et_pre_measure)
    EditText etPreMeasure;
    private MeasureBeans measureBeans;
    private MeasureBeans.MeasureItem methodItem;
    private int position;
    private List<MeasureBeans.MeasureItem> beans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_item);
        ButterKnife.bind(this);
        measureBeans = new MeasureBeans();
        methodItem = new MeasureBeans.MeasureItem();
        beans = new ArrayList<>();
        if (beans != null) {
            beans.clear();
        }
        position = getIntent().getIntExtra("position", 0);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventMeasureBeans(MeasureBeans bean) {
        beans = bean.getMeasureItems();
        Log.v("qqqqqqqqq", beans.size() + "==" + position);
        etPreMeasure.setText(bean.getMeasureItems().get(position).getMeasure());
        if (beans.size() == position) {
            return;//创建重新编辑
        }
        StringBuffer stringBufferL = new StringBuffer();
        etMeasure.setText(bean.getMeasureItems().get(position).getMeasure());
    }

    private void initDate() {
        methodItem.setThreat(etPreMeasure.getText().toString());
        methodItem.setMeasure(etMeasure.getText().toString());
        if (beans.size() == position) {
            beans.add(position, methodItem);//第几个修改第几个
        } else {
            beans.set(position, methodItem);//第几个修改第几个
        }
        Log.v("222222222222", "--initDate--" + beans.toString());
        measureBeans.setMeasureItems(beans);
    }

    @OnClick({R.id.imageView_back, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_back:
                finish();
                break;
            case R.id.save:
                initDate();
                if (!etMeasure.getText().toString().startsWith("请输入") && measureBeans != null) {
                    EventBus.getDefault().postSticky(measureBeans);
                    EventBus.getDefault().unregister(this);
                    hintKeyBoard();
                    finish();
                } else {
                    AppUtils.showToast(this, "请描述预防措施");
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
