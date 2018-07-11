package com.mango.leo.dcom.event.model;

import android.content.Context;

import com.mango.leo.dcom.event.bean.EventBean;
import com.mango.leo.dcom.event.listener.OnEventListener;

/**
 * Created by admin on 2018/5/21.
 */

public interface EventModel {
    void visitProjects(Context context, int type, EventBean eventBean,String url,int page, OnEventListener listener);//刷新动作加载新闻数据
}
