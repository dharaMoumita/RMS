package com.example.rms.Service;

import com.example.rms.DTO.UserDTO;
import com.example.rms.Entity.Auth.User;

import java.util.List;

public interface UserService {
    public void initRoleAndUser();
//    public User registerNewUser(String userDTO) throws  Exception;
    public UserDTO addUser(String userDTO) throws Exception;
    public UserDTO getUser(int id);

    public List<UserDTO> getAllUsers();

    public UserDTO deleteUser(int id);

    public UserDTO updateUser(int id, String userDTO) throws Exception;


}
