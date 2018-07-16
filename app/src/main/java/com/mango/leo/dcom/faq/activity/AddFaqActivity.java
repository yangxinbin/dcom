package com.mango.leo.dcom.faq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.event.activity.EventActivity;
import com.mango.leo.dcom.event.bean.EventBean;
import com.mango.leo.dcom.faq.bean.FaqBean;
import com.mango.leo.dcom.faq.presenter.FaqPresenter;
import com.mango.leo.dcom.faq.presenter.FaqPresenterImpl;
import com.mango.leo.dcom.faq.view.FaqView;
import com.mango.leo.dcom.util.RoundImageView;
import com.mango.leo.dcom.util.flowview.FlowTagLayout;
import com.mango.leo.dcom.util.flowview.TagAdapter;
import com.mango.leo.dcom.util.relate.ChangeChooseBean;
import com.mango.leo.dcom.util.relate.ConfigActivity;
import com.mango.leo.dcom.util.relate.ConfigChooseBean;
import com.mango.leo.dcom.util.relate.EventChooseBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashSet;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddFaqActivity extends AppCompatActivity implements FaqView {
    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.editText_faq_flag)
    EditText editTextFaqFlag;
    @Bind(R.id.editText_faq_title)
    EditText editTextFaqTitle;
    @Bind(R.id.textView_faq_time)
    TextView textViewFaqTime;
    @Bind(R.id.linearLayout_faq_time)
    LinearLayout linearLayoutFaqTime;
    @Bind(R.id.textView_faq_overtime)
    TextView textViewFaqOvertime;
    @Bind(R.id.linearLayout_faq_overtime)
    LinearLayout linearLayoutFaqOvertime;
    @Bind(R.id.textView_faq_from)
    TextView textViewFaqFrom;
    @Bind(R.id.linearLayout_faq_from)
    LinearLayout linearLayoutFaqFrom;
    @Bind(R.id.textView_faq_type)
    TextView textViewFaqType;
    @Bind(R.id.linearLayout_faq_type)
    LinearLayout linearLayoutFaqType;
    @Bind(R.id.textView_faq_level)
    TextView textViewFaqLevel;
    @Bind(R.id.linearLayout_faq_level)
    LinearLayout linearLayoutFaqLevel;
    @Bind(R.id.textView_faq_system)
    TextView textViewFaqSystem;
    @Bind(R.id.linearLayout_faq_system)
    LinearLayout linearLayoutFaqSystem;
    @Bind(R.id.l_event)
    LinearLayout lEvent;
    @Bind(R.id.l_change)
    LinearLayout lChange;
    @Bind(R.id.l_config)
    LinearLayout lConfig;
    @Bind(R.id.editText_description)
    EditText editTextDescription;
    @Bind(R.id.b_save)
    Button bSave;
    @Bind(R.id.b_save_commit)
    Button bSaveCommit;
    @Bind(R.id.flow_event_layout)
    FlowTagLayout flowEventLayout;
    @Bind(R.id.flow_change_layout)
    FlowTagLayout flowChangeLayout;
    @Bind(R.id.flow_config_layout)
    FlowTagLayout flowConfigLayout;
    @Bind(R.id.imageView_pic_choose)
    ImageView imageViewPicChoose;
    @Bind(R.id.imageView_pic)
    RoundImageView imageViewPic;
    @Bind(R.id.imageViewP)
    ImageView imageViewP;
    @Bind(R.id.p)
    RelativeLayout p;
    private FaqPresenter faqPresenter;
    private boolean flag;
    private TagAdapter tagAdapter;
    private FaqBean faqBean;
    private ConfigChooseBean bean1;
    private EventChooseBean bean_event;
    private ChangeChooseBean bean_change;
    private ConfigChooseBean bean_config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faq);
        faqBean = new FaqBean();

        ButterKnife.bind(this);
        faqPresenter = new FaqPresenterImpl(this);
        EventBus.getDefault().register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBus1(EventChooseBean bean) {
        if (bean == null)
            return;
        bean_event = bean;
        tagAdapter = new TagAdapter(this);
        flowEventLayout.setAdapter(tagAdapter);
        tagAdapter.onlyAddAll(bean.getChooses());
        //faqBean.setRelatedConfigSNs(removeDuplicate(bean.getChooses()));
        if (flag) {
            EventBus.getDefault().removeStickyEvent(ConfigChooseBean.class);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBus2(ChangeChooseBean bean) {
        if (bean == null)
            return;
        bean_change = bean;
        tagAdapter = new TagAdapter(this);
        flowChangeLayout.setAdapter(tagAdapter);
        tagAdapter.onlyAddAll(bean.getChooses());
        //faqBean.setRelatedConfigSNs(removeDuplicate(bean.getChooses()));
        if (flag) {
            EventBus.getDefault().removeStickyEvent(ConfigChooseBean.class);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBus3(ConfigChooseBean bean) {
        if (bean == null)
            return;
        bean_config = bean;
        tagAdapter = new TagAdapter(this);
        flowConfigLayout.setAdapter(tagAdapter);
        tagAdapter.onlyAddAll(bean.getChooses());
        //faqBean.setRelatedConfigSNs(removeDuplicate(bean.getChooses()));
        if (flag) {
            EventBus.getDefault().removeStickyEvent(ConfigChooseBean.class);
        }
    }
    public List<String> removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
    @Override
    public void addFaqSuccess(List<FaqBean> eventBeans) {

    }

    @Override
    public void addFaqMes(String s) {

    }

    @Override
    public void addFaqFail(String e) {

    }

    @OnClick({R.id.imageView_back, R.id.linearLayout_faq_time, R.id.linearLayout_faq_overtime, R.id.linearLayout_faq_from, R.id.linearLayout_faq_type, R.id.linearLayout_faq_level, R.id.linearLayout_faq_system, R.id.l_event, R.id.l_change, R.id.l_config, R.id.b_save, R.id.b_save_commit, R.id.imageView_pic_choose, R.id.imageView_pic, R.id.imageViewP})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                break;
            case R.id.linearLayout_faq_time:
                break;
            case R.id.linearLayout_faq_overtime:
                break;
            case R.id.linearLayout_faq_from:
                break;
            case R.id.linearLayout_faq_type:
                break;
            case R.id.linearLayout_faq_level:
                break;
            case R.id.linearLayout_faq_system:
                break;
            case R.id.l_event:
                flag = false;
                intent = new Intent(this, ConfigActivity.class);
                intent.putExtra("config", "关联事件单");
                intent.putExtra("what", "event");
/*                if (bean1 != null) {
                    Log.v("ccccc", "ccc");
                    EventBus.getDefault().postSticky(bean1);
                }*/
                startActivity(intent);
                break;
            case R.id.l_change:
                flag = false;
                intent = new Intent(this, ConfigActivity.class);
                intent.putExtra("config", "关联变更单");
                intent.putExtra("what", "change");
/*                if (bean1 != null) {
                    Log.v("ccccc", "ccc");
                    EventBus.getDefault().postSticky(bean1);
                }*/
                startActivity(intent);
                break;
            case R.id.l_config:
                flag = false;
                intent = new Intent(this, ConfigActivity.class);
                intent.putExtra("config", "关联配置项");
                intent.putExtra("what", "asset");
/*                if (bean1 != null) {
                    Log.v("ccccc", "ccc");
                    EventBus.getDefault().postSticky(bean1);
                }*/
                startActivity(intent);
                break;
            case R.id.b_save:
                break;
            case R.id.b_save_commit:
                break;
            case R.id.imageView_pic_choose:
                break;
            case R.id.imageView_pic:
                break;
            case R.id.imageViewP:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, FaqActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
