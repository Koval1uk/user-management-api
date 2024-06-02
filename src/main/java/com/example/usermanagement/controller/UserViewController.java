package com.example.usermanagement.controller;

import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserViewController {

    @Autowired
    private UserService userService;

    @GetMapping("/user-management")
    public String viewUsers(@RequestParam(required = false) Integer userId, Model model) {
        List<User> users;
        if (userId != null) {
            users = userService.getUsersByUserId(userId);
        } else {
            users = userService.getAllUsers();
        }
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/user-management";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/user-management";
    }

    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/user-management";
    }

}

