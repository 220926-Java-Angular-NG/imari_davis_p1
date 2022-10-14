package com.revatureimari.services;

import com.revatureimari.models.Ticket;
import com.revatureimari.repos.TicketRepo;

import java.util.List;

public class TicketService {
    private TicketRepo ticketRepo;

    public TicketService() {
        ticketRepo = new TicketRepo();
    }

    public TicketService(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public int createTicket(Ticket ticket) {
        return ticketRepo.create(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepo.getAll();
    }

    public List<Ticket> getAllTicketsByUser(int employeeID) {
        return ticketRepo.getAllByUser(employeeID);
    }

    public List<Ticket> getAllPendingTickets() {
        return ticketRepo.getAllPending();
    }

    public Ticket getTicketById(int ticketId) {
        return ticketRepo.getById(ticketId);
    }

    public Ticket updateTicket(Ticket ticket) {
        return ticketRepo.update(ticket);
    }

    public boolean deleteTicket(Ticket ticket) {
        return ticketRepo.delete(ticket);
    }

    public boolean deleteUserById(int ticketId) {
        return ticketRepo.deleteById(ticketId);
    }
}
