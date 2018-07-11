package com.mango.leo.dcom.faq.presenter;

import android.content.Context;

import com.mango.leo.dcom.faq.bean.FaqBean;

/**
 * Created by admin on 2018/5/21.
 */

public interface FaqPresenter {
    void visitProjects(Context context, int type, FaqBean faqBean, int page);//刷新动作加载新闻数据
}
