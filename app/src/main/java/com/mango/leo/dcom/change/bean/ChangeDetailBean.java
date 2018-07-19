package com.mango.leo.dcom.change.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/17.
 */

public class ChangeDetailBean {

    /**
     * id : 51
     * tenantId : 42
     * stage : 0
     * title : yh
     * tag : 186191057FJP
     * relatedOa : hdb
     * impactScope : 低
     * impactLevel : 1
     * changeType : 紧急变更
     * riskLevel : C
     * cause : gxbsb
     * content : fhh
     * planBReviews : null
     * expertOpinion : null
     * status : null
     * contentAttachments : [{"fileName":"1531969077979","filePath":"/api/1531969077977.jpg","url":"/cdn/dcom/out/api/1531969077977.jpg","createdOn":1531969077979}]
     * solution : [{"step":1,"detail":"gxhdh","state":null,"type":null},{"step":2,"detail":"续本","state":null,"type":null}]
     * solutionAttachments : [{"fileName":"1531969077979","filePath":"/api/1531969077979.jpg","url":"/cdn/dcom/out/api/1531969077979.jpg","createdOn":1531969077979}]
     * planB : [{"step":1,"detail":"好大的包","state":null,"type":null},{"step":2,"detail":"邓货币兑换","state":null,"type":null}]
     * review1On : null
     * implementedOn : null
     * review2On : null
     * review3On : null
     * createdOn : 1531969078000
     * closedOn : null
     * planningTime : 1531968900000
     * deadline : 1531968900000
     * implementedBy : null
     * closedBy : null
     * createdBy : {"id":96,"tenantId":42,"realName":"Ali","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null}
     * expertUser : null
     * eventTickets : ["1807160933DVR"]
     * problemTickets : ["18617158OTN","186181154QHH"]
     * changeTickets : ["186181124VHC","186181115QGZ","186181112HJO"]
     * assetConfigSNs : ["dsgrdg","UI909090","UI9090901","S8989"]
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
    private Object planBReviews;
    private Object expertOpinion;
    private Object status;
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
    private List<SolutionBean> solution;
    private List<SolutionAttachmentsBean> solutionAttachments;
    private List<PlanBBean> planB;
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

    public Object getPlanBReviews() {
        return planBReviews;
    }

    public void setPlanBReviews(Object planBReviews) {
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

    public List<SolutionBean> getSolution() {
        return solution;
    }

    public void setSolution(List<SolutionBean> solution) {
        this.solution = solution;
    }

    public List<SolutionAttachmentsBean> getSolutionAttachments() {
        return solutionAttachments;
    }

    public void setSolutionAttachments(List<SolutionAttachmentsBean> solutionAttachments) {
        this.solutionAttachments = solutionAttachments;
    }

    public List<PlanBBean> getPlanB() {
        return planB;
    }

    public void setPlanB(List<PlanBBean> planB) {
        this.planB = planB;
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
         * fileName : 1531969077979
         * filePath : /api/1531969077977.jpg
         * url : /cdn/dcom/out/api/1531969077977.jpg
         * createdOn : 1531969077979
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

    public static class SolutionBean {
        /**
         * step : 1
         * detail : gxhdh
         * state : null
         * type : null
         */

        private int step;
        private String detail;
        private Object state;
        private Object type;

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }
    }

    public static class SolutionAttachmentsBean {
        /**
         * fileName : 1531969077979
         * filePath : /api/1531969077979.jpg
         * url : /cdn/dcom/out/api/1531969077979.jpg
         * createdOn : 1531969077979
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

    public static class PlanBBean {
        /**
         * step : 1
         * detail : 好大的包
         * state : null
         * type : null
         */

        private int step;
        private String detail;
        private Object state;
        private Object type;

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }
    }
}
