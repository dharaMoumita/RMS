package com.example.rms.Controller;

import com.example.rms.Entity.Auth.User;
import com.example.rms.Entity.Auth.UserDTO;
import com.example.rms.Service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }
    @PostMapping("/registerNewUser")
    public UserDTO registerNewUser(@RequestBody String user)throws Exception {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Manager')")
    public String forAdmin(){
        return "Admin URl";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('Waiter')")
    public String forUser(){
        return "User URL";
    }
    @GetMapping("/forboth")
    @PreAuthorize("hasRole('Waiter') || hasRole('Manager')")
    public String forBoth(){
        return "This URL is only accessible to the both";
    }

}
