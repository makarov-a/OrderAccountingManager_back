package com.magical.oms.dto;

import com.magical.oms.model.Customer;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.sql.Date;
import java.util.List;

public class OrderDto {
    private int id;
    @NotNull
    @PastOrPresent
    private Date creationDate;
    @FutureOrPresent
    private Date deadlineDate;
    private float orderCost;
    private float prepayAmount;
    private String orderComment;
    @NotNull
    private CustomerDto customer;
    private List<ProductDto> productsList;
    private boolean complete;
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

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public List<ProductDto> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<ProductDto> productsList) {
        this.productsList = productsList;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }
}
