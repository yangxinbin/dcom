package com.mango.leo.dcom.util;

import android.content.Context;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mango.leo.dcom.login.bean.UserMessageBean;


/**
 * Description :
 * Date   : 15/12/19
 */
public class ProjectsJsonUtils {

    private final static String TAG = "NewsJsonUtils";

    /**
     * 将获取到的json转换为列表对象
     *
     * @param res
     * @param
     * @return
     */
/*    public static List<AllProjectsBean> readJsonNewsBeans(String res, String va) {
        List<AllProjectsBean> beans = new ArrayList<AllProjectsBean>();
        try {
            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            JsonObject ob = jsonObject.getAsJsonObject("responseObject");
            JsonArray jsonArray = ob.getAsJsonArray(va);
            for (int i = 0; i < jsonArray.size(); i++) {
                AllProjectsBean news = JsonUtils.deserialize(jsonObject, AllProjectsBean.class);
                beans.add(news);//这里会将所有的json对象转换为bean对象
            }
        } catch (Exception e) {
        }
        return beans;
    }*/

    public static UserMessageBean readJsonUserMessageBeans(String res, Context context) {
        ACache.get(context).put("message", res);
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        //JsonObject ob = jsonObject.getAsJsonObject("responseObject");
        UserMessageBean bean = JsonUtils.deserialize(jsonObject, UserMessageBean.class);
        return bean;
    }
}
