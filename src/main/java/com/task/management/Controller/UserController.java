package com.task.management.Controller;

import com.task.management.Model.User;
import com.task.management.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        user.setPasswordHash(userService.encodePassword(user.getPasswordHash()));
        return userService.saveUser(user);    }

    // Get a user by username
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    // Login - you can implement JWT authentication here for login
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null && user.getPasswordHash().equals(existingUser.getPasswordHash())) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }
}
