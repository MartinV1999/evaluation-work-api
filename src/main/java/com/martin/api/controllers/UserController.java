package com.martin.api.controllers;

import com.martin.api.entities.User;
import com.martin.api.entities.dto.UserDto;
import com.martin.api.entities.requests.UserRequest;
import com.martin.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/users")
@CrossOrigin(originPatterns = "*")
public class UserController {
    @Value("${email.regex}")
    private String emailRegExp;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> list(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Map<String , String> err = validateEmail(user.getEmail());
        if(!err.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
        Optional<UserDto> userOp = userService.save(user);
        if(userOp.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(userOp.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear usuario");

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<User> userOpt = userService.findById(id);

        if (!userRequest.getEmail().equals(userOpt.orElseThrow().getEmail())
                && userService.existsByEmailAndIdNot(userRequest.getEmail(), id)) {
            String[] errors = {"El correo electronico ya esta en uso"};
            return customValidation(errors);
        }
        Optional<UserDto> userOp = userService.update(id, userRequest);
        if(userOp.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(userOp.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al modificar usuario");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(userService.delete(id)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            String[] error = {"No se pudo eliminar el usuario"};
            return ResponseEntity.status(HttpStatus.OK).body(customValidation(error));
        }
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put("Mensaje", err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    private ResponseEntity<?> customValidation(String[] error) {
        Map<String, String> errors = new HashMap<>();
        for(String item : error){
            errors.put("Mensaje", item);
        }
        return ResponseEntity.badRequest().body(errors);
    }
    private Map<String, String> validateEmail(String email) {
        Map<String, String> errors = new HashMap<>();
        Pattern pattern = Pattern.compile(emailRegExp);
        Matcher mather = pattern.matcher(email);
        if(!mather.find()){
            errors.put("Mensaje", "El correo no es valido");
        }
        return errors;
    }
}
