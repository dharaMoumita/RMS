package com.example.rms.Service.ServiceImpl;

import com.example.rms.Entity.Auth.Role;
import com.example.rms.Entity.Auth.User;
import com.example.rms.DTO.UserDTO;
import com.example.rms.DAO.RoleRepo;
import com.example.rms.DAO.UserRepo;
import com.example.rms.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
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

    @Override
    public UserDTO addUser(String userDTO) throws Exception{

            ObjectMapper ob=new ObjectMapper();
        UserDTO userDTO1=ob.readValue(userDTO,UserDTO.class);
            System.out.println(".................................."+userDTO1);
           User user1=UserDTOtoUser(userDTO1);
            System.out.println("......................................."+user1);
           userDao.save(user1);
            System.out.println("......................................................"+UsertoUserDTO(user1));
           return UsertoUserDTO(user1);


    }

    @Override
    public UserDTO getUser(int id) {
        User user=userDao.findById(id).get();
        return UsertoUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList=userDao.findAll();
        List<UserDTO> userDTOList=new ArrayList<>();
        userList.forEach((user)->{
            userDTOList.add(UsertoUserDTO(user));
        });
        return userDTOList;
    }

    @Override
    public UserDTO deleteUser(int id) {
        User user=userDao.findById(id).get();
        userDao.deleteById(id);

        return UsertoUserDTO(user);

    }

    @Override
    public UserDTO updateUser(int id, String userDTO) throws Exception {
        ObjectMapper ob=new ObjectMapper();
        UserDTO userDTO1=ob.readValue(userDTO,UserDTO.class);
        User user=UserDTOtoUser(userDTO1);
        user.setId(id);
        userDao.save(user);
        return UsertoUserDTO(user);

    }

    @Override
    public UserDTO getCurrentUser() {
        return UsertoUserDTO(currentUser());
    }


//    public User registerNewUser(String userDTO) throws  Exception{
////        User user=UserDTOtoUser(userDTO);
////        System.out.println(user);
////        return userDao.save(user);
//        ObjectMapper ob=new ObjectMapper();
//        UserDTO userDTO1=ob.readValue(userDTO,UserDTO.class);
//        User user=UserDTOtoUser(userDTO1);
//
//
//        return user;
//    }
private User currentUser(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    User user=userDao.findByUserName(username);
    return user;
}
    private User UserDTOtoUser(UserDTO userDTO){
        System.out.println(userDTO);
        User user=new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(getEncodedPassword(userDTO.getPassword()));
        Set<Role> roles=new HashSet<>();
        for(String i: userDTO.getRole()){

            roles.add(roleDao.findByRoleName(i));
        }
        user.setRole(roles);
        System.out.println(user);
        return user;
    }
    private UserDTO UsertoUserDTO(User user){
        UserDTO userDTO=new UserDTO( );
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        Set<Role> roleSet=user.getRole();
        List<String> rolList =new ArrayList<>();
        roleSet.forEach((role)->{
            rolList.add(role.getRoleName());
        });
        String[] roleArray=new String[rolList.size()];
        roleArray=rolList.toArray(roleArray);
        userDTO.setRole(roleArray);
        return userDTO;
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}

