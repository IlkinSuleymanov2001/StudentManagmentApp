package org.example.controller;

import org.example.dto.UserRegisterDto;
import org.example.entity.User;
import org.example.service.UserService;

import java.util.Arrays;
import java.util.Scanner;

public class UserController {

    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);

    private String input(String message) {
        System.out.print(message);
        return scanner.next();
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User register() {
        Long id = Long.valueOf(input("Enter id: "));
        String name = input("Enter name: ");
        String email = input("enter email :");
        String password = input("enter password :");
        return userService.register(new User(id, name, email, password));
    }

    public void login() {
        String email = input("enter email: ");
        String password = input("enter password: ");
        UserRegisterDto userRegisterDto = new UserRegisterDto(email, password);
        userService.login(userRegisterDto);
    }

    public void logout() {
        userService.logout(Long.valueOf(input("Enter id: ")));
    }

    public User getUser() {
        Long id = Long.valueOf(input("Enter id: "));

        User u = userService.readUser(id);
        System.out.println(u);
        return u;

    }

    public User updateUser() {
        long id = Long.parseLong(input("Enter id: "));
        String name = input("Enter name: ");
        String email = input("enter email :");
        String password = input("enter password :");

        return userService.updateUser(id, new User(name, email, password));
    }

    public User deleteUser() {
        long id = Long.parseLong(input("Enter id"));
        return userService.deleteUser(id);
    }

    public User[] getAllUsers() {
        User[] users = userService.getAllUsers();
        for (User user : users) {
            if (user != null) {
                System.out.println(user);
            }
        }
        return users;
    }

}
