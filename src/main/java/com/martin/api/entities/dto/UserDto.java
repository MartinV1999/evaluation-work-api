package com.martin.api.entities.dto;

import com.martin.api.entities.Phone;

import java.util.List;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private List<Phone> phoneList;

    public UserDto(Long id, String name, String email, List<Phone> phoneList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneList = phoneList;
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
}
