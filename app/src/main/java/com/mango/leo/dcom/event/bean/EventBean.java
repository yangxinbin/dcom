package com.mango.leo.dcom.event.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/10.
 */

public class EventBean {
    private String tag;
    private String title;
    private String occuredOn;
    private String complaintBy;
    private String origin;
    private String type;
    private String priority;
    private String severity;
    private String eventScope;
    private List<String> relatedConfigSNs;
    private String description;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOccuredOn() {
        return occuredOn;
    }

    public void setOccuredOn(String occuredOn) {
        this.occuredOn = occuredOn;
    }

    public String getComplaintBy() {
        return complaintBy;
    }

    public void setComplaintBy(String complaintBy) {
        this.complaintBy = complaintBy;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getEventScope() {
        return eventScope;
    }

    public void setEventScope(String eventScope) {
        this.eventScope = eventScope;
    }

    public List<String> getRelatedConfigSNs() {
        return relatedConfigSNs;
    }

    public void setRelatedConfigSNs(List<String> relatedConfigSNs) {
        this.relatedConfigSNs = relatedConfigSNs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
