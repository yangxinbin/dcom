package com.mango.leo.dcom.change.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.event.activity.EventActivity;
import com.mango.leo.dcom.util.RoundImageView;
import com.mango.leo.dcom.util.flowview.FlowTagLayout;
import com.mango.leo.dcom.util.relate.ChangeChooseBean;
import com.mango.leo.dcom.util.relate.ConfigChooseBean;
import com.mango.leo.dcom.util.relate.EventChooseBean;
import com.mango.leo.dcom.util.relate.ProblemChooseBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddChangeActivity extends AppCompatActivity {

    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.editText_change_flag)
    EditText editTextChangeFlag;
    @Bind(R.id.editText_change_title)
    EditText editTextChangeTitle;
    @Bind(R.id.textView_change_time)
    TextView textViewChangeTime;
    @Bind(R.id.linearLayout_change_time)
    LinearLayout linearLayoutChangeTime;
    @Bind(R.id.textView_change_overtime)
    TextView textViewChangeOvertime;
    @Bind(R.id.linearLayout_change_overtime)
    LinearLayout linearLayoutChangeOvertime;
    @Bind(R.id.editText_change_oa)
    EditText editTextChangeOa;
    @Bind(R.id.textView_change_type)
    TextView textViewChangeType;
    @Bind(R.id.linearLayout_change_type)
    LinearLayout linearLayoutChangeType;
    @Bind(R.id.textView_change_effect)
    TextView textViewChangeEffect;
    @Bind(R.id.linearLayout_change_effect)
    LinearLayout linearLayoutChangeEffect;
    @Bind(R.id.textView_change_degree)
    TextView textViewChangeDegree;
    @Bind(R.id.linearLayout_change_degree)
    LinearLayout linearLayoutChangeDegree;
    @Bind(R.id.textView_change_risk)
    TextView textViewChangeRisk;
    @Bind(R.id.linearLayout_change_risk)
    LinearLayout linearLayoutChangeRisk;
    @Bind(R.id.textView_event_faqlist)
    TextView textViewEventFaqlist;
    @Bind(R.id.linearLayout_event_faqlist)
    LinearLayout linearLayoutEventFaqlist;
    @Bind(R.id.flow_event_layout)
    FlowTagLayout flowEventLayout;
    @Bind(R.id.textView_change_faqlist)
    TextView textViewChangeFaqlist;
    @Bind(R.id.linearLayout_change_faqlist)
    LinearLayout linearLayoutChangeFaqlist;
    @Bind(R.id.flow_problem_layout)
    FlowTagLayout flowProblemLayout;
    @Bind(R.id.textView_change_changelist)
    TextView textViewChangeChangelist;
    @Bind(R.id.linearLayout_change_changelist)
    LinearLayout linearLayoutChangeChangelist;
    @Bind(R.id.flow_change_layout)
    FlowTagLayout flowChangeLayout;
    @Bind(R.id.textView_change_item)
    TextView textViewChangeItem;
    @Bind(R.id.linearLayout_change_item)
    LinearLayout linearLayoutChangeItem;
    @Bind(R.id.flow_config_layout)
    FlowTagLayout flowConfigLayout;
    @Bind(R.id.editText_description)
    EditText editTextDescription;
    @Bind(R.id.editText_content)
    EditText editTextContent;
    @Bind(R.id.b_save)
    Button bSave;
    @Bind(R.id.b_save_commit)
    Button bSaveCommit;
    @Bind(R.id.imageView_pic_choose)
    ImageView imageViewPicChoose;
    @Bind(R.id.imageView_pic)
    RoundImageView imageViewPic;
    @Bind(R.id.imageViewP)
    ImageView imageViewP;
    @Bind(R.id.p)
    RelativeLayout p;
    @Bind(R.id.e_method_way)
    EditText eMethodWay;
    @Bind(R.id.e_revert)
    EditText eRevert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_change);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_back, R.id.linearLayout_change_time, R.id.linearLayout_change_overtime, R.id.linearLayout_change_type, R.id.linearLayout_change_effect, R.id.linearLayout_change_degree, R.id.linearLayout_change_risk, R.id.linearLayout_event_faqlist, R.id.linearLayout_change_faqlist, R.id.linearLayout_change_changelist, R.id.linearLayout_change_item, R.id.imageView_pic_choose, R.id.imageView_pic, R.id.imageViewP})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                intent = new Intent(this, ChangeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.linearLayout_change_time:
                break;
            case R.id.linearLayout_change_overtime:
                break;
            case R.id.linearLayout_change_type:
                break;
            case R.id.linearLayout_change_effect:
                break;
            case R.id.linearLayout_change_degree:
                break;
            case R.id.linearLayout_change_risk:
                break;
            case R.id.linearLayout_event_faqlist:
                break;
            case R.id.linearLayout_change_faqlist:
                break;
            case R.id.linearLayout_change_changelist:
                break;
            case R.id.linearLayout_change_item:
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
            Intent intent = new Intent(this, ChangeActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().removeStickyEvent(EventChooseBean.class);
        EventBus.getDefault().removeStickyEvent(ChangeChooseBean.class);
        EventBus.getDefault().removeStickyEvent(ConfigChooseBean.class);
        EventBus.getDefault().removeStickyEvent(ProblemChooseBean.class);
        EventBus.getDefault().unregister(this);
    }
}
