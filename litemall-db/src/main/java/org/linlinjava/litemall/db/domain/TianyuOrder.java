package org.linlinjava.litemall.db.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class TianyuOrder {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tianyu_order.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tianyu_order.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tianyu_order.plan_id
     *
     * @mbg.generated
     */
    private Integer planId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tianyu_order.order_sn
     *
     * @mbg.generated
     */
    private String orderSn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tianyu_order.order_status
     *
     * @mbg.generated
     */
    private Short orderStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tianyu_order.consignee
     *
     * @mbg.generated
     */
    private String consignee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tianyu_order.mobile
     *
     * @mbg.generated
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tianyu_order.end_time
     *
     * @mbg.generated
     */
    private LocalDateTime endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tianyu_order.comments
     *
     * @mbg.generated
     */
    private Integer comments;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tianyu_order.add_time
     *
     * @mbg.generated
     */
    private LocalDateTime addTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tianyu_order.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tianyu_order.deleted
     *
     * @mbg.generated
     */
    private Boolean deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tianyu_order.id
     *
     * @return the value of tianyu_order.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tianyu_order.id
     *
     * @param id the value for tianyu_order.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tianyu_order.user_id
     *
     * @return the value of tianyu_order.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tianyu_order.user_id
     *
     * @param userId the value for tianyu_order.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tianyu_order.plan_id
     *
     * @return the value of tianyu_order.plan_id
     *
     * @mbg.generated
     */
    public Integer getPlanId() {
        return planId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tianyu_order.plan_id
     *
     * @param planId the value for tianyu_order.plan_id
     *
     * @mbg.generated
     */
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tianyu_order.order_sn
     *
     * @return the value of tianyu_order.order_sn
     *
     * @mbg.generated
     */
    public String getOrderSn() {
        return orderSn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tianyu_order.order_sn
     *
     * @param orderSn the value for tianyu_order.order_sn
     *
     * @mbg.generated
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tianyu_order.order_status
     *
     * @return the value of tianyu_order.order_status
     *
     * @mbg.generated
     */
    public Short getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tianyu_order.order_status
     *
     * @param orderStatus the value for tianyu_order.order_status
     *
     * @mbg.generated
     */
    public void setOrderStatus(Short orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tianyu_order.consignee
     *
     * @return the value of tianyu_order.consignee
     *
     * @mbg.generated
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tianyu_order.consignee
     *
     * @param consignee the value for tianyu_order.consignee
     *
     * @mbg.generated
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tianyu_order.mobile
     *
     * @return the value of tianyu_order.mobile
     *
     * @mbg.generated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tianyu_order.mobile
     *
     * @param mobile the value for tianyu_order.mobile
     *
     * @mbg.generated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tianyu_order.end_time
     *
     * @return the value of tianyu_order.end_time
     *
     * @mbg.generated
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tianyu_order.end_time
     *
     * @param endTime the value for tianyu_order.end_time
     *
     * @mbg.generated
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tianyu_order.comments
     *
     * @return the value of tianyu_order.comments
     *
     * @mbg.generated
     */
    public Integer getComments() {
        return comments;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tianyu_order.comments
     *
     * @param comments the value for tianyu_order.comments
     *
     * @mbg.generated
     */
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tianyu_order.add_time
     *
     * @return the value of tianyu_order.add_time
     *
     * @mbg.generated
     */
    public LocalDateTime getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tianyu_order.add_time
     *
     * @param addTime the value for tianyu_order.add_time
     *
     * @mbg.generated
     */
    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tianyu_order.update_time
     *
     * @return the value of tianyu_order.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tianyu_order.update_time
     *
     * @param updateTime the value for tianyu_order.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tianyu_order.deleted
     *
     * @return the value of tianyu_order.deleted
     *
     * @mbg.generated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tianyu_order.deleted
     *
     * @param deleted the value for tianyu_order.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", IS_DELETED=").append(IS_DELETED);
        sb.append(", NOT_DELETED=").append(NOT_DELETED);
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", planId=").append(planId);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", consignee=").append(consignee);
        sb.append(", mobile=").append(mobile);
        sb.append(", endTime=").append(endTime);
        sb.append(", comments=").append(comments);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TianyuOrder other = (TianyuOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPlanId() == null ? other.getPlanId() == null : this.getPlanId().equals(other.getPlanId()))
            && (this.getOrderSn() == null ? other.getOrderSn() == null : this.getOrderSn().equals(other.getOrderSn()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getConsignee() == null ? other.getConsignee() == null : this.getConsignee().equals(other.getConsignee()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getComments() == null ? other.getComments() == null : this.getComments().equals(other.getComments()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPlanId() == null) ? 0 : getPlanId().hashCode());
        result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getConsignee() == null) ? 0 : getConsignee().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getComments() == null) ? 0 : getComments().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    public enum Deleted {
        NOT_DELETED(new Boolean("0"), "未删除"),
        IS_DELETED(new Boolean("1"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        private final Boolean value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        Deleted(Boolean value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        public Boolean getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        public Boolean value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        userId("user_id", "userId", "INTEGER", false),
        planId("plan_id", "planId", "INTEGER", false),
        orderSn("order_sn", "orderSn", "VARCHAR", false),
        orderStatus("order_status", "orderStatus", "SMALLINT", false),
        consignee("consignee", "consignee", "VARCHAR", false),
        mobile("mobile", "mobile", "VARCHAR", false),
        endTime("end_time", "endTime", "TIMESTAMP", false),
        comments("comments", "comments", "INTEGER", false),
        addTime("add_time", "addTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        deleted("deleted", "deleted", "BIT", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table tianyu_order
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}