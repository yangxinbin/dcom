package com.mango.leo.dcom.event.bean;

/**
 * Created by admin on 2018/7/12.
 */

public class ConfigBean {

    /**
     * type : id_type
     * value : ["护照","身份证"]
     */

    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
