package com.onuroztas.expensetracker.controller;

import com.onuroztas.expensetracker.model.User;
import com.onuroztas.expensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/users/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerNewUser(user.getUsername(), user.getPassword(), user.getEmail());
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/api/users/test")
    public ResponseEntity<String> apiTestEndpoint(Authentication authentication) {
        return ResponseEntity.ok("Hello, " + authentication.getName() + "! API Test endpoint working");
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint(Authentication authentication) {
        return ResponseEntity.ok("Hello, " + authentication.getName() + "! Root Test endpoint working");
    }

    @GetMapping("/api/users/secured")
    public ResponseEntity<String> securedEndpoint(Authentication authentication) {
        return ResponseEntity.ok("Hello, " + authentication.getName() + "! This is a secured endpoint.");
    }

    @GetMapping("/api/users/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}