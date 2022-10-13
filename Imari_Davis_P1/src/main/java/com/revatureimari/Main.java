package com.revatureimari;

import com.revatureimari.controllers.TicketController;
import com.revatureimari.controllers.UserController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);

        UserController userController = new UserController();
        TicketController ticketController = new TicketController();

        app.get("/users", userController.getAllUsers);
        app.get("/user/{id}", userController.getUserById);
        app.post("/user", userController.createNewUser);
        app.put("/user", userController.updateUser);
        app.delete("/user", userController.deleteUser);
        app.delete("/user/{id}", userController.deleteUserById);

        app.get("/tickets", ticketController.getAllTickets);
        app.get("/ticket/{id}", ticketController.getTicketById);
        app.post("/ticket", ticketController.createNewTicket);
        app.put("/ticket", ticketController.updateTicket);
        app.delete("/ticket", ticketController.deleteTicket);
        app.delete("/ticket/{id}", ticketController.deleteTicketById);
    }
}