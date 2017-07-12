package com.hfjy.service.order.model;

import java.util.Date;

public class Order {
    /**
     * id : 
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * order_sn : 订单编号
     *
     * @mbg.generated
     */
    private String orderSn;

    /**
     * user_id : 用户ID
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     * status : 状态
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * produce_amount : 商品总额
     *
     * @mbg.generated
     */
    private Long produceAmount;

    /**
     * payment_amount : 支付总额
     *
     * @mbg.generated
     */
    private Long paymentAmount;

    /**
     * user_info : 
     *
     * @mbg.generated
     */
    private String userInfo;

    /**
     * address : 
     *
     * @mbg.generated
     */
    private String address;

    /**
     * comment : 
     *
     * @mbg.generated
     */
    private String comment;

    /**
     * create_time : 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * update_time : 更新时间
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     *
     * @return the value of order.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id the value for order.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return the value of order.order_sn
     *
     * @mbg.generated
     */
    public String getOrderSn() {
        return orderSn;
    }

    /**
     *
     * @param orderSn the value for order.order_sn
     *
     * @mbg.generated
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    /**
     *
     * @return the value of order.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     *
     * @param userId the value for order.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     *
     * @return the value of order.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     *
     * @param status the value for order.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     *
     * @return the value of order.produce_amount
     *
     * @mbg.generated
     */
    public Long getProduceAmount() {
        return produceAmount;
    }

    /**
     *
     * @param produceAmount the value for order.produce_amount
     *
     * @mbg.generated
     */
    public void setProduceAmount(Long produceAmount) {
        this.produceAmount = produceAmount;
    }

    /**
     *
     * @return the value of order.payment_amount
     *
     * @mbg.generated
     */
    public Long getPaymentAmount() {
        return paymentAmount;
    }

    /**
     *
     * @param paymentAmount the value for order.payment_amount
     *
     * @mbg.generated
     */
    public void setPaymentAmount(Long paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     *
     * @return the value of order.user_info
     *
     * @mbg.generated
     */
    public String getUserInfo() {
        return userInfo;
    }

    /**
     *
     * @param userInfo the value for order.user_info
     *
     * @mbg.generated
     */
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo == null ? null : userInfo.trim();
    }

    /**
     *
     * @return the value of order.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address the value for order.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     *
     * @return the value of order.comment
     *
     * @mbg.generated
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * @param comment the value for order.comment
     *
     * @mbg.generated
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     *
     * @return the value of order.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     * @param createTime the value for order.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     * @return the value of order.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     * @param updateTime the value for order.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", produceAmount=").append(produceAmount);
        sb.append(", paymentAmount=").append(paymentAmount);
        sb.append(", userInfo=").append(userInfo);
        sb.append(", address=").append(address);
        sb.append(", comment=").append(comment);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}