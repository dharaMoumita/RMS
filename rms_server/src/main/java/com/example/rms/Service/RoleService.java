package com.example.rms.Service;

import com.example.rms.Entity.Auth.Role;

import java.util.List;

public interface RoleService {
    public Role createNewRole(Role role);
    public Role deleteRole(int id);
    public List<Role> getAllRole();
    public Role getRole(int id);
    public Role updateRole(Role role,int id);
}
