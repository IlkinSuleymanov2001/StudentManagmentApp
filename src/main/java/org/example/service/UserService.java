package org.example.service;

import org.example.entity.User;
import org.example.dto.UserRegisterDto;


public interface UserService {

    User register(User u);
    void login(UserRegisterDto userRegister);
    void logout(Long id);
    User readUser(Long id );
    User updateUser(long id, User u);
    User  deleteUser(Long id);
    User[] getAllUsers();
}
