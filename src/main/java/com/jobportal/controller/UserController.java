
package com.jobportal.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobportal.entity.User;
import com.jobportal.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // User Registration
    @PostMapping("/register")
    public String registerUser(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register";
        }

        User user = new User();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);

        try {
            userService.registerUser(user);

            model.addAttribute("success",
                    "Registration successful! Your account has been created.");

        } catch (RuntimeException e) {

            model.addAttribute("error", e.getMessage());
        }

        return "register";
    }

    // User Login
    @PostMapping("/login")
    public String loginUser(
            @RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        try {
            User user = userService.loginUser(email, password);

            // Store user details in session
            session.setAttribute("loggedInUserId", user.getId());
            session.setAttribute("loggedInUserName", user.getFullName());
            session.setAttribute("loggedInUserEmail", user.getEmail());

            model.addAttribute("userName", user.getFullName());

            return "dashboard";

        } catch (RuntimeException e) {

            model.addAttribute("error", e.getMessage());

            return "login";
        }
    }

    // User Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}
