package com.mango.leo.dcom.event.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mango.leo.dcom.event.bean.ConfigBean;
import com.mango.leo.dcom.event.bean.EventDetailBean;
import com.mango.leo.dcom.event.bean.ListEventBean;
import com.mango.leo.dcom.event.bean.PeopleBean;
import com.mango.leo.dcom.event.bean.TeamBean;
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
    public static List<ListEventBean> readJsonEventBeans(String res, String va) {
        List<ListEventBean> beans = new ArrayList<ListEventBean>();
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        //JsonObject ob = jsonObject.getAsJsonObject("responseObject");
        JsonArray jsonArray = jsonObject.getAsJsonArray(va);
        for (int i = 0; i < jsonArray.size(); i++) {
            ListEventBean news = JsonUtils.deserialize(jsonObject, ListEventBean.class);
            beans.add(news);//这里会将所有的json对象转换为bean对象
        }
        return beans;
    }

    public static List<ConfigBean> readJsonConfigBean(String res,String va) {
        List<ConfigBean> beans = new ArrayList<ConfigBean>();
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        //JsonObject ob = jsonObject.getAsJsonObject("responseObject");
        JsonArray jsonArray = jsonObject.getAsJsonArray(va);
        for (int i = 0; i < jsonArray.size(); i++) {
            ConfigBean bean = JsonUtils.deserialize(jsonObject, ConfigBean.class);
            beans.add(bean);
        }
        return beans;
    }
    public static EventDetailBean readDetailBean(String res) {
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        EventDetailBean eventDetailBean = JsonUtils.deserialize(jsonObject, EventDetailBean.class);
        return eventDetailBean;
    }
    public static List<TeamBean> readTeamBean(String res) {
/*        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        TeamBean teamBean = JsonUtils.deserialize(jsonObject, TeamBean.class);
        return teamBean;*/
        Gson gson = new Gson();
        List<TeamBean> teamBeans = gson.fromJson(res, new TypeToken<List<TeamBean>>(){}.getType());
        return teamBeans;
    }
    public static List<PeopleBean> readPeopleBean(String res) {
/*        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        TeamBean teamBean = JsonUtils.deserialize(jsonObject, TeamBean.class);
        return teamBean;*/
        Gson gson = new Gson();
        List<PeopleBean> peopleBeans = gson.fromJson(res, new TypeToken<List<PeopleBean>>(){}.getType());
        return peopleBeans;
    }
}
