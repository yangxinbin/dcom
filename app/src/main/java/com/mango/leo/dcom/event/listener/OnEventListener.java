package com.mango.leo.dcom.event.listener;

import com.mango.leo.dcom.event.bean.EventBean;
import com.mango.leo.dcom.event.bean.ListEventBean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public interface OnEventListener {
    void onSuccess(List<ListEventBean> list);
    void onSuccessMes(String msg);
    void onFailure(String msg, Exception e);
}
