package org.example.service.impl;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.dto.UserRegisterDto;


public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User u) {
        if (isPasswordNotValid(u) || isEmailsNotUnique(u)) return null;
        System.out.println("user register successfully..");
        return userRepository.addUser(u);
    }

    @Override
    public void login(UserRegisterDto userRegister) {
        User u = userRepository.findByEmail(userRegister.getEmail());
        if (u == null) {
            System.out.println("not found user by email " + userRegister.getEmail());
            return;
        }
        if (!u.getPassword().equals(userRegister.getPassword())) {
            System.out.println("password is incorrect : " + userRegister.getPassword());
            return;
        }
        u.setActive(true);
        System.out.println("user  login  successfully");
    }

    @Override
    public void logout(Long id) {
        User u = userRepository.findUsersById(id);
        if (u == null) {
            System.out.println("user not found by id " + id);
            return;
        }
        if (isLoginUser(u)) {
            u.setActive(false);
            System.out.println("logout user by name " + u.getName());
        }

    }

    private boolean isPasswordNotValid(User u) {
        if (u.getPassword().length() < 4) {
            System.out.println("User password  must be  at least  4 character ");
            return true;
        }
        return false;
    }

    private boolean isEmailsNotUnique(User u) {
        for (int i = 0; i < userRepository.userCount; i++) {
            if (userRepository.getUsers()[i].getEmail().equals(u.getEmail())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User readUser(Long id) {
        User user = userRepository.findUsersById(id);
        if (user == null) {
            System.out.println("not find user by id " + id);
            return null;
        }
        if (!isLoginUser(user)) {
            return null;
        }
        return user;

    }

    private boolean isLoginUser(User u) {
        if (u.isActive()) return true;
        System.out.println("user is not login , please..login account or register other account  ");
        return false;

    }

    private boolean isLoginUser(long id) {
        User u = userRepository.findUsersById(id);
        if (u == null) {
            System.out.println("no user this id" + id);
            return true;
        }
        if (u.isActive()) return true;
        System.out.println("user is not login , please..login account or register other account  ");
        return false;

    }

    @Override
    public User updateUser(long id, User u) {

        if (isPasswordNotValid(u) || isEmailsNotUnique(u)) {
            return null;
        }
        if (!isLoginUser(id)){
            System.out.println("user firstly  must be login account!!");
            return null;
        }
        System.out.println(" update user...");
        return userRepository.updateUser(id, u);
    }

    @Override
    public User deleteUser(Long id) {
        if (!isLoginUser(id)) {
            return null;
        }
        System.out.println("delete user ..");
        return userRepository.removeUser(id);
    }

    @Override
    public User[] getAllUsers() {
        return userRepository.getUsers();
    }
}
