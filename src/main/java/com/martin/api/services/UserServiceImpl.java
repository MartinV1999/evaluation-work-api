package com.martin.api.services;

import com.martin.api.entities.Role;
import com.martin.api.entities.User;
import com.martin.api.entities.dto.UserDto;
import com.martin.api.entities.mapper.DtoMapperUser;
import com.martin.api.entities.requests.UserRequest;
import com.martin.api.repositories.RoleRepository;
import com.martin.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    @Lazy
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
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmail(email)
            .map(u -> DtoMapperUser.builder().setUser(u).build());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmailAndIdNot(String email, Long id) {
        return userRepository.existsByEmailAndIdNot(email,id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public Optional<UserDto> save(User userRequest) {
        try{
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
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return Optional.empty();
        }

    }

    @Override
    @Transactional
    public Optional<UserDto> update(Long id, UserRequest userRequest) {
        Optional<User> userOp = userRepository.findById(id);
        if(userOp.isPresent()) {
            User userDb = userOp.orElseThrow();

            userDb.setEmail(userRequest.getEmail());
            userDb.setName(userRequest.getName());
            userDb.setPhoneList(userRequest.getPhoneList());
            userDb.setModifiedAt(new Date());

            return Optional.ofNullable(DtoMapperUser.builder().setUser(userRepository.save(userDb)).build());
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void updateLastLogin(String email) {
        Optional<User> userOp = userRepository.findByEmail(email);
        if(userOp.isPresent()){
            userOp.orElseThrow().setLastLogin(new Date());
        }
        userRepository.save(userOp.orElseThrow());
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Optional<User> userOp = userRepository.findById(id);
        if(userOp.isPresent()){
            User user = userOp.orElseThrow();
            user.setActive(false);
            userRepository.save(user);
            return true;
        }
        return false;
    }

}
