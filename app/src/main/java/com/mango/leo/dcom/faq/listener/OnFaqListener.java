package com.mango.leo.dcom.faq.listener;

import com.mango.leo.dcom.faq.bean.FaqBean;
import com.mango.leo.dcom.faq.bean.ListFaqBean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public interface OnFaqListener {
    void onSuccess(List<ListFaqBean> list);
    void onSuccessMes(String msg);
    void onFailure(String msg, Exception e);
}
