package com.mango.leo.dcom.faq.view;

import com.mango.leo.dcom.faq.bean.FaqBean;

import java.util.List;

/**
 * Created by admin on 2018/7/10.
 */

public interface FaqView {
    void addFaqSuccess(List<FaqBean> eventBeans);
    void addFaqMes(String s);
    void addFaqFail(String e);
}
