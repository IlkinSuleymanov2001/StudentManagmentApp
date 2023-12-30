package org.example;


import org.example.controller.UserController;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        UserService userService = new UserServiceImpl(userRepository);
        UserController controller = new UserController(userService);
        execute(controller);


    }

    public static void execute(UserController controller) {
        boolean loop = true;
        while (loop){
            switch (menu()){
                case 1-> controller.register();
                case 2-> controller.login();
                case 3-> controller.updateUser();
                case 4-> controller.getUser();
                case 5-> controller.getAllUsers();
                case 6-> controller.deleteUser();
                case 7-> controller.logout();
                case 8-> {
                    System.out.println("exit system..");
                    loop = false;
                }
                default -> System.out.println("incorrect command , please enter new command" );
            }

        }
    }

    public static int menu(){
        Scanner scanner  = new Scanner(System.in);
        System.out.println("""
                
                please enter command(1-8):
                1.register
                2.login 
                3.update
                4.getUser
                5.GetAllUser
                6.delete
                7.logout
                8.exit
                
                """);
        return scanner.nextInt();
    }
}
