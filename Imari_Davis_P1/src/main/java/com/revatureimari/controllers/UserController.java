package com.revatureimari.controllers;

import com.revatureimari.Driver;
import com.revatureimari.models.User;
import com.revatureimari.services.UserService;
import io.javalin.http.Handler;

public class UserController {
    private UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Handler createNewUser = context -> {
        if (Driver.getCurrentUser() == null) {
            User user = context.bodyAsClass(User.class);
            int userId = userService.createUser(user);

            if (userId > 0) {
                user.setUserId(userId);
                context.json(user).status(200);
            } else {
                context.result("Could not create user").status(400);
            }
        } else {
            context.result("Current user must be logged out").status(400);
        }
    };

    public Handler getAllUsers = context -> {
        context.json(userService.getAllUsers());
    };

    public Handler getUserById = context -> {
        String param = context.pathParam("userId");

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

        if (Driver.getCurrentUser() != null && Driver.getCurrentUser().getUserId() == user.getUserId()) {
            if (user != null) {
                context.json(user).status(202);
            } else {
                context.result("Could not update user").status(400);
            }
        } else {
            context.result("Invalid user. User data can only be modified by designated user.").status(400);
        }
    };

    public Handler deleteUser = context -> {
        User user = context.bodyAsClass(User.class);

        if (Driver.getCurrentUser() != null && Driver.getCurrentUser().getUserId() == user.getUserId()) {
            if (user != null) {
                context.status(200).json(userService.deleteUser(user));
                Driver.setCurrentUser(null);
            } else {
                context.status(400).result("Could not delete user");
            }
        } else {
            context.result("Invalid user. User data can only be modified by designated user.").status(400);
        }
    };

    public Handler deleteUserById = context -> {
        String param = context.pathParam("userId");

        try {
            int userId = Integer.parseInt(param);

            if (Driver.getCurrentUser() != null && Driver.getCurrentUser().getUserId() == userId) {
                context.json(userService.deleteUserById(userId)).status(202);
            } else {
                context.result("Invalid user. User data can only be modified by designated user.").status(400);
            }

        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
        }
    };

    public Handler loginUser = context -> {
        User user = context.bodyAsClass(User.class);

        try {
            if (Driver.getCurrentUser() == null) {
                Driver.setCurrentUser(userService.loginUser(user));

                if (Driver.getCurrentUser() != null) {
                    context.result("Logged in as " + Driver.getCurrentUser().getFirstName() + " " +
                            Driver.getCurrentUser().getLastName()).status(200);
                } else {
                    context.result("Invalid username or password").status(400);
                }
            } else {
                context.result("Current user must be logged out").status(400);
            }

        } catch (NumberFormatException nfmException) {
            System.out.println(nfmException.getMessage());

        }
    };

    public Handler loginUserByEmail = context -> {
        User user = context.bodyAsClass(User.class);

        try {
            if (Driver.getCurrentUser() == null) {
                Driver.setCurrentUser(userService.loginUserByEmail(user));

                if (Driver.getCurrentUser() != null) {
                    context.result("Logged in as " + Driver.getCurrentUser().getFirstName() + " " +
                            Driver.getCurrentUser().getLastName()).status(200);
                } else {
                    context.result("Invalid email address or password").status(400);
                }
            } else {
                context.result("Current user must be logged out").status(400);
            }

        } catch (NumberFormatException nfmException) {
            System.out.println(nfmException.getMessage());
        }
    };

    public Handler logoutUser = context -> {
        if (Driver.getCurrentUser() != null) {
            Driver.setCurrentUser(null);
            context.result("Successfully logged out").status(200);
        } else {
            context.result("No user is logged in").status(400);
        }
    };
}
