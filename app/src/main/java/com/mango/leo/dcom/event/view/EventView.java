package com.mango.leo.dcom.event.view;

import com.mango.leo.dcom.event.bean.EventBean;

import java.util.List;

/**
 * Created by admin on 2018/7/10.
 */

public interface EventView {
    void addEventSuccess(List<EventBean> eventBeans);
    void addEventMes(String s);
    void addEventFail(String e);
}
