package com.mango.leo.dcom.event.model;

import android.content.Context;
import android.util.Log;

import com.mango.leo.dcom.event.bean.EventBean;
import com.mango.leo.dcom.event.listener.OnEventListener;
import com.mango.leo.dcom.event.util.EventJsonUtils;
import com.mango.leo.dcom.util.HttpUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/5/21.
 */

public class EventModelImpl implements EventModel {
    @Override
    public void visitProjects(final Context context, final int type,EventBean eventBean, String url, final OnEventListener listener) {

        HttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onFailure("FAILURE", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    //Log.v("yyyyyyyyy","*****onResponse******"+response.body().string());
                    //response.body().string() 只能用一次  java.lang.IllegalStateException异常, 该异常表示，当前对客户端的响应已经结束，不能在响应已经结束（或说消亡）后再向客户端（实际上是缓冲区）输出任何内容。
                    List<EventBean> beanList = EventJsonUtils.readJsonNewsBeans(response.body().string(), "content");//data是json字段获得data的值即对象数组
                    //listener.onSuccess(beanList);
                    //listener.onSuccessMes("保存成功");
                } catch (Exception e) {
                    Log.e("yyyyy", "Exception = " + e);
                }
            }
        });
    }

}
