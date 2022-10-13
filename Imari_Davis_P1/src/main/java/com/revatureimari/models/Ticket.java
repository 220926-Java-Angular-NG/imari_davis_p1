package com.revature.models;

public class Ticket {
    private int ticketId;
    private String description;
    private double reimbursement;
    private TicketStatus ticketStatus;
    private String response;
    private int employeeId;

    public Ticket() {
    }

    public Ticket(int ticketId, String description, double reimbursement, TicketStatus ticketStatus,
                  String response, int employeeId) {
        this.ticketId = ticketId;
        this.description = description;
        this.reimbursement = reimbursement;
        this.ticketStatus = ticketStatus;
        this.response = response;
        this.employeeId = employeeId;
    }

    public Ticket(String description, double reimbursement, TicketStatus ticketStatus, String response,
                  int employeeId) {
        this.description = description;
        this.reimbursement = reimbursement;
        this.ticketStatus = ticketStatus;
        this.response = response;
        this.employeeId = employeeId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(double reimbursement) {
        this.reimbursement = reimbursement;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
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
                ", description='" + description + '\'' +
                ", reimbursement=" + reimbursement +
                ", ticketStatus=" + ticketStatus +
                ", response='" + response + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}

