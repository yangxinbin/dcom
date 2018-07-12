package com.mango.leo.dcom.event.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mango.leo.dcom.event.bean.EventBean;
import com.mango.leo.dcom.event.bean.ListEventBean;
import com.mango.leo.dcom.event.listener.OnEventListener;
import com.mango.leo.dcom.event.util.EventJsonUtils;
import com.mango.leo.dcom.util.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/5/21.
 */

public class EventModelImpl implements EventModel {
    private SharedPreferences sharedPreferences;

    @Override
    public void visitProjects(final Context context, final int type, EventBean eventBean, String url,int page, final OnEventListener listener) {
        sharedPreferences = context.getSharedPreferences("DCOM", MODE_PRIVATE);
        if (type == 0) {//我的事件
            //Map<String, String> mapParams = new HashMap<>();
            //mapParams.put("token", sharedPreferences.getString("token", ""));
            //mapParams.put("pageNum", String.valueOf(page));
            //mapParams.put("stage", "1");

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
                        List<ListEventBean> beanList = EventJsonUtils.readJsonEventBeans(response.body().string(), "list");//data是json字段获得data的值即对象数组
                        listener.onSuccess(beanList);
                        listener.onSuccessMes("请求成功");
                    } catch (Exception e) {
                        Log.e("yyyyy", "Exception = " + e);
                    }
                }
            });
        }
        if (type == 1) {//全部事件
            Map<String, String> mapParams = new HashMap<>();
            mapParams.put("token", sharedPreferences.getString("token", ""));
            mapParams.put("pageNum", String.valueOf(page));
            //mapParams.put("stage", "1");

            HttpUtils.doPost(url, mapParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    listener.onFailure("FAILURE", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        //Log.v("yyyyyyyyy","*****onResponse******"+response.body().string());
                        //response.body().string() 只能用一次  java.lang.IllegalStateException异常, 该异常表示，当前对客户端的响应已经结束，不能在响应已经结束（或说消亡）后再向客户端（实际上是缓冲区）输出任何内容。
                        List<ListEventBean> beanList = EventJsonUtils.readJsonEventBeans(response.body().string(), "list");//data是json字段获得data的值即对象数组
                        listener.onSuccess(beanList);
                        listener.onSuccessMes("请求成功");
                    } catch (Exception e) {
                        Log.e("yyyyy", "Exception = " + e);
                    }
                }
            });
        }
        if (type == 2) {
            final HashMap<String, String> mapParams = new HashMap<String, String>();
            mapParams.clear();
            mapParams.put("token", sharedPreferences.getString("token", ""));
            mapParams.put("tag", eventBean.getTag());
            mapParams.put("title", eventBean.getTitle());
            mapParams.put("complaintBy", eventBean.getComplaintBy());
            mapParams.put("origin", eventBean.getOrigin());
            mapParams.put("type", eventBean.getType());
            mapParams.put("priority", eventBean.getPriority());
            mapParams.put("severity", eventBean.getSeverity());
            mapParams.put("eventScope", eventBean.getEventScope());
            mapParams.put("relatedConfigSNs", "");//待定
            mapParams.put("description", eventBean.getDescription());
            mapParams.put("publish", "false");
            HttpUtils.doPostAll(url, mapParams,eventBean.getFile(),new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("doPostAll", "^^^^^onFailure^^^^^");
                    listener.onFailure("事件保存失败", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        listener.onSuccessMes("事件保存成功");//异步请求
                    } else {
                        Log.v("doPostAll", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccessMes("事件保存失败");
                    }
                }
            });
        }
        if (type == 3) {
            final HashMap<String, String> mapParams = new HashMap<String, String>();
            mapParams.clear();
            mapParams.put("token", sharedPreferences.getString("token", ""));
            mapParams.put("tag", eventBean.getTag());
            mapParams.put("title", eventBean.getTitle());
            mapParams.put("complaintBy", eventBean.getComplaintBy());
            mapParams.put("origin", eventBean.getOrigin());
            mapParams.put("type", eventBean.getType());
            mapParams.put("priority", eventBean.getPriority());
            mapParams.put("severity", eventBean.getSeverity());
            mapParams.put("eventScope", eventBean.getEventScope());
            mapParams.put("relatedConfigSNs", "");//待定
            mapParams.put("description", eventBean.getDescription());
            mapParams.put("publish", "true");
            HttpUtils.doPostAll(url, mapParams,eventBean.getFile(),new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("doPostAll", "^^^^^onFailure^^^^^");
                    listener.onFailure("事件保存失败", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        listener.onSuccessMes("事件保存成功");//异步请求
                    } else {
                        Log.v("doPostAll", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccessMes("事件保存失败");
                    }
                }
            });
        }
    }

}
