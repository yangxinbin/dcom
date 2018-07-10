package com.mango.leo.dcom.event.presenter;

import android.content.Context;

import com.mango.leo.dcom.event.bean.EventBean;

/**
 * Created by admin on 2018/5/21.
 */

public interface EventPresenter {
    void visitProjects(Context context, int type, EventBean eventBean, int page);//刷新动作加载新闻数据
}
