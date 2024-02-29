package com.martin.api.services;

import com.martin.api.entities.User;
import com.martin.api.entities.dto.UserDto;
import com.martin.api.entities.requests.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAll();
    Optional<UserDto> findByEmail(String email);
    Optional<UserDto> save(UserRequest userRequest);

    Optional<UserDto> update(UserRequest userRequest);

    Optional<UserDto> updateLastLogin(String email);
}
