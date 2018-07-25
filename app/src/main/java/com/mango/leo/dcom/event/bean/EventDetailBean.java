package com.mango.leo.dcom.event.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/13.
 */

public class EventDetailBean {

    /**
     * id : 98
     * tenantId : 42
     * stage : 4
     * level : 3
     * tag : 1807251355HIS
     * title : oo
     * origin : 用户报告
     * type : 服务请求
     * severity : 中
     * scope : yy
     * contactPerson : null
     * description : pp
     * status : fixed
     * assignedDesc : kk
     * solutionReviews : 213
     * eventCause : 大
     * attachments : [{"fileName":"1532498166154","filePath":"/api/1532498166153.jpg","url":"/cdn/dcom/out/api/1532498166153.jpg","createdOn":1532498166154}]
     * solutions : null
     * preMeasures : null
     * claimedOn : 1532498377000
     * createdOn : 1532498166000
     * updatedOn : 1532498462000
     * solutionOn : 1532498462000
     * reviewedOn : null
     * assignedOn : 1532498377000
     * occurredOn : 1532498100000
     * assignedBy : {"id":96,"tenantId":42,"realName":"mango1","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null}
     * assignedTo : {"id":67,"name":"team A"}
     * claimedBy : {"id":96,"tenantId":42,"realName":"mango1","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null}
     * createdBy : {"id":96,"tenantId":42,"realName":"mango1","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null}
     * reviewedBy : null
     * solutionBy : {"id":96,"tenantId":42,"realName":"mango1","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null}
     * assetConfigSNs : ["UI9090901","S8989","UI90909011"]
     * eventTickets : []
     * problemTickets : []
     * changeTickets : []
     */

    private int id;
    private int tenantId;
    private int stage;
    private int level;
    private String tag;
    private String title;
    private String origin;
    private String type;
    private String severity;
    private String scope;
    private Object contactPerson;
    private String description;
    private String status;
    private String assignedDesc;
    private String solutionReviews;
    private String eventCause;
    private Object solutions;
    private Object preMeasures;
    private long claimedOn;
    private long createdOn;
    private long updatedOn;
    private long solutionOn;
    private Object reviewedOn;
    private long assignedOn;
    private long occurredOn;
    private AssignedByBean assignedBy;
    private AssignedToBean assignedTo;
    private ClaimedByBean claimedBy;
    private CreatedByBean createdBy;
    private Object reviewedBy;
    private SolutionByBean solutionBy;
    private List<AttachmentsBean> attachments;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
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

    public String getAssignedDesc() {
        return assignedDesc;
    }

    public void setAssignedDesc(String assignedDesc) {
        this.assignedDesc = assignedDesc;
    }

    public String getSolutionReviews() {
        return solutionReviews;
    }

    public void setSolutionReviews(String solutionReviews) {
        this.solutionReviews = solutionReviews;
    }

    public String getEventCause() {
        return eventCause;
    }

    public void setEventCause(String eventCause) {
        this.eventCause = eventCause;
    }

    public Object getSolutions() {
        return solutions;
    }

    public void setSolutions(Object solutions) {
        this.solutions = solutions;
    }

    public Object getPreMeasures() {
        return preMeasures;
    }

    public void setPreMeasures(Object preMeasures) {
        this.preMeasures = preMeasures;
    }

    public long getClaimedOn() {
        return claimedOn;
    }

    public void setClaimedOn(long claimedOn) {
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

    public long getSolutionOn() {
        return solutionOn;
    }

    public void setSolutionOn(long solutionOn) {
        this.solutionOn = solutionOn;
    }

    public Object getReviewedOn() {
        return reviewedOn;
    }

    public void setReviewedOn(Object reviewedOn) {
        this.reviewedOn = reviewedOn;
    }

    public long getAssignedOn() {
        return assignedOn;
    }

    public void setAssignedOn(long assignedOn) {
        this.assignedOn = assignedOn;
    }

    public long getOccurredOn() {
        return occurredOn;
    }

    public void setOccurredOn(long occurredOn) {
        this.occurredOn = occurredOn;
    }

    public AssignedByBean getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(AssignedByBean assignedBy) {
        this.assignedBy = assignedBy;
    }

    public AssignedToBean getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(AssignedToBean assignedTo) {
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

    public SolutionByBean getSolutionBy() {
        return solutionBy;
    }

    public void setSolutionBy(SolutionByBean solutionBy) {
        this.solutionBy = solutionBy;
    }

    public List<AttachmentsBean> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentsBean> attachments) {
        this.attachments = attachments;
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

    public static class AssignedByBean {
        /**
         * id : 96
         * tenantId : 42
         * realName : mango1
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

    public static class AssignedToBean {
        /**
         * id : 67
         * name : team A
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ClaimedByBean {
        /**
         * id : 96
         * tenantId : 42
         * realName : mango1
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
         * realName : mango1
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

    public static class SolutionByBean {
        /**
         * id : 96
         * tenantId : 42
         * realName : mango1
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
         * fileName : 1532498166154
         * filePath : /api/1532498166153.jpg
         * url : /cdn/dcom/out/api/1532498166153.jpg
         * createdOn : 1532498166154
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
