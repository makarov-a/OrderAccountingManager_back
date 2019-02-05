package com.magical.oms.dto;

import javax.validation.constraints.NotNull;

public class DeliveryInfoDto {
    private int id;
    @NotNull
    private String address;
    private float costDelivery;
    private int customerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getCostDelivery() {
        return costDelivery;
    }

    public void setCostDelivery(float costDelivery) {
        this.costDelivery = costDelivery;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
