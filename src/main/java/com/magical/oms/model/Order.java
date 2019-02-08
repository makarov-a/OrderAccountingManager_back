package com.magical.oms.model;

import java.sql.Date;
import java.util.List;

/**
 * Сущность - Заказ
 */
public class Order {
    private int id;
    private Date creationDate;
    private Date deadlineDate;
    private float orderCost;
    private float prepayAmount;
    private String orderComment;
    private int customerId;
    private int deliveryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public float getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(float orderCost) {
        this.orderCost = orderCost;
    }

    public float getPrepayAmount() {
        return prepayAmount;
    }

    public void setPrepayAmount(float prepayAmount) {
        this.prepayAmount = prepayAmount;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }
}
