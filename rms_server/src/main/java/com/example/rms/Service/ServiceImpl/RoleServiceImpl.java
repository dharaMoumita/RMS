package com.example.rms.Service.ServiceImpl;

import com.example.rms.Entity.Auth.Role;
import com.example.rms.DAO.RoleRepo;
import com.example.rms.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role createNewRole(Role role) {
        return roleRepo.save(role);

    }

    @Override
    public Role deleteRole(int id) {
        Role role1=roleRepo.findById(id).get();
        roleRepo.deleteById(id);
        return role1;
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepo.findAll();
    }

    @Override
    public Role getRole(int id) {
        return roleRepo.findById(id).get();
    }

    @Override
    public Role updateRole(Role role, int id) {
        Role role1=roleRepo.findById(id).get();
        role1.setRoleName(role.getRoleName());
        role1.setRoleDescription(role.getRoleDescription());
        role1.setId(id);
        return role1;
    }
}
