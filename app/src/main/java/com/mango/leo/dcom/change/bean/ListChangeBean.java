package com.mango.leo.dcom.change.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/11.
 */

public class ListChangeBean {

    /**
     * list : [{"id":15,"tenantId":42,"stage":1,"title":"2www","tag":"186101520QJR","relatedOa":"","impactScope":"","impactLevel":"??","changeType":"????","riskLevel":"?","cause":"","content":"","planBReviews":null,"expertOpinion":null,"status":"expert_opinion","contentAttachments":null,"solution":[],"solutionAttachments":null,"planB":[],"review1On":null,"implementedOn":null,"review2On":null,"review3On":null,"createdOn":1531207305000,"closedOn":null,"planningTime":1530720000000,"deadline":1530547200000,"implementedBy":null,"closedBy":null,"createdBy":{"id":73,"tenantId":35,"realName":"admin","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null},"expertUser":null,"eventTickets":null,"problemTickets":null,"changeTickets":null,"assetConfigSNs":null},{"id":13,"tenantId":42,"stage":2,"title":"123","tag":"18461531VTR","relatedOa":"","impactScope":"5","impactLevel":"??","changeType":"????","riskLevel":"?","cause":"","content":"","planBReviews":null,"expertOpinion":null,"status":"","contentAttachments":null,"solution":[],"solutionAttachments":null,"planB":[],"review1On":null,"implementedOn":null,"review2On":null,"review3On":null,"createdOn":1525591921000,"closedOn":null,"planningTime":1526486400000,"deadline":1526054400000,"implementedBy":null,"closedBy":null,"createdBy":{"id":73,"tenantId":35,"realName":"admin","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null},"expertUser":null,"eventTickets":null,"problemTickets":null,"changeTickets":null,"assetConfigSNs":null},{"id":12,"tenantId":42,"stage":5,"title":"dsaf","tag":"18461524HGV","relatedOa":"","impactScope":"","impactLevel":"??","changeType":"????","riskLevel":"?","cause":"","content":"","planBReviews":null,"expertOpinion":null,"status":"","contentAttachments":null,"solution":[],"solutionAttachments":null,"planB":[],"review1On":null,"implementedOn":1525591632000,"review2On":null,"review3On":null,"createdOn":1525591492000,"closedOn":null,"planningTime":1527004800000,"deadline":1527177600000,"implementedBy":{"id":73,"tenantId":35,"realName":"admin","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null},"closedBy":null,"createdBy":{"id":73,"tenantId":35,"realName":"admin","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null},"expertUser":null,"eventTickets":null,"problemTickets":null,"changeTickets":null,"assetConfigSNs":null},{"id":11,"tenantId":42,"stage":5,"title":"dsaf","tag":"18461524LIF","relatedOa":"","impactScope":"","impactLevel":"??","changeType":"????","riskLevel":"?","cause":"","content":"","planBReviews":null,"expertOpinion":null,"status":"","contentAttachments":null,"solution":[],"solutionAttachments":null,"planB":[],"review1On":null,"implementedOn":1525591654000,"review2On":null,"review3On":null,"createdOn":1525591465000,"closedOn":null,"planningTime":1527091200000,"deadline":1527177600000,"implementedBy":{"id":73,"tenantId":35,"realName":"admin","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null},"closedBy":null,"createdBy":{"id":73,"tenantId":35,"realName":"admin","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null},"expertUser":null,"eventTickets":null,"problemTickets":null,"changeTickets":null,"assetConfigSNs":null}]
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
         * id : 15
         * tenantId : 42
         * stage : 1
         * title : 2www
         * tag : 186101520QJR
         * relatedOa :
         * impactScope :
         * impactLevel : ??
         * changeType : ????
         * riskLevel : ?
         * cause :
         * content :
         * planBReviews : null
         * expertOpinion : null
         * status : expert_opinion
         * contentAttachments : null
         * solution : []
         * solutionAttachments : null
         * planB : []
         * review1On : null
         * implementedOn : null
         * review2On : null
         * review3On : null
         * createdOn : 1531207305000
         * closedOn : null
         * planningTime : 1530720000000
         * deadline : 1530547200000
         * implementedBy : null
         * closedBy : null
         * createdBy : {"id":73,"tenantId":35,"realName":"admin","username":null,"token":null,"status":null,"roleName":null,"phone":null,"displayPicture":null}
         * expertUser : null
         * eventTickets : null
         * problemTickets : null
         * changeTickets : null
         * assetConfigSNs : null
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
        private String status;
        private Object contentAttachments;
        private Object solutionAttachments;
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
        private Object eventTickets;
        private Object problemTickets;
        private Object changeTickets;
        private Object assetConfigSNs;
        private List<?> solution;
        private List<?> planB;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getContentAttachments() {
            return contentAttachments;
        }

        public void setContentAttachments(Object contentAttachments) {
            this.contentAttachments = contentAttachments;
        }

        public Object getSolutionAttachments() {
            return solutionAttachments;
        }

        public void setSolutionAttachments(Object solutionAttachments) {
            this.solutionAttachments = solutionAttachments;
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

        public List<?> getSolution() {
            return solution;
        }

        public void setSolution(List<?> solution) {
            this.solution = solution;
        }

        public List<?> getPlanB() {
            return planB;
        }

        public void setPlanB(List<?> planB) {
            this.planB = planB;
        }

        public static class CreatedByBean {
            /**
             * id : 73
             * tenantId : 35
             * realName : admin
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
