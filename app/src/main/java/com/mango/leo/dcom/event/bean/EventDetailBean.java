package com.mango.leo.dcom.event.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/13.
 */

public class EventDetailBean {

    /**
     * id : 62
     * tenantId : 42
     * stage : 1
     * level : null
     * tag : 1807131743YJK
     * title : yy
     * origin : 服务中心
     * type : 服务请求
     * severity : 严重
     * scope : 77
     * contactPerson : null
     * description : gg
     * status : new
     * assignedDesc : null
     * solutionReviews : null
     * eventCause : null
     * attachments : [{"fileName":"1531475016729","filePath":"/api/1531475016729.jpg","url":"/cdn/dcom/out/api/1531475016729.jpg","createdOn":1531475016729}]
     * solutions : []
     * preMeasures : []
     * claimedOn : null
     * createdOn : 1531475017000
     * updatedOn : 1531475017000
     * solutionOn : null
     * reviewedOn : null
     * assignedOn : null
     * occurredOn : 1531474980000
     * assignedBy : null
     * assignedTo : null
     * claimedBy : {"id":96,"tenantId":42,"realName":"Ali","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null}
     * createdBy : {"id":96,"tenantId":42,"realName":"Ali","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null}
     * reviewedBy : null
     * solutionBy : null
     * assetConfigSNs : ["dsgrdg","UI9090901","S8989"]
     * eventTickets : []
     * problemTickets : []
     * changeTickets : []
     */

    private int id;
    private int tenantId;
    private int stage;
    private Object level;
    private String tag;
    private String title;
    private String origin;
    private String type;
    private String severity;
    private String scope;
    private Object contactPerson;
    private String description;
    private String status;
    private Object assignedDesc;
    private Object solutionReviews;
    private Object eventCause;
    private Object claimedOn;
    private long createdOn;
    private long updatedOn;
    private Object solutionOn;
    private Object reviewedOn;
    private Object assignedOn;
    private long occurredOn;
    private Object assignedBy;
    private Object assignedTo;
    private ClaimedByBean claimedBy;
    private CreatedByBean createdBy;
    private Object reviewedBy;
    private Object solutionBy;
    private List<AttachmentsBean> attachments;
    private List<?> solutions;
    private List<?> preMeasures;
    private List<String> assetConfigSNs;
    private List<?> eventTickets;
    private List<?> problemTickets;
    private List<?> changeTickets;

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

    public Object getLevel() {
        return level;
    }

    public void setLevel(Object level) {
        this.level = level;
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

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Object getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(Object contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getAssignedDesc() {
        return assignedDesc;
    }

    public void setAssignedDesc(Object assignedDesc) {
        this.assignedDesc = assignedDesc;
    }

    public Object getSolutionReviews() {
        return solutionReviews;
    }

    public void setSolutionReviews(Object solutionReviews) {
        this.solutionReviews = solutionReviews;
    }

    public Object getEventCause() {
        return eventCause;
    }

    public void setEventCause(Object eventCause) {
        this.eventCause = eventCause;
    }

    public Object getClaimedOn() {
        return claimedOn;
    }

    public void setClaimedOn(Object claimedOn) {
        this.claimedOn = claimedOn;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public long getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Object getSolutionOn() {
        return solutionOn;
    }

    public void setSolutionOn(Object solutionOn) {
        this.solutionOn = solutionOn;
    }

    public Object getReviewedOn() {
        return reviewedOn;
    }

    public void setReviewedOn(Object reviewedOn) {
        this.reviewedOn = reviewedOn;
    }

    public Object getAssignedOn() {
        return assignedOn;
    }

    public void setAssignedOn(Object assignedOn) {
        this.assignedOn = assignedOn;
    }

    public long getOccurredOn() {
        return occurredOn;
    }

    public void setOccurredOn(long occurredOn) {
        this.occurredOn = occurredOn;
    }

    public Object getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Object assignedBy) {
        this.assignedBy = assignedBy;
    }

    public Object getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Object assignedTo) {
        this.assignedTo = assignedTo;
    }

    public ClaimedByBean getClaimedBy() {
        return claimedBy;
    }

    public void setClaimedBy(ClaimedByBean claimedBy) {
        this.claimedBy = claimedBy;
    }

    public CreatedByBean getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedByBean createdBy) {
        this.createdBy = createdBy;
    }

    public Object getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(Object reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public Object getSolutionBy() {
        return solutionBy;
    }

    public void setSolutionBy(Object solutionBy) {
        this.solutionBy = solutionBy;
    }

    public List<AttachmentsBean> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentsBean> attachments) {
        this.attachments = attachments;
    }

    public List<?> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<?> solutions) {
        this.solutions = solutions;
    }

    public List<?> getPreMeasures() {
        return preMeasures;
    }

    public void setPreMeasures(List<?> preMeasures) {
        this.preMeasures = preMeasures;
    }

    public List<String> getAssetConfigSNs() {
        return assetConfigSNs;
    }

    public void setAssetConfigSNs(List<String> assetConfigSNs) {
        this.assetConfigSNs = assetConfigSNs;
    }

    public List<?> getEventTickets() {
        return eventTickets;
    }

    public void setEventTickets(List<?> eventTickets) {
        this.eventTickets = eventTickets;
    }

    public List<?> getProblemTickets() {
        return problemTickets;
    }

    public void setProblemTickets(List<?> problemTickets) {
        this.problemTickets = problemTickets;
    }

    public List<?> getChangeTickets() {
        return changeTickets;
    }

    public void setChangeTickets(List<?> changeTickets) {
        this.changeTickets = changeTickets;
    }

    public static class ClaimedByBean {
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
