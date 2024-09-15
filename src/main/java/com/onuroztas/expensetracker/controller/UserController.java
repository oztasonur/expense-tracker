package com.onuroztas.expensetracker.controller;

import com.onuroztas.expensetracker.model.User;
import com.onuroztas.expensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerNewUser(user.getUsername(), user.getPassword(), user.getEmail());
        return ResponseEntity.ok("User registered successfully");
    }


    @GetMapping("/me")
    public ResponseEntity<UserDetails> getCurrentUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userDetails);
    }
}
