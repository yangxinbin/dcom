package com.mango.leo.dcom.faq.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mango.leo.dcom.event.bean.EventDetailBean;
import com.mango.leo.dcom.faq.bean.FaqDetailBean;
import com.mango.leo.dcom.faq.bean.ListFaqBean;
import com.mango.leo.dcom.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Date   : 15/12/19
 */
public class FaqJsonUtils {

    private final static String TAG = "NewsJsonUtils";

    /**
     * 将获取到的json转换为列表对象
     *
     * @param res
     * @param
     * @return
     */
    public static List<ListFaqBean> readListFaqBean(String res, String va) {
        List<ListFaqBean> beans = new ArrayList<ListFaqBean>();
        try {
            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            //JsonObject ob = jsonObject.getAsJsonObject("responseObject");
            JsonArray jsonArray = jsonObject.getAsJsonArray(va);
            for (int i = 0; i < jsonArray.size(); i++) {
                ListFaqBean news = JsonUtils.deserialize(jsonObject, ListFaqBean.class);
                beans.add(news);//这里会将所有的json对象转换为bean对象
            }
        } catch (Exception e) {
        }
        return beans;
    }
    public static FaqDetailBean readDetailBean(String res) {
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        FaqDetailBean faqDetailBean = JsonUtils.deserialize(jsonObject, FaqDetailBean.class);
        return faqDetailBean;
    }
}
