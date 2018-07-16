package com.mango.leo.dcom.faq.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.faq.bean.FaqBean;
import com.mango.leo.dcom.faq.presenter.FaqPresenter;
import com.mango.leo.dcom.faq.presenter.FaqPresenterImpl;
import com.mango.leo.dcom.faq.view.FaqView;

import java.util.List;

public class AddFaqActivity extends AppCompatActivity implements FaqView{
    private FaqPresenter faqPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faq);
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
}
