package com.magical.oms.model;

/**
 * Сущность - Покупатель
 */
public class Customer {
    private int id;
    private String name;
    private String nickname;
    private String phone;
    private String contactComment;

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

    public String getContactComment() {
        return contactComment;
    }

    public void setContactComment(String contactComment) {
        this.contactComment = contactComment;
    }
}
