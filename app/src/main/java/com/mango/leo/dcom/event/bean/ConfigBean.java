package com.mango.leo.dcom.event.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/12.
 */

public class ConfigBean {

    /**
     * content : [{"type":"id_type","value":"[\"护照\",\"身份证\"]"},{"type":"asset_type","value":"[\"使用中\",\"上电\",\"下电\",\"使用中\",\"维护\"]"},{"type":"other_type","value":"[\"路由器\",\"NAT\",\"SAN\"]"},{"type":"license_type","value":"[\"GNU\",\"EULA\",\"工作地点\",\"永续\",\"非永续\"]"},{"type":"access_reason","value":"[\"人员出入申请\",\"机柜上电申请\",\"机柜下电申请\",\"设备上架申请\",\"设备下架申请\",\"设备寄存入库申请\",\"设备寄存出库申请\",\"机房公共区域布线申请\",\"施工申请\"]"},{"type":"event_origin","value":"[\"服务中心\",\"用户报告\",\"问题监控\",\"内部开单\"]"},{"type":"event_type","value":"[\"故障\",\"服务请求\"]"},{"type":"event_level","value":"5"},{"type":"event_severity","value":"[\"高严重\",\"严重\",\"高\",\"中\",\"低\"]"},{"type":"pro_origin","value":"[\"事件\",\"维护\",\"趋势分析\"]"},{"type":"pro_priority","value":"[\"关键问题\",\"重要问题\"]"},{"type":"pro_class","value":"[\"交换机\",\"防火墙\",\"数据库\",\"中间件\"]"},{"type":"pro_system","value":"[\"网络\",\"高性能计算\"]"},{"type":"change_type","value":"[\"标准变更\",\"紧急变更\",\"例行变更\"]"},{"type":"change_impact_level","value":"[\"严重\",\"高\",\"中\",\"低\",\"无\"]"},{"type":"change_risk_level","value":"[\"高\",\"中\",\"低\"]"},{"type":"release_test_status","value":"[\"A\",\"B\",\"C\"]"}]
     * totalElements : 17
     * totalPages : 1
     * last : true
     * number : 0
     * size : 0
     * sort : null
     * first : true
     * numberOfElements : 17
     */

    private int totalElements;
    private int totalPages;
    private boolean last;
    private int number;
    private int size;
    private Object sort;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
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
    }
}
