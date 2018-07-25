package com.mango.leo.dcom.event.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mango.leo.dcom.event.bean.EventBean;
import com.mango.leo.dcom.event.bean.ListEventBean;
import com.mango.leo.dcom.event.listener.OnEventListener;
import com.mango.leo.dcom.event.model.EventModel;
import com.mango.leo.dcom.event.model.EventModelImpl;
import com.mango.leo.dcom.event.view.EventView;
import com.mango.leo.dcom.util.Urls;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/5/21.
 */

public class EventPresenterImpl implements EventPresenter, OnEventListener {
    private EventView eventView;
    private EventModel eventModel;
    private SharedPreferences sharedPreferences;

    public EventPresenterImpl(EventView u) {
        this.eventView = u;
        this.eventModel = new EventModelImpl();
    }

    @Override
    public void visitProjects(Context context, int type, EventBean eventBean, int page) {
        sharedPreferences = context.getSharedPreferences("DCOM", MODE_PRIVATE);
        String url = null;
        if (type == 0) {
            url = getUrl(type, context) + "?token=" + sharedPreferences.getString("token", "") + "&pageNum=" + page;
        } else if (type == 1) {
            url = getUrl(type, context) + "?token=" + sharedPreferences.getString("token", "") + "&pageNum=" + page;
        } else if (type == 2) {
            url = getUrl(type, context);
        } else if (type == 3) {
            url = getUrl(type, context);
        }else if (type == -1) {
            url = getUrl(type, context) + "?token=" + sharedPreferences.getString("token", "") + "&pageNum=" + page;
        }
        Log.v("pppppppppppp", "" + url);
        eventModel.visitProjects(context, type, eventBean, url, page, this);
    }

    @Override
    public void onSuccess(List<ListEventBean> list) {
        eventView.addEventSuccess(list);
    }

    @Override
    public void onSuccessMes(String msg) {
        eventView.addEventMes(msg);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        eventView.addEventFail(msg);
    }

    private String getUrl(int type, Context context) {
        StringBuffer sburl = new StringBuffer();
        switch (type) {
            case -1:
                sburl.append(Urls.HOST_DRAFT);
                break;
            case 0:
                sburl.append(Urls.HOST_MYEVENT);
                break;
            case 1:
                sburl.append(Urls.HOST_ALLEVENT);
                break;
            case 2:
                sburl.append(Urls.HOST_CREATEEVENT);//创建
                break;
            case 3:
                sburl.append(Urls.HOST_CREATEEVENT);//创建
                break;
        }
        return sburl.toString();
    }
}
