package com.example.rms.Controller;

import com.example.rms.Entity.Auth.Role;
import com.example.rms.Service.RoleService;
import com.example.rms.Service.ServiceImpl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }

    @PutMapping("/role/{id}")
    public Role updateRole(@RequestBody Role role,@PathVariable int id){
        return roleService.updateRole(role,id);
    }

    @GetMapping("/role/{id}")
    public Role getRole(@PathVariable int id){
        return roleService.getRole(id);
    }

    @GetMapping("/role")
    public List<Role> getAllRole(){
        return roleService.getAllRole();
    }

    @DeleteMapping("/role/{id}")
    public Role deleteRole(@PathVariable int id){
        return roleService.deleteRole(id);
    }
}
