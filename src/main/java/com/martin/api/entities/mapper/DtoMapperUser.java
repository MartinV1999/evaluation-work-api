package com.martin.api.entities.mapper;

import com.martin.api.entities.User;
import com.martin.api.entities.dto.UserDto;

public class DtoMapperUser {
    private User user;

    private DtoMapperUser(){

    }

    public static DtoMapperUser builder(){
        return new DtoMapperUser();
    }

    public DtoMapperUser setUser(User user){
        this.user = user;
        return this;
    }

    public UserDto build(){
        if(user == null){
            throw new RuntimeException("Debe pasar el entity user!");
        }

        return new UserDto(
            this.user.getId(),
            this.user.getName(),
            this.user.getEmail(),
            this.user.getPhoneList(),
            this.user.getCreatedAt(),
            this.user.getModifiedAt(),
            this.user.getLastLogin(),
            this.user.getActive()
        );
    }

}
