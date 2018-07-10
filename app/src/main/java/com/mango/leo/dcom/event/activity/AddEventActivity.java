package com.mango.leo.dcom.event.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mango.leo.dcom.R;
import com.mango.leo.dcom.event.bean.EventBean;
import com.mango.leo.dcom.event.presenter.EventPresenter;
import com.mango.leo.dcom.event.presenter.EventPresenterImpl;
import com.mango.leo.dcom.event.view.EventView;

import java.util.List;

public class AddEventActivity extends AppCompatActivity implements EventView {
    private EventPresenter eventPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        eventPresenter = new EventPresenterImpl(this);
        //eventPresenter.visitProjects();
    }

    @Override
    public void addEventSuccess(List<EventBean> eventBeans) {

    }

    @Override
    public void addEventMes(String s) {

    }

    @Override
    public void addEventFail(String e) {

    }
}
