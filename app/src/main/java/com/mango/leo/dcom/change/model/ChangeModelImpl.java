package com.mango.leo.dcom.change.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mango.leo.dcom.change.bean.ChangeBean;
import com.mango.leo.dcom.change.bean.ListChangeBean;
import com.mango.leo.dcom.change.bean.MethodBeans;
import com.mango.leo.dcom.change.bean.RevertBeans;
import com.mango.leo.dcom.change.listener.OnChangeListener;
import com.mango.leo.dcom.change.util.ChangeJsonUtils;
import com.mango.leo.dcom.util.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
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

public class ChangeModelImpl implements ChangeModel {
    private SharedPreferences sharedPreferences;

    @Override
    public void visitProjects(final Context context, final int type, ChangeBean changeBean, String url, final OnChangeListener listener) {
        sharedPreferences = context.getSharedPreferences("DCOM", MODE_PRIVATE);
        if (type == -1) {//草稿箱
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
                        List<ListChangeBean> beanList = ChangeJsonUtils.readJsonListChangeBean(response.body().string(), "list");//data是json字段获得data的值即对象数组
                        listener.onSuccess(beanList);
                        Log.v("yyyyyyyyy",response.code()+"*****onResponse******"+beanList.size());
                        //listener.onSuccessMes("请求成功");
                    } catch (Exception e) {
                       // Log.v("yyyyyyyyy",response.code()+"*****onResponse******"+response.body().string());
                        Log.v("yyyyyyyyy",response.code()+"*****Exception******");
                        listener.onSuccessMes("请求失败");
                    }
                }
            });
        }
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
                        List<ListChangeBean> beanList = ChangeJsonUtils.readJsonListChangeBean(response.body().string(), "list");//data是json字段获得data的值即对象数组
                        listener.onSuccess(beanList);
                        //listener.onSuccessMes("请求成功");
                    } catch (Exception e) {
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
                        //response.body().string() 只能用一次  java.lang.IllegalStateException异常, 该异常表示，当前对客户端的响应已经结束，不能在响应已经结束（或说消亡）后再向客户端（实际上是缓冲区）输出任何内容。
                        List<ListChangeBean> beanList = ChangeJsonUtils.readJsonListChangeBean(response.body().string(), "list");//data是json字段获得data的值即对象数组
                        listener.onSuccess(beanList);
                        //listener.onSuccessMes("请求成功");
                    } catch (Exception e) {
                        Log.e("yyyyy", "Exception = " + e);
                        listener.onSuccessMes("请求失败");
                    }
                }
            });
        }
        if (type == 2) {
            final HashMap<String, String> mapParams = new HashMap<String, String>();
            mapParams.clear();
            if (changeBean == null)
                return;
            mapParams.put("token", sharedPreferences.getString("token", ""));
            mapParams.put("tag", changeBean.getTag() != null ? changeBean.getTag() : "");
            mapParams.put("title", changeBean.getTitle() != null ? changeBean.getTitle() : "");
            mapParams.put("planningTime", changeBean.getPlanningTime() != null ? changeBean.getPlanningTime() : "");
            mapParams.put("deadline", changeBean.getDeadline() != null ? changeBean.getDeadline() : "");
            mapParams.put("type", changeBean.getType() != null ? changeBean.getType() : "");
            mapParams.put("oaNumber", changeBean.getOaNumber() != null ? changeBean.getOaNumber() : "");
            mapParams.put("impactScope", changeBean.getImpactScope() != null ? changeBean.getImpactScope() : "");
            mapParams.put("impactLevel", changeBean.getImpactLevel() != null ? changeBean.getImpactLevel() : "");
            mapParams.put("riskLevel", changeBean.getRiskLevel() != null ? changeBean.getRiskLevel() : "");
            mapParams.put("relatedConfigSNs", changeBean.getRelatedConfigSNs() != null && changeBean.getRelatedConfigSNs().size() != 0 ? listToString(changeBean.getRelatedConfigSNs()) : "");//待定
            mapParams.put("relatedEventTags", changeBean.getRelatedEventTags() != null && changeBean.getRelatedEventTags().size() != 0 ? listToString(changeBean.getRelatedEventTags()) : "");//待定
            mapParams.put("relatedProblemTags", changeBean.getRelatedProblemTags() != null && changeBean.getRelatedProblemTags().size() != 0 ? listToString(changeBean.getRelatedProblemTags()) : "");//待定
            mapParams.put("relatedChangeTags", changeBean.getRelatedChangeTags() != null && changeBean.getRelatedChangeTags().size() != 0 ? listToString(changeBean.getRelatedChangeTags()) : "");//待定
            mapParams.put("cause", changeBean.getCause() != null ? changeBean.getCause() : "");
            mapParams.put("content", changeBean.getContent() != null ? changeBean.getContent() : "");
            //mapParams.put("solutions", changeBean.getSolutions() != null ? buildArrayJson_Method(changeBean.getSolutions()) : "");
            //mapParams.put("planBSolutions", changeBean.getPlanBSolutions() != null ? buildArrayJson_Revert(changeBean.getPlanBSolutions()) : "");
            mapParams.put("publish", "false");
            try {
                Log.v("pppppppppp",getFileSize(changeBean.getFile())+"--"+getFileSize(changeBean.getSolutionAttachment()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            HttpUtils.doPostAll(url, mapParams, changeBean.getFile()/*,changeBean.getSolutionAttachment()*/, new Callback() {
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
            mapParams.put("tag", changeBean.getTag() != null ? changeBean.getTag() : "");
            mapParams.put("title", changeBean.getTitle() != null ? changeBean.getTitle() : "");
            mapParams.put("planningTime", changeBean.getPlanningTime() != null ? changeBean.getPlanningTime() : "");
            mapParams.put("deadline", changeBean.getDeadline() != null ? changeBean.getDeadline() : "");
            mapParams.put("type", changeBean.getType() != null ? changeBean.getType() : "");
            mapParams.put("oaNumber", changeBean.getOaNumber() != null ? changeBean.getOaNumber() : "");
            mapParams.put("impactScope", changeBean.getImpactScope() != null ? changeBean.getImpactScope() : "");
            mapParams.put("impactLevel", changeBean.getImpactLevel() != null ? changeBean.getImpactLevel() : "");
            mapParams.put("riskLevel", changeBean.getRiskLevel() != null ? changeBean.getRiskLevel() : "");
            mapParams.put("relatedConfigSNs", changeBean.getRelatedConfigSNs() != null && changeBean.getRelatedConfigSNs().size() != 0 ? listToString(changeBean.getRelatedConfigSNs()) : "");//待定
            mapParams.put("relatedEventTags", changeBean.getRelatedEventTags() != null && changeBean.getRelatedEventTags().size() != 0 ? listToString(changeBean.getRelatedEventTags()) : "");//待定
            mapParams.put("relatedProblemTags", changeBean.getRelatedProblemTags() != null && changeBean.getRelatedProblemTags().size() != 0 ? listToString(changeBean.getRelatedProblemTags()) : "");//待定
            mapParams.put("relatedChangeTags", changeBean.getRelatedChangeTags() != null && changeBean.getRelatedChangeTags().size() != 0 ? listToString(changeBean.getRelatedChangeTags()) : "");//待定
            mapParams.put("cause", changeBean.getCause() != null ? changeBean.getCause() : "");
            mapParams.put("content", changeBean.getContent() != null ? changeBean.getContent() : "");
            //mapParams.put("solutions", changeBean.getSolutions() != null ? buildArrayJson_Method(changeBean.getSolutions()) : "");
            //mapParams.put("planBSolutions", changeBean.getPlanBSolutions() != null ? buildArrayJson_Revert(changeBean.getPlanBSolutions()) : "");
            mapParams.put("publish", "true");
            HttpUtils.doPostAll(url, mapParams, changeBean.getFile()/*,changeBean.getSolutionAttachment()*/, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("doPostAll", "^^^^^onFailure^^^^^");
                    listener.onFailure("变更保存失败", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        listener.onSuccessMes("SUCCESS");//异步请求
                    } else {
                        Log.v("doPostAll1111", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccessMes("FAILURE");
                    }
                }
            });
        }
    }
    public String buildArrayJson_Method(MethodBeans methodBeans) {
        JSONArray json = new JSONArray();
        try {
            for (int i = 0; i < methodBeans.getMethodItems().size(); i++) {
                JSONObject jsonObj = new JSONObject();//一定要new对象
                jsonObj.put("step", methodBeans.getMethodItems().get(i).getStep()+"");
                jsonObj.put("detail", methodBeans.getMethodItems().get(i).getDetail().toString());
                json.put(i, jsonObj);
                continue;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //把每个数据当作一对象添加到数组里
        Log.v("uuuuuuuu","-m---"+json.toString());
        return json.toString();
    }
    public String buildArrayJson_Revert(RevertBeans revertBeans) {
        JSONArray json = new JSONArray();
        try {
            for (int i = 0; i < revertBeans.getRevertItems().size(); i++) {
                JSONObject jsonObj = new JSONObject();//一定要new对象
                jsonObj.put("step", revertBeans.getRevertItems().get(i).getStep()+"");
                jsonObj.put("detail", revertBeans.getRevertItems().get(i).getDetail().toString());
                json.put(i, jsonObj);
                continue;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //把每个数据当作一对象添加到数组里
        Log.v("uuuuuuuu","-r---"+json.toString());
        return json.toString();
    }
    private String listToString(List<String> stringList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < stringList.size(); i++) {
            if (i == stringList.size() - 1) {
                stringBuffer.append(stringList.get(i));
                break;
            }
            stringBuffer.append(stringList.get(i) + ",");
        }
        Log.v("uuuuuuuu","----"+stringBuffer.toString());
        return stringBuffer.toString();
    }
    public long getFileSize(File f) throws Exception{


        long l = 0;
        if (f.exists()){

            FileInputStream mFIS = new FileInputStream(f);

            l= mFIS.available();

        } else {

            f.createNewFile();

        }

        return l;

    }
}
