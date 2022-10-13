package com.revatureimari.controllers;

import com.revatureimari.models.Ticket;
import com.revatureimari.services.TicketService;
import io.javalin.http.Handler;

public class TicketController {
    TicketService ticketService;

    public TicketController() {
        ticketService = new TicketService();
    }

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public Handler createNewTicket = context -> {
        Ticket ticket = context.bodyAsClass(Ticket.class);
        int ticketId = ticketService.createTicket(ticket);

        if (ticketId > 0) {
            ticket.setTicketId(ticketId);
            context.json(ticket).status(200);
        } else {
            context.result("Could not create ticket").status(400);
        }
    };

    public Handler getAllTickets = context -> {
        context.json(ticketService.getAllTickets());
    };

    public Handler getTicketById = context -> {
        String param = context.pathParam("ticket_id");

        try {
            int ticketId = Integer.parseInt(param);
            Ticket ticket = ticketService.getTicketById(ticketId);

            if (ticket != null) {
                context.json(ticket);
            } else {
                context.result("Could not find ticket").status(400);
            }

        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
        }
    };

    public Handler updateTicket = context -> {
        Ticket ticket = context.bodyAsClass(Ticket.class);
        ticket = ticketService.updateTicket(ticket);

        if (ticket != null) {
            context.json(ticket).status(202);
        } else {
            context.result("Could not update ticket").status(400);
        }
    };

    public Handler deleteTicket = context -> {
        Ticket ticket = context.bodyAsClass(Ticket.class);

        if (ticket != null) {
            context.status(200).json(ticketService.deleteTicket(ticket));
        } else {
            context.status(400).result("Could not delete ticket");
        }
    };

    public Handler deleteTicketById = context -> {
        String param = context.pathParam("ticket_id");

        try {
            int ticketId = Integer.parseInt(param);

            context.json(ticketService.deleteUserById(ticketId)).status(202);

        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
        }
    };
}
