package com.mango.leo.dcom.change.bean;

import java.io.File;
import java.util.List;

/**
 * Created by admin on 2018/7/10.
 */

public class ChangeBean {
    private String tag;
    private String title;
    private String occurredOn;
    private String deadline;
    private String origin;
    private String priority;
    private String classification;
    private String system;
    private List<String> relatedEventTags;
    private List<String> relatedChangeTags;
    private List<String> relatedConfigSNs;
    private String description;
    private File file;

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

    public String getOccurredOn() {
        return occurredOn;
    }

    public void setOccurredOn(String occurredOn) {
        this.occurredOn = occurredOn;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public List<String> getRelatedEventTags() {
        return relatedEventTags;
    }

    public void setRelatedEventTags(List<String> relatedEventTags) {
        this.relatedEventTags = relatedEventTags;
    }

    public List<String> getRelatedChangeTags() {
        return relatedChangeTags;
    }

    public void setRelatedChangeTags(List<String> relatedChangeTags) {
        this.relatedChangeTags = relatedChangeTags;
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "FaqBean{" +
                "tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", occurredOn='" + occurredOn + '\'' +
                ", deadline='" + deadline + '\'' +
                ", origin='" + origin + '\'' +
                ", priority='" + priority + '\'' +
                ", classification='" + classification + '\'' +
                ", system='" + system + '\'' +
                ", relatedEventTags=" + relatedEventTags +
                ", relatedChangeTags=" + relatedChangeTags +
                ", relatedConfigSNs=" + relatedConfigSNs +
                ", description='" + description + '\'' +
                ", file=" + file +
                '}';
    }
}
