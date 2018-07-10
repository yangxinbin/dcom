package com.mango.leo.dcom.event.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mango.leo.dcom.event.bean.EventBean;
import com.mango.leo.dcom.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Date   : 15/12/19
 */
public class EventJsonUtils {

    private final static String TAG = "NewsJsonUtils";

    /**
     * 将获取到的json转换为列表对象
     *
     * @param res
     * @param
     * @return
     */
    public static List<EventBean> readJsonNewsBeans(String res, String va) {
        List<EventBean> beans = new ArrayList<EventBean>();
        try {
            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            JsonObject ob = jsonObject.getAsJsonObject("responseObject");
            JsonArray jsonArray = ob.getAsJsonArray(va);
            for (int i = 0; i < jsonArray.size(); i++) {
                EventBean news = JsonUtils.deserialize(jsonObject, EventBean.class);
                beans.add(news);//这里会将所有的json对象转换为bean对象
            }
        } catch (Exception e) {
        }
        return beans;
    }
}
