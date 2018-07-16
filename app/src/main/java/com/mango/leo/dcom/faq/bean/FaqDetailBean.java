package com.mango.leo.dcom.faq.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/16.
 */

public class FaqDetailBean {

    /**
     * id : 34
     * tenantId : 42
     * solAvailable : null
     * stage : 1
     * tag : 186161751YND
     * title : kk
     * priority : 1
     * origin : 趋势分析
     * classification : 防火墙
     * system : 高性能计算
     * description : kk
     * anzCause : null
     * anzMethods : null
     * solReasonNotAvailable : null
     * monitorStatus : null
     * status : new
     * occurredOn : 1531734660000
     * deadline : 1531734960000
     * createdOn : 1531734704000
     * anzOn : null
     * solOn : null
     * monitoredOn : null
     * reviewedBy : null
     * anzBy : null
     * closedBy : null
     * createdBy : {"id":96,"tenantId":42,"realName":"Ali","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null}
     * monitoredBy : null
     * solBy : null
     * attachments : [{"fileName":"1531475016729","filePath":"/api/1531475016729.jpg","url":"/cdn/dcom/out/api/1531475016729.jpg","createdOn":1531475016729}]
     * eventTickets : ["1807131743YJK","1807160933DVR"]
     * problemTickets : []
     * changeTickets : ["18481528EPX","186101520QJR","18461531VTR"]
     * assetConfigSNs : ["dsgrdg","UI9090901","UI90909011"]
     */

    private int id;
    private int tenantId;
    private Object solAvailable;
    private int stage;
    private String tag;
    private String title;
    private String priority;
    private String origin;
    private String classification;
    private String system;
    private String description;
    private Object anzCause;
    private Object anzMethods;
    private Object solReasonNotAvailable;
    private Object monitorStatus;
    private String status;
    private long occurredOn;
    private long deadline;
    private long createdOn;
    private Object anzOn;
    private Object solOn;
    private Object monitoredOn;
    private Object reviewedBy;
    private Object anzBy;
    private Object closedBy;
    private CreatedByBean createdBy;
    private Object monitoredBy;
    private Object solBy;
    private List<AttachmentsBean> attachments;
    private List<String> eventTickets;
    private List<?> problemTickets;
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

    public Object getSolAvailable() {
        return solAvailable;
    }

    public void setSolAvailable(Object solAvailable) {
        this.solAvailable = solAvailable;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getAnzCause() {
        return anzCause;
    }

    public void setAnzCause(Object anzCause) {
        this.anzCause = anzCause;
    }

    public Object getAnzMethods() {
        return anzMethods;
    }

    public void setAnzMethods(Object anzMethods) {
        this.anzMethods = anzMethods;
    }

    public Object getSolReasonNotAvailable() {
        return solReasonNotAvailable;
    }

    public void setSolReasonNotAvailable(Object solReasonNotAvailable) {
        this.solReasonNotAvailable = solReasonNotAvailable;
    }

    public Object getMonitorStatus() {
        return monitorStatus;
    }

    public void setMonitorStatus(Object monitorStatus) {
        this.monitorStatus = monitorStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getOccurredOn() {
        return occurredOn;
    }

    public void setOccurredOn(long occurredOn) {
        this.occurredOn = occurredOn;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public Object getAnzOn() {
        return anzOn;
    }

    public void setAnzOn(Object anzOn) {
        this.anzOn = anzOn;
    }

    public Object getSolOn() {
        return solOn;
    }

    public void setSolOn(Object solOn) {
        this.solOn = solOn;
    }

    public Object getMonitoredOn() {
        return monitoredOn;
    }

    public void setMonitoredOn(Object monitoredOn) {
        this.monitoredOn = monitoredOn;
    }

    public Object getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(Object reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public Object getAnzBy() {
        return anzBy;
    }

    public void setAnzBy(Object anzBy) {
        this.anzBy = anzBy;
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

    public Object getMonitoredBy() {
        return monitoredBy;
    }

    public void setMonitoredBy(Object monitoredBy) {
        this.monitoredBy = monitoredBy;
    }

    public Object getSolBy() {
        return solBy;
    }

    public void setSolBy(Object solBy) {
        this.solBy = solBy;
    }

    public List<AttachmentsBean> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentsBean> attachments) {
        this.attachments = attachments;
    }

    public List<String> getEventTickets() {
        return eventTickets;
    }

    public void setEventTickets(List<String> eventTickets) {
        this.eventTickets = eventTickets;
    }

    public List<?> getProblemTickets() {
        return problemTickets;
    }

    public void setProblemTickets(List<?> problemTickets) {
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

    public static class AttachmentsBean {
        /**
         * fileName : 1531475016729
         * filePath : /api/1531475016729.jpg
         * url : /cdn/dcom/out/api/1531475016729.jpg
         * createdOn : 1531475016729
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
