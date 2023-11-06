package com.example.rms.Controller;

import com.example.rms.DTO.UserDTO;
import com.example.rms.Entity.Auth.User;
import com.example.rms.Service.ServiceImpl.UserServiceImpl;
import com.example.rms.Service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

//    @PostConstruct
//    public void initRoleAndUser() {
//        userService.initRoleAndUser();
//    }


    @PostMapping("/user")
    public UserDTO registerNewUser(@RequestBody String user)throws Exception {
        return userService.addUser(user);
    }

    @GetMapping("/user/{id}")
    public UserDTO getUser(@PathVariable int id){
        return  userService.getUser(id);
    }

    @GetMapping("/user")
    public List<UserDTO> getAllUser(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/user/{id}")
    public UserDTO deleteUser(int id){
        return userService.deleteUser(id);
    }

    @PutMapping("/user/{id}")
    public UserDTO updateUser(@PathVariable int id,@RequestBody String userDTO)throws Exception{
        return userService.updateUser(id,userDTO);
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
