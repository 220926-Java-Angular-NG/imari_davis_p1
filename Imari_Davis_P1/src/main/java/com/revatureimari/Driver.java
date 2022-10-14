package com.revatureimari;

import com.revatureimari.controllers.TicketController;
import com.revatureimari.controllers.UserController;
import com.revatureimari.models.User;
import io.javalin.Javalin;

public class Driver {
     private static User currentUser;

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);

        UserController userController = new UserController();
        TicketController ticketController = new TicketController();

        app.get("/users", userController.getAllUsers);
        app.get("/user/{userId}", userController.getUserById);
        app.put("/user", userController.updateUser);
        app.delete("/user", userController.deleteUser);
        app.delete("/user/{userId}", userController.deleteUserById);

        app.post("/register", userController.createNewUser);
        app.get("/login", userController.loginUser);
        app.get("/login-email", userController.loginUserByEmail);
        app.get("/logout", userController.logoutUser);

        app.get("/tickets", ticketController.getAllTickets);
        app.get("/ticket/{ticketId}", ticketController.getTicketById);
        app.post("/ticket", ticketController.createNewTicket);
        app.put("/ticket", ticketController.updateTicket);
        app.delete("/ticket", ticketController.deleteTicket);
        app.delete("/ticket/{ticketId}", ticketController.deleteTicketById);

        app.get("/tickets/{employeeId}", ticketController.getAllTicketsByUser);
        app.get("/pending", ticketController.getAllPendingTickets);
    }

    public static void setCurrentUser(User user) {
        Driver.currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}