package com.mango.leo.dcom.faq.model;

import android.content.Context;

import com.mango.leo.dcom.faq.bean.FaqBean;
import com.mango.leo.dcom.faq.listener.OnFaqListener;

/**
 * Created by admin on 2018/5/21.
 */

public interface FaqModel {
    void visitProjects(Context context, int type, FaqBean faqBean, String url, OnFaqListener listener);//刷新动作加载新闻数据
}
