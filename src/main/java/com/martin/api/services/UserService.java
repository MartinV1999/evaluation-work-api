package com.martin.api.services;

import com.martin.api.entities.User;
import com.martin.api.entities.dto.UserDto;
import com.martin.api.entities.requests.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAll();
    Optional<User> findById(Long id);
    Optional<UserDto> findByEmail(String email);
    Optional<UserDto> save(User user);

    Optional<UserDto> update(Long id, UserRequest userRequest);

    boolean delete(Long id);

    boolean existsByEmailAndIdNot(String email , Long id);
    boolean existsByEmail(String email);

    void updateLastLogin(String email);
}
