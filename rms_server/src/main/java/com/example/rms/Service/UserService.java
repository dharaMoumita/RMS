package com.example.rms.Service;

import com.example.rms.Entity.Auth.Role;
import com.example.rms.Entity.Auth.User;
import com.example.rms.Entity.Auth.UserDTO;
import com.example.rms.Repo.RoleRepo;
import com.example.rms.Repo.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
@Service
public class UserService {
    @Autowired
    private RoleRepo roleDao;
    @Autowired
    private UserRepo userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    public void initRoleAndUser() {

        Role waiterRole = new Role();
        waiterRole.setRoleName("Waiter");
        waiterRole.setRoleDescription("Waiter role");
        roleDao.save(waiterRole);

        Role managerRole = new Role();
        managerRole.setRoleName("Manager");
        managerRole.setRoleDescription("Manager Role");
        roleDao.save(managerRole);

        User waiterUser = new User();
        waiterUser.setUserName("waiter123");
        waiterUser.setPassword(getEncodedPassword("waiter@123"));
        waiterUser.setEmail("waiter@gmail.com");

        Set<Role> waiterRoles = new HashSet<>();
        waiterRoles.add(waiterRole);
        waiterUser.setRole(waiterRoles);
        System.out.println(waiterUser.getUserName());
        userDao.save(waiterUser);

        User managerUser = new User();
        managerUser.setUserName("manager123");
        managerUser.setPassword(getEncodedPassword("manager@123"));
        managerUser.setEmail("manager@gmail.com");
        Set<Role> managerRoles = new HashSet<>();
        managerRoles.add(managerRole);
        managerUser.setRole(managerRoles);
        userDao.save(managerUser);

        User user1= new User();
        user1.setUserName("vxc");
        user1.setPassword(getEncodedPassword("vxc"));
        user1.setEmail("vxc@gmail.com");
        Set<Role> user1Roles = new HashSet<>();
        user1Roles.add(managerRole);
        user1Roles.add(waiterRole);
        user1.setRole(user1Roles);
        userDao.save(user1);

    }


    public User registerNewUser(String userDTO) throws  Exception{
//        User user=UserDTOtoUser(userDTO);
//        System.out.println(user);
//        return userDao.save(user);
        ObjectMapper ob=new ObjectMapper();
        UserDTO userDTO1=ob.readValue(userDTO,UserDTO.class);
        User user=UserDTOtoUser(userDTO1);
        return user;
    }

    private User UserDTOtoUser(UserDTO userDTO){
        System.out.println(userDTO.getRole());
        User user=new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(getEncodedPassword(userDTO.getPassword()));
        Set<Role> roles=new HashSet<>();
        for(String i: userDTO.getRole()){

            roles.add(roleDao.findByRoleName(i));
        }
        user.setRole(roles);
        System.out.println(user.toString());
        userDao.save(user);
        return user;
    }
//    private UserDTO UsertoUserDTO(User user){
//        UserDTO userDTO=new UserDTO( user.getUserName(), user.getEmail(), user.getPassword());
//        return userDTO;
//    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}

