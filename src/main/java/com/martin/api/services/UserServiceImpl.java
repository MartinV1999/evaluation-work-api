package com.martin.api.services;

import com.martin.api.entities.Role;
import com.martin.api.entities.User;
import com.martin.api.entities.dto.UserDto;
import com.martin.api.entities.mapper.DtoMapperUser;
import com.martin.api.entities.requests.UserRequest;
import com.martin.api.repositories.RoleRepository;
import com.martin.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(u -> DtoMapperUser
            .builder()
            .setUser(u).build())
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmail(email)
            .map(u -> DtoMapperUser.builder().setUser(u).build());
    }

    @Override
    public Optional<UserDto> update(UserRequest userRequest) {
        Optional<User> userOp = userRepository.findByEmail(userRequest.getEmail());
        if(userOp.isPresent()){
            userOp.orElseThrow().setLastLogin(new Date());
        }
        return Optional.ofNullable(DtoMapperUser.builder().setUser(userOp.orElseThrow()).build());
    }

    @Override
    public Optional<UserDto> updateLastLogin(String email) {
        Optional<User> userOp = userRepository.findByEmail(email);
        if(userOp.isPresent()){
            userOp.orElseThrow().setLastLogin(new Date());
        }
        return Optional.ofNullable(DtoMapperUser.builder().setUser(userOp.orElseThrow()).build());
    }

    @Override
    @Transactional
    public Optional<UserDto> save(UserRequest userRequest) {

        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        optionalRoleUser.ifPresent(roles::add);

        if(userRequest.getPassword() != null){
            userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
         User user = userRepository.save(new User(
            userRequest.getName(),
            userRequest.getEmail(),
            userRequest.getPassword(),
            roles,
            userRequest.getPhoneList(),
            true
        ));

        return Optional.ofNullable(DtoMapperUser.builder().setUser(user).build());
    }




}
