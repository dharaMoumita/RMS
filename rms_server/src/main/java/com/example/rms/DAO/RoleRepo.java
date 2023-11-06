package com.example.rms.DAO;

import com.example.rms.Entity.Auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role findByRoleName(String roleName);
}
