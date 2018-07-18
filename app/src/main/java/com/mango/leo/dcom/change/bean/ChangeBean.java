package com.mango.leo.dcom.change.bean;

import java.io.File;
import java.util.List;

/**
 * Created by admin on 2018/7/10.
 */

public class ChangeBean {
    private String tag;
    private String title;
    private String planningTime;
    private String deadline;

    private String oaNumber;
    private String type;
    private String impactScope;
    private String impactLevel;
    private String riskLevel;

    private List<String> relatedEventTags;
    private List<String> relatedProblemTags;
    private List<String> relatedChangeTags;
    private List<String> relatedConfigSNs;

    private String cause;
    private String content;
    private File file;
    private String solutions;
    private File solutionAttachment;
    private String planBSolutions;

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

    public String getPlanningTime() {
        return planningTime;
    }

    public void setPlanningTime(String planningTime) {
        this.planningTime = planningTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getOaNumber() {
        return oaNumber;
    }

    public void setOaNumber(String oaNumber) {
        this.oaNumber = oaNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImpactScope() {
        return impactScope;
    }

    public void setImpactScope(String impactScope) {
        this.impactScope = impactScope;
    }

    public String getImpactLevel() {
        return impactLevel;
    }

    public void setImpactLevel(String impactLevel) {
        this.impactLevel = impactLevel;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public List<String> getRelatedEventTags() {
        return relatedEventTags;
    }

    public void setRelatedEventTags(List<String> relatedEventTags) {
        this.relatedEventTags = relatedEventTags;
    }

    public List<String> getRelatedProblemTags() {
        return relatedProblemTags;
    }

    public void setRelatedProblemTags(List<String> relatedProblemTags) {
        this.relatedProblemTags = relatedProblemTags;
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

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getSolutions() {
        return solutions;
    }

    public void setSolutions(String solutions) {
        this.solutions = solutions;
    }

    public File getSolutionAttachment() {
        return solutionAttachment;
    }

    public void setSolutionAttachment(File solutionAttachment) {
        this.solutionAttachment = solutionAttachment;
    }

    public String getPlanBSolutions() {
        return planBSolutions;
    }

    public void setPlanBSolutions(String planBSolutions) {
        this.planBSolutions = planBSolutions;
    }

    @Override
    public String toString() {
        return "ChangeBean{" +
                "tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", planningTime='" + planningTime + '\'' +
                ", deadline='" + deadline + '\'' +
                ", oaNumber='" + oaNumber + '\'' +
                ", type='" + type + '\'' +
                ", impactScope='" + impactScope + '\'' +
                ", impactLevel='" + impactLevel + '\'' +
                ", riskLevel='" + riskLevel + '\'' +
                ", relatedEventTags=" + relatedEventTags +
                ", relatedProblemTags=" + relatedProblemTags +
                ", relatedChangeTags=" + relatedChangeTags +
                ", relatedConfigSNs=" + relatedConfigSNs +
                ", cause='" + cause + '\'' +
                ", content='" + content + '\'' +
                ", file=" + file +
                ", solutions='" + solutions + '\'' +
                ", solutionAttachment=" + solutionAttachment +
                ", planBSolutions='" + planBSolutions + '\'' +
                '}';
    }
}
