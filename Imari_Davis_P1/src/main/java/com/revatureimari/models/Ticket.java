package com.revatureimari.models;

public class Ticket {
    private int ticketId;
    private double amount;
    private String description;
    private TicketStatus ticketStatus;
    private int employeeId;

    public Ticket() {
    }

    public Ticket(int ticketId, double amount, String description, TicketStatus ticketStatus, int employeeId) {
        this.ticketId = ticketId;
        this.amount = amount;
        this.description = description;
        this.ticketStatus = ticketStatus;
        this.employeeId = employeeId;
    }

    public Ticket(double amount, String description, TicketStatus ticketStatus, int employeeId) {
        this.amount = amount;
        this.description = description;
        this.ticketStatus = ticketStatus;
        this.employeeId = employeeId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", ticketStatus=" + ticketStatus +
                ", employeeId=" + employeeId +
                '}';
    }
}

