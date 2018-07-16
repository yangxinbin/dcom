package com.mango.leo.dcom.faq.view;

import com.mango.leo.dcom.faq.bean.FaqBean;
import com.mango.leo.dcom.faq.bean.ListFaqBean;

import java.util.List;

/**
 * Created by admin on 2018/7/10.
 */

public interface FaqView {
    void addFaqSuccess(List<ListFaqBean> faqBeans);
    void addFaqMes(String s);
    void addFaqFail(String e);
}
