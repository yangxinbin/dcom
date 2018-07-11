package com.mango.leo.dcom.change.listener;

import com.mango.leo.dcom.change.bean.ChangeBean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public interface OnChangeListener {
    void onSuccess(List<ChangeBean> list);
    void onSuccessMes(String msg);
    void onFailure(String msg, Exception e);
}
