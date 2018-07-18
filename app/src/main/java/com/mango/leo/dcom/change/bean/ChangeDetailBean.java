package com.mango.leo.dcom.change.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/17.
 */

public class ChangeDetailBean {

    /**
     * id : 47
     * tenantId : 42
     * stage : 0
     * title : u的话
     * tag : 186181124VHC
     * relatedOa : u发货
     * impactScope : 中
     * impactLevel : 1
     * changeType : 紧急变更
     * riskLevel : B
     * cause : 更好地
     * content : 更多
     * planBReviews : null
     * expertOpinion : null
     * status : null
     * contentAttachments : [{"fileName":"1531884284855","filePath":"/api/1531884284848.jpg","url":"/cdn/dcom/out/api/1531884284848.jpg","createdOn":1531884284855}]
     * solution : [{"step":1,"detail":"内容1","state":null,"type":null},{"step":2,"detail":"内容2","state":null,"type":null}]
     * solutionAttachments : [{"fileName":"1531884284860","filePath":"/api/1531884284855.jpg","url":"/cdn/dcom/out/api/1531884284855.jpg","createdOn":1531884284860}]
     * planB : [{"step":1,"detail":"回退内容1","state":null,"type":null},{"step":2,"detail":"回退内容2","state":null,"type":null}]
     * review1On : null
     * implementedOn : null
     * review2On : null
     * review3On : null
     * createdOn : 1531884285000
     * closedOn : null
     * planningTime : 1531884180000
     * deadline : 1531884180000
     * implementedBy : null
     * closedBy : null
     * createdBy : {"id":96,"tenantId":42,"realName":"Ali","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null}
     * expertUser : null
     * eventTickets : ["1807131743YJK","1807160933DVR"]
     * problemTickets : ["186161824RHN","18617158OTN","186161824NFW"]
     * changeTickets : ["186181115QGZ","186181112GKC","186181118BGV","186181112HJO"]
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
         * fileName : 1531884284855
         * filePath : /api/1531884284848.jpg
         * url : /cdn/dcom/out/api/1531884284848.jpg
         * createdOn : 1531884284855
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
         * detail : 内容1
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
         * fileName : 1531884284860
         * filePath : /api/1531884284855.jpg
         * url : /cdn/dcom/out/api/1531884284855.jpg
         * createdOn : 1531884284860
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
         * detail : 回退内容1
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
