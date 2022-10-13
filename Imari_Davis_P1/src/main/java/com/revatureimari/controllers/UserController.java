package com.revatureimari.controllers;

import com.revatureimari.models.User;
import com.revatureimari.services.UserService;
import io.javalin.http.Handler;

public class UserController {
    UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Handler createNewUser = context -> {

        User user = context.bodyAsClass(User.class);
        int userId = userService.createUser(user);

        if (userId > 0) {
            user.setUserId(userId);
            context.json(user).status(200);
        } else {
            context.result("Could not create user").status(400);
        }
    };

    public Handler getAllUsers = context -> {
        context.json(userService.getAllUsers());
    };

    public Handler getUserById = context -> {
        String param = context.pathParam("user_id");

        try {
            int userId = Integer.parseInt(param);
            User user = userService.getUserById(userId);

            if (user != null) {
                context.json(user);
            } else {
                context.result("User not found").status(400);
            }

        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
        }
    };

    public Handler updateUser = context -> {
        User user = context.bodyAsClass(User.class);
        user = userService.updateUser(user);

        if (user != null) {
            context.json(user).status(202);
        } else {
            context.result("Could not update user").status(400);
        }
    };

    public Handler deleteUser = context -> {
        User user = context.bodyAsClass(User.class);

        if (user != null) {
            context.status(200).json(userService.deleteUser(user));
        } else {
            context.status(400).result("Could not delete user");
        }
    };

    public Handler deleteUserById = context -> {
        String param = context.pathParam("user_id");

        try {
            int userId = Integer.parseInt(param);

            context.json(userService.deleteUserById(userId)).status(202);

        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
        }
    };
}
