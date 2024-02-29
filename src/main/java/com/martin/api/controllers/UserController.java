package com.martin.api.controllers;

import com.martin.api.entities.dto.UserDto;
import com.martin.api.entities.requests.UserRequest;
import com.martin.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(originPatterns = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> list(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserRequest userRequest){
        Optional<UserDto> userOp = userService.save(userRequest);
        return ResponseEntity.ok().body(userOp.orElseThrow());
    }
}
