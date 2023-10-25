package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        userService.createUsersTable();

        userService.saveUser("Vik", "Kaneki", (byte) 22);
        userService.saveUser("Zak", "Shelby", (byte) 21);
        userService.saveUser("Lolly", "Candy", (byte) 18);
        userService.saveUser("Tom", "Hiber", (byte) 25);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
