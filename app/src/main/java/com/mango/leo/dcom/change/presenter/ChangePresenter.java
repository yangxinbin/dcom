package com.mango.leo.dcom.change.presenter;

import android.content.Context;

import com.mango.leo.dcom.change.bean.ChangeBean;

/**
 * Created by admin on 2018/5/21.
 */

public interface ChangePresenter {
    void visitProjects(Context context, int type, ChangeBean changeBean, int page);//刷新动作加载新闻数据
}
