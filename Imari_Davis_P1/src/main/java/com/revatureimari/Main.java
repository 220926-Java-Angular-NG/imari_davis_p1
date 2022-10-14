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
        app.get("/user/{userId}", userController.getUserById);
        app.post("/user", userController.createNewUser);
        app.put("/user", userController.updateUser);
        app.delete("/user", userController.deleteUser);
        app.delete("/user/{userId}", userController.deleteUserById);

        app.get("/user", userController.loginUser);
        app.get("/user", userController.loginUserByEmail);

        app.get("/tickets", ticketController.getAllTickets);
        app.get("/ticket/{ticketId}", ticketController.getTicketById);
        app.post("/ticket", ticketController.createNewTicket);
        app.put("/ticket", ticketController.updateTicket);
        app.delete("/ticket", ticketController.deleteTicket);
        app.delete("/ticket/{ticketId}", ticketController.deleteTicketById);

        app.get("/tickets/{employeeId}", ticketController.getAllTicketsByUser);
        app.get("/tickets/pending", ticketController.getAllPendingTickets);

    }
}