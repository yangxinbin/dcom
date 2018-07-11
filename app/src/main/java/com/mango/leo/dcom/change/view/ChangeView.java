package com.mango.leo.dcom.change.view;

import com.mango.leo.dcom.change.bean.ChangeBean;

import java.util.List;

/**
 * Created by admin on 2018/7/10.
 */

public interface ChangeView {
    void addChangeSuccess(List<ChangeBean> changeBeans);
    void addChangeMes(String s);
    void addChangeFail(String e);
}
