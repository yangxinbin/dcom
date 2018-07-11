package com.mango.leo.dcom.change.model;

import android.content.Context;

import com.mango.leo.dcom.change.bean.ChangeBean;
import com.mango.leo.dcom.change.listener.OnChangeListener;

/**
 * Created by admin on 2018/5/21.
 */

public interface ChangeModel {
    void visitProjects(Context context, int type, ChangeBean faqBean, String url, OnChangeListener listener);//刷新动作加载新闻数据
}
