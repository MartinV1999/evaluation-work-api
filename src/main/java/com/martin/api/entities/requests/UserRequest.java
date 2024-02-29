package com.martin.api.entities.requests;

import com.martin.api.entities.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class UserRequest {
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;
    @NotBlank(message = "El correo electronico no puede estar vacio")
    @Email
    private String email;
    private List<Phone> phoneList;

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
