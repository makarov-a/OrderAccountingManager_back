package com.magical.oms.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CustomerDto {
    private int id;
    private String name;
    private String nickname;
    private String phone;
    private String comment;
    @NotNull
    private List<DeliveryInfoDto> deliveries;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<DeliveryInfoDto> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryInfoDto> deliveries) {
        this.deliveries = deliveries;
    }
}
