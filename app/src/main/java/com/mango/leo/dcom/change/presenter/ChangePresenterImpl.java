package com.mango.leo.dcom.change.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mango.leo.dcom.change.bean.ChangeBean;
import com.mango.leo.dcom.change.bean.ListChangeBean;
import com.mango.leo.dcom.change.listener.OnChangeListener;
import com.mango.leo.dcom.change.model.ChangeModelImpl;
import com.mango.leo.dcom.change.model.ChangeModel;
import com.mango.leo.dcom.change.view.ChangeView;
import com.mango.leo.dcom.faq.view.FaqView;
import com.mango.leo.dcom.util.Urls;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/5/21.
 */

public class ChangePresenterImpl implements ChangePresenter, OnChangeListener {
    private ChangeView changeView;
    private ChangeModel changeModel;
    private SharedPreferences sharedPreferences;

    public ChangePresenterImpl(ChangeView u) {
        this.changeView = u;
        this.changeModel = new ChangeModelImpl();
    }

    @Override
    public void visitProjects(Context context, int type, ChangeBean changeBean, int page) {
        sharedPreferences = context.getSharedPreferences("DCOM", MODE_PRIVATE);
        String url = null;
        if (type == 0) {
            url = getUrl(type, context) + "?token=" + sharedPreferences.getString("token", "") + "&page=" + page;
        } else if (type == 1) {
            url = getUrl(type, context) + "?token=" + sharedPreferences.getString("token", "") + "&page=" + page;
        } else if (type == 2) {
            url = getUrl(type, context);
        } else if (type == 3) {
            url = getUrl(type, context);
        }
        Log.v("pppppppppppp", "" + url);
        changeModel.visitProjects(context, type, changeBean,url, this);
    }

    @Override
    public void onSuccess(List<ListChangeBean> list) {
        changeView.addChangeSuccess(list);
    }

    @Override
    public void onSuccessMes(String msg) {
        changeView.addChangeMes(msg);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        changeView.addChangeFail(msg);
    }

    private String getUrl(int type, Context context) {
        StringBuffer sburl = new StringBuffer();
        switch (type) {
            case 0:
                sburl.append(Urls.HOST_MYCHANGE);
                break;
            case 1:
                sburl.append(Urls.HOST_ALLCHANGE);
                break;
            case 2:
                sburl.append(Urls.HOST_CREATECHANGE);//创建
                break;
            case 3:
                sburl.append(Urls.HOST_CREATECHANGE);//创建
                break;
        }
        return sburl.toString();
    }
}
