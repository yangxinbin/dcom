package com.mango.leo.dcom.faq.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/16.
 */

public class ListFaqBean {

    /**
     * list : [{"id":30,"tenantId":42,"solAvailable":null,"stage":1,"tag":"186161422LUK","title":"uu","priority":"0","origin":"维护","classification":"数据库","system":"高性能计算","description":"yy","anzCause":null,"anzMethods":null,"solReasonNotAvailable":null,"monitorStatus":null,"status":null,"occurredOn":1531722060000,"deadline":1531722060000,"createdOn":1531722126000,"anzOn":null,"solOn":null,"monitoredOn":null,"reviewedBy":null,"anzBy":null,"closedBy":null,"createdBy":{"id":96,"tenantId":42,"realName":"Ali","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null},"monitoredBy":null,"solBy":null,"attachments":null,"eventTickets":null,"problemTickets":null,"changeTickets":null,"assetConfigSNs":null},{"id":29,"tenantId":42,"solAvailable":null,"stage":1,"tag":"186161137PVA","title":"a12121212121","priority":"1","origin":"dd","classification":"\"\"","system":"\"\"","description":"234","anzCause":null,"anzMethods":null,"solReasonNotAvailable":null,"monitorStatus":null,"status":null,"occurredOn":0,"deadline":0,"createdOn":1531712271000,"anzOn":null,"solOn":null,"monitoredOn":null,"reviewedBy":null,"anzBy":null,"closedBy":null,"createdBy":{"id":96,"tenantId":42,"realName":"Ali","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null},"monitoredBy":null,"solBy":null,"attachments":null,"eventTickets":null,"problemTickets":null,"changeTickets":null,"assetConfigSNs":null},{"id":28,"tenantId":42,"solAvailable":null,"stage":1,"tag":"186161019GBQ","title":"a12121212121","priority":"1","origin":"cc","classification":"\"\"","system":"\"\"","description":"234","anzCause":null,"anzMethods":null,"solReasonNotAvailable":null,"monitorStatus":null,"status":null,"occurredOn":0,"deadline":0,"createdOn":1531707584000,"anzOn":null,"solOn":null,"monitoredOn":null,"reviewedBy":null,"anzBy":null,"closedBy":null,"createdBy":{"id":96,"tenantId":42,"realName":"Ali","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null},"monitoredBy":null,"solBy":null,"attachments":null,"eventTickets":null,"problemTickets":null,"changeTickets":null,"assetConfigSNs":null},{"id":27,"tenantId":42,"solAvailable":null,"stage":1,"tag":"186161010GNI","title":"a12121212121","priority":"1","origin":"bb","classification":"\"\"","system":"\"\"","description":"234","anzCause":null,"anzMethods":null,"solReasonNotAvailable":null,"monitorStatus":null,"status":null,"occurredOn":0,"deadline":0,"createdOn":1531707016000,"anzOn":null,"solOn":null,"monitoredOn":null,"reviewedBy":null,"anzBy":null,"closedBy":null,"createdBy":{"id":96,"tenantId":42,"realName":"Ali","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null},"monitoredBy":null,"solBy":null,"attachments":null,"eventTickets":null,"problemTickets":null,"changeTickets":null,"assetConfigSNs":null},{"id":26,"tenantId":42,"solAvailable":null,"stage":1,"tag":"18616108WYO","title":"a12121212121","priority":"1","origin":"aa","classification":"\"\"","system":"\"\"","description":"234","anzCause":null,"anzMethods":null,"solReasonNotAvailable":null,"monitorStatus":null,"status":null,"occurredOn":0,"deadline":0,"createdOn":1531706923000,"anzOn":null,"solOn":null,"monitoredOn":null,"reviewedBy":null,"anzBy":null,"closedBy":null,"createdBy":{"id":96,"tenantId":42,"realName":"Ali","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null},"monitoredBy":null,"solBy":null,"attachments":null,"eventTickets":null,"problemTickets":null,"changeTickets":null,"assetConfigSNs":null},{"id":25,"tenantId":42,"solAvailable":null,"stage":1,"tag":"18612118JMH","title":"dd","priority":"关键问题","origin":"事件","classification":"交换机","system":"网络","description":"","anzCause":null,"anzMethods":null,"solReasonNotAvailable":null,"monitorStatus":null,"status":null,"occurredOn":1531332300000,"deadline":1531332000000,"createdOn":1531364963000,"anzOn":null,"solOn":null,"monitoredOn":null,"reviewedBy":null,"anzBy":null,"closedBy":null,"createdBy":{"id":96,"tenantId":42,"realName":"Ali","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null},"monitoredBy":null,"solBy":null,"attachments":[],"eventTickets":null,"problemTickets":null,"changeTickets":null,"assetConfigSNs":null}]
     * size : 20
     */

    private int size;
    private List<ListBean> list;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 30
         * tenantId : 42
         * solAvailable : null
         * stage : 1
         * tag : 186161422LUK
         * title : uu
         * priority : 0
         * origin : 维护
         * classification : 数据库
         * system : 高性能计算
         * description : yy
         * anzCause : null
         * anzMethods : null
         * solReasonNotAvailable : null
         * monitorStatus : null
         * status : null
         * occurredOn : 1531722060000
         * deadline : 1531722060000
         * createdOn : 1531722126000
         * anzOn : null
         * solOn : null
         * monitoredOn : null
         * reviewedBy : null
         * anzBy : null
         * closedBy : null
         * createdBy : {"id":96,"tenantId":42,"realName":"Ali","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null}
         * monitoredBy : null
         * solBy : null
         * attachments : null
         * eventTickets : null
         * problemTickets : null
         * changeTickets : null
         * assetConfigSNs : null
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
        private Object status;
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
        private Object attachments;
        private Object eventTickets;
        private Object problemTickets;
        private Object changeTickets;
        private Object assetConfigSNs;

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

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
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

        public Object getAttachments() {
            return attachments;
        }

        public void setAttachments(Object attachments) {
            this.attachments = attachments;
        }

        public Object getEventTickets() {
            return eventTickets;
        }

        public void setEventTickets(Object eventTickets) {
            this.eventTickets = eventTickets;
        }

        public Object getProblemTickets() {
            return problemTickets;
        }

        public void setProblemTickets(Object problemTickets) {
            this.problemTickets = problemTickets;
        }

        public Object getChangeTickets() {
            return changeTickets;
        }

        public void setChangeTickets(Object changeTickets) {
            this.changeTickets = changeTickets;
        }

        public Object getAssetConfigSNs() {
            return assetConfigSNs;
        }

        public void setAssetConfigSNs(Object assetConfigSNs) {
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
    }
}
