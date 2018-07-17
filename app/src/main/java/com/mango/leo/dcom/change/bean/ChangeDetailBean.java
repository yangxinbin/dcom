package com.mango.leo.dcom.change.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/17.
 */

public class ChangeDetailBean {

    /**
     * id : 17
     * tenantId : 42
     * stage : 1
     * title : yy
     * tag : 186171550VZV
     * relatedOa : 1852
     * impactScope : 高
     * impactLevel : 1
     * changeType : 紧急变更
     * riskLevel : C
     * cause : null
     * content : 哈哈
     * planBReviews : null
     * expertOpinion : null
     * status : null
     * contentAttachments : [{"fileName":"1531813824973","filePath":"/api/1531813824967.jpg","url":"/cdn/dcom/out/api/1531813824967.jpg","createdOn":1531813824973}]
     * solution : null
     * solutionAttachments : [{"fileName":"1531813824980","filePath":"/api/1531813824974.jpg","url":"/cdn/dcom/out/api/1531813824974.jpg","createdOn":1531813824980}]
     * planB : null
     * review1On : null
     * implementedOn : null
     * review2On : null
     * review3On : null
     * createdOn : 1531813825000
     * closedOn : null
     * planningTime : 1531813740000
     * deadline : 1531813740000
     * implementedBy : null
     * closedBy : null
     * createdBy : {"id":96,"tenantId":42,"realName":"Ali","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null}
     * expertUser : null
     * eventTickets : ["1807131743YJK","1807160933DVR"]
     * problemTickets : ["1807131743YJK","1807160933DVR"]
     * changeTickets : ["186161812ARU","18617168KCU","186161824NFW","186161812MYJ"]
     * assetConfigSNs : ["dsgrdg","UI909090","UI9090901","S8989","UI90909011"]
     */

    private int id;
    private int tenantId;
    private int stage;
    private String title;
    private String tag;
    private String relatedOa;
    private String impactScope;
    private String impactLevel;
    private String changeType;
    private String riskLevel;
    private String cause;
    private String content;
    private String planBReviews;
    private Object expertOpinion;
    private Object status;
    private Object solution;
    private Object planB;
    private Object review1On;
    private Object implementedOn;
    private Object review2On;
    private Object review3On;
    private long createdOn;
    private Object closedOn;
    private long planningTime;
    private long deadline;
    private Object implementedBy;
    private Object closedBy;
    private CreatedByBean createdBy;
    private Object expertUser;
    private List<ContentAttachmentsBean> contentAttachments;
    private List<SolutionAttachmentsBean> solutionAttachments;
    private List<String> eventTickets;
    private List<String> problemTickets;
    private List<String> changeTickets;
    private List<String> assetConfigSNs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRelatedOa() {
        return relatedOa;
    }

    public void setRelatedOa(String relatedOa) {
        this.relatedOa = relatedOa;
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

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
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

    public String getPlanBReviews() {
        return planBReviews;
    }

    public void setPlanBReviews(String planBReviews) {
        this.planBReviews = planBReviews;
    }

    public Object getExpertOpinion() {
        return expertOpinion;
    }

    public void setExpertOpinion(Object expertOpinion) {
        this.expertOpinion = expertOpinion;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getSolution() {
        return solution;
    }

    public void setSolution(Object solution) {
        this.solution = solution;
    }

    public Object getPlanB() {
        return planB;
    }

    public void setPlanB(Object planB) {
        this.planB = planB;
    }

    public Object getReview1On() {
        return review1On;
    }

    public void setReview1On(Object review1On) {
        this.review1On = review1On;
    }

    public Object getImplementedOn() {
        return implementedOn;
    }

    public void setImplementedOn(Object implementedOn) {
        this.implementedOn = implementedOn;
    }

    public Object getReview2On() {
        return review2On;
    }

    public void setReview2On(Object review2On) {
        this.review2On = review2On;
    }

    public Object getReview3On() {
        return review3On;
    }

    public void setReview3On(Object review3On) {
        this.review3On = review3On;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public Object getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(Object closedOn) {
        this.closedOn = closedOn;
    }

    public long getPlanningTime() {
        return planningTime;
    }

    public void setPlanningTime(long planningTime) {
        this.planningTime = planningTime;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public Object getImplementedBy() {
        return implementedBy;
    }

    public void setImplementedBy(Object implementedBy) {
        this.implementedBy = implementedBy;
    }

    public Object getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(Object closedBy) {
        this.closedBy = closedBy;
    }

    public CreatedByBean getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedByBean createdBy) {
        this.createdBy = createdBy;
    }

    public Object getExpertUser() {
        return expertUser;
    }

    public void setExpertUser(Object expertUser) {
        this.expertUser = expertUser;
    }

    public List<ContentAttachmentsBean> getContentAttachments() {
        return contentAttachments;
    }

    public void setContentAttachments(List<ContentAttachmentsBean> contentAttachments) {
        this.contentAttachments = contentAttachments;
    }

    public List<SolutionAttachmentsBean> getSolutionAttachments() {
        return solutionAttachments;
    }

    public void setSolutionAttachments(List<SolutionAttachmentsBean> solutionAttachments) {
        this.solutionAttachments = solutionAttachments;
    }

    public List<String> getEventTickets() {
        return eventTickets;
    }

    public void setEventTickets(List<String> eventTickets) {
        this.eventTickets = eventTickets;
    }

    public List<String> getProblemTickets() {
        return problemTickets;
    }

    public void setProblemTickets(List<String> problemTickets) {
        this.problemTickets = problemTickets;
    }

    public List<String> getChangeTickets() {
        return changeTickets;
    }

    public void setChangeTickets(List<String> changeTickets) {
        this.changeTickets = changeTickets;
    }

    public List<String> getAssetConfigSNs() {
        return assetConfigSNs;
    }

    public void setAssetConfigSNs(List<String> assetConfigSNs) {
        this.assetConfigSNs = assetConfigSNs;
    }

    public static class CreatedByBean {
        /**
         * id : 96
         * tenantId : 42
         * realName : Ali
         * username : null
         * token : null
         * status : null
         * roleName : null
         * phone : null
         * displayPicture : null
         */

        private int id;
        private int tenantId;
        private String realName;
        private Object username;
        private Object token;
        private Object status;
        private Object roleName;
        private Object phone;
        private Object displayPicture;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTenantId() {
            return tenantId;
        }

        public void setTenantId(int tenantId) {
            this.tenantId = tenantId;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getRoleName() {
            return roleName;
        }

        public void setRoleName(Object roleName) {
            this.roleName = roleName;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getDisplayPicture() {
            return displayPicture;
        }

        public void setDisplayPicture(Object displayPicture) {
            this.displayPicture = displayPicture;
        }
    }

    public static class ContentAttachmentsBean {
        /**
         * fileName : 1531813824973
         * filePath : /api/1531813824967.jpg
         * url : /cdn/dcom/out/api/1531813824967.jpg
         * createdOn : 1531813824973
         */

        private String fileName;
        private String filePath;
        private String url;
        private long createdOn;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(long createdOn) {
            this.createdOn = createdOn;
        }
    }

    public static class SolutionAttachmentsBean {
        /**
         * fileName : 1531813824980
         * filePath : /api/1531813824974.jpg
         * url : /cdn/dcom/out/api/1531813824974.jpg
         * createdOn : 1531813824980
         */

        private String fileName;
        private String filePath;
        private String url;
        private long createdOn;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(long createdOn) {
            this.createdOn = createdOn;
        }
    }
}
