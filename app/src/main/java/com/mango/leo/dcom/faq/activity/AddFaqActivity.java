package com.mango.leo.dcom.faq.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.faq.bean.FaqBean;
import com.mango.leo.dcom.faq.presenter.FaqPresenter;
import com.mango.leo.dcom.faq.presenter.FaqPresenterImpl;
import com.mango.leo.dcom.faq.view.FaqView;
import com.mango.leo.dcom.util.RoundImageView;
import com.mango.leo.dcom.util.flowview.FlowTagLayout;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faq);
        ButterKnife.bind(this);
        faqPresenter = new FaqPresenterImpl(this);
        //eventPresenter.visitProjects();
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

    @OnClick({R.id.imageView_back, R.id.linearLayout_faq_time, R.id.linearLayout_faq_overtime, R.id.linearLayout_faq_from, R.id.linearLayout_faq_type, R.id.linearLayout_faq_level, R.id.linearLayout_faq_system, R.id.l_event, R.id.l_change, R.id.l_config, R.id.b_save, R.id.b_save_commit,R.id.imageView_pic_choose, R.id.imageView_pic, R.id.imageViewP})
    public void onViewClicked(View view) {
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
                break;
            case R.id.l_change:
                break;
            case R.id.l_config:
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

}
