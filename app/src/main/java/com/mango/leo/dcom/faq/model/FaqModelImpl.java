package com.mango.leo.dcom.faq.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mango.leo.dcom.event.bean.ListEventBean;
import com.mango.leo.dcom.event.util.EventJsonUtils;
import com.mango.leo.dcom.faq.bean.FaqBean;
import com.mango.leo.dcom.faq.bean.ListFaqBean;
import com.mango.leo.dcom.faq.listener.OnFaqListener;
import com.mango.leo.dcom.faq.util.FaqJsonUtils;
import com.mango.leo.dcom.util.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/5/21.
 */

public class FaqModelImpl implements FaqModel {
    private SharedPreferences sharedPreferences;

    @Override
    public void visitProjects(final Context context, final int type, FaqBean faqBean, String url, final OnFaqListener listener) {
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
                        List<ListFaqBean> beanList = FaqJsonUtils.readListFaqBean(response.body().string(), "list");//data是json字段获得data的值即对象数组
                        listener.onSuccess(beanList);
                        //listener.onSuccessMes("请求成功");
                    } catch (Exception e) {
                        Log.v("doPostAll", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccessMes("请求失败");
                    }
                }
            });
        }
        if (type == 1) {//全部事件
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
                        List<ListFaqBean> beanList = FaqJsonUtils.readListFaqBean(response.body().string(), "list");//data是json字段获得data的值即对象数组
                        listener.onSuccess(beanList);
                        //listener.onSuccessMes("请求成功");
                    } catch (Exception e) {
                        Log.v("doPostAll", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccessMes("请求失败");
                    }
                }
            });
        }
        if (type == 2) {
            final HashMap<String, String> mapParams = new HashMap<String, String>();
            mapParams.clear();
            if (faqBean == null)
                return;
            mapParams.put("token", sharedPreferences.getString("token", ""));
            mapParams.put("tag", faqBean.getTag() != null ?faqBean.getTag():"");
            mapParams.put("title", faqBean.getTitle() != null ? faqBean.getTitle():"");
            mapParams.put("occurredOn", faqBean.getOccurredOn() != null ? faqBean.getOccurredOn():"");
            mapParams.put("deadline", faqBean.getDeadline() != null ? faqBean.getDeadline():"");
            mapParams.put("origin", faqBean.getOrigin() != null ?faqBean.getOrigin():"");
            mapParams.put("classification", faqBean.getClassification() != null ? faqBean.getClassification():"");
            mapParams.put("priority", faqBean.getPriority() != null? faqBean.getPriority():"");
            mapParams.put("system", faqBean.getSystem() != null? faqBean.getSystem():"");
            mapParams.put("relatedConfigSNs", faqBean.getRelatedConfigSNs()!=null&&faqBean.getRelatedConfigSNs().size() != 0?listToString(faqBean.getRelatedConfigSNs()):"");//待定
            mapParams.put("relatedEventTags", faqBean.getRelatedEventTags()!=null&&faqBean.getRelatedEventTags().size() != 0?listToString(faqBean.getRelatedEventTags()):"");//待定
            mapParams.put("relatedProblemTags", "");//待定
            mapParams.put("relatedChangeTags", faqBean.getRelatedChangeTags()!=null&&faqBean.getRelatedChangeTags().size() != 0?listToString(faqBean.getRelatedChangeTags()):"");//待定
            mapParams.put("description", faqBean.getDescription() != null? faqBean.getDescription():"");
            mapParams.put("publish", "false");
            HttpUtils.doPostAll(url, mapParams,faqBean.getFile(),new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("doPostAll", "^^^^^onFailure^^^^^");
                    listener.onFailure("事件保存失败", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        listener.onSuccessMes("SUCCESS");//异步请求
                    } else {
                        Log.v("doPostAll", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccessMes("FAILURE");
                    }
                }
            });
        }
        if (type == 3) {
            final HashMap<String, String> mapParams = new HashMap<String, String>();
            mapParams.clear();
            mapParams.put("token", sharedPreferences.getString("token", ""));
            mapParams.put("tag", faqBean.getTag() != null ?faqBean.getTag():"");
            mapParams.put("title", faqBean.getTitle() != null ? faqBean.getTitle():"");
            mapParams.put("occurredOn", faqBean.getOccurredOn() != null ? faqBean.getOccurredOn():"");
            mapParams.put("deadline", faqBean.getDeadline() != null ? faqBean.getDeadline():"");
            mapParams.put("origin", faqBean.getOrigin() != null ?faqBean.getOrigin():"");
            mapParams.put("classification", faqBean.getClassification() != null ? faqBean.getClassification():"");
            mapParams.put("priority", faqBean.getPriority() != null? faqBean.getPriority():"");
            mapParams.put("system", faqBean.getSystem() != null? faqBean.getSystem():"");
            mapParams.put("relatedConfigSNs", faqBean.getRelatedConfigSNs().size() != 0?listToString(faqBean.getRelatedConfigSNs()):"");//待定
            mapParams.put("relatedEventTags", faqBean.getRelatedEventTags().size() != 0?listToString(faqBean.getRelatedEventTags()):"");//待定
            mapParams.put("relatedProblemTags", "");//待定
            mapParams.put("relatedChangeTags", faqBean.getRelatedChangeTags().size() != 0?listToString(faqBean.getRelatedChangeTags()):"");//待定
            mapParams.put("description", faqBean.getDescription() != null? faqBean.getDescription():"");
            mapParams.put("publish", "true");
            HttpUtils.doPostAll(url, mapParams,faqBean.getFile(),new Callback() {
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
    private String listToString(List<String> stringList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i<stringList.size();i++){
            if (i == stringList.size()-1){
                stringBuffer.append(stringList.get(i));
                break;
            }
            stringBuffer.append(stringList.get(i)+",");
        }
        return stringBuffer.toString();
    }
}
