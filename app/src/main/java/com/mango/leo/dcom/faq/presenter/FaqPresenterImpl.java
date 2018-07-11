package com.mango.leo.dcom.faq.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mango.leo.dcom.faq.bean.FaqBean;
import com.mango.leo.dcom.faq.listener.OnFaqListener;
import com.mango.leo.dcom.faq.model.FaqModel;
import com.mango.leo.dcom.faq.model.FaqModelImpl;
import com.mango.leo.dcom.faq.view.FaqView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/5/21.
 */

public class FaqPresenterImpl implements FaqPresenter, OnFaqListener {
    private FaqView faqView;
    private FaqModel faqModel;
    private SharedPreferences sharedPreferences;

    public FaqPresenterImpl(FaqView u) {
        this.faqView = u;
        this.faqModel = new FaqModelImpl();
    }

    @Override
    public void visitProjects(Context context, int type, FaqBean faqBean, int page) {
        sharedPreferences = context.getSharedPreferences("CIFIT", MODE_PRIVATE);
        String url = null;
        if (type == 0) {
            url = getUrl(type, context) + "?token=" + sharedPreferences.getString("token", "") + "&stage=" + 1 + "&page=" + page;
        } else if (type == 1) {
            url = getUrl(type, context) + "?token=" + sharedPreferences.getString("token", "") + "&stage=" + 0 + "&page=" + page;
        } else if (type == 2) {
            url = getUrl(type, context) + "?token=" + sharedPreferences.getString("token", "") + "&stage=" + 2 + "&page=" + page;
        }
        Log.v("pppppppppppp", "" + url);
        faqModel.visitProjects(context, type, faqBean,url, this);
    }

    @Override
    public void onSuccess(List<FaqBean> list) {
        faqView.addFaqSuccess(list);
    }

    @Override
    public void onSuccessMes(String msg) {
        faqView.addFaqMes(msg);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        faqView.addFaqFail(msg);
    }

    private String getUrl(int type, Context context) {
        StringBuffer sburl = new StringBuffer();
        switch (type) {
            case 0:
                break;
            case 1:
                //sburl.append(Urls.HOST_PROJECT_BUSSINESSLIST);
                break;
            case 2:
               // sburl.append(Urls.HOST_PROJECT_BUSSINESSLIST);//已审核
                break;
        }
        return sburl.toString();
    }
}