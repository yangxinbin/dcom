package com.mango.leo.dcom.change.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mango.leo.dcom.change.bean.ListChangeBean;
import com.mango.leo.dcom.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Date   : 15/12/19
 */
public class ChangeJsonUtils {

    private final static String TAG = "NewsJsonUtils";

    /**
     * 将获取到的json转换为列表对象
     *
     * @param res
     * @param
     * @return
     */
    public static List<ListChangeBean> readJsonListChangeBean(String res, String va) {
        List<ListChangeBean> beans = new ArrayList<ListChangeBean>();
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        //JsonObject ob = jsonObject.getAsJsonObject("responseObject");
        JsonArray jsonArray = jsonObject.getAsJsonArray(va);
        for (int i = 0; i < jsonArray.size(); i++) {
            ListChangeBean news = JsonUtils.deserialize(jsonObject, ListChangeBean.class);
            beans.add(news);//这里会将所有的json对象转换为bean对象
        }
        return beans;
    }
}
