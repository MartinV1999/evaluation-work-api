package com.martin.api.entities.dto;

import com.martin.api.entities.Phone;

import java.util.Date;
import java.util.List;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private List<Phone> phoneList;
    private Date createdAt;
    private Date modifiedAt;
    private Date lastLogin;
    private Boolean isActive;

    public UserDto(Long id, String name, String email, List<Phone> phoneList,Date createdAt, Date modifiedAt, Date lastLogin, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneList = phoneList;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.lastLogin = lastLogin;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
