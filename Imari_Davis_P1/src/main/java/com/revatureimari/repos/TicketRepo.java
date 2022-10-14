package com.revatureimari.repos;

import com.revatureimari.models.Ticket;
import com.revatureimari.models.TicketStatus;
import com.revatureimari.utils.CRUDDaoInterface;
import com.revatureimari.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepo implements CRUDDaoInterface<Ticket> {
    public Connection connection;

    public TicketRepo() {
        try {
            connection = ConnectionManager.getConnection();
            System.out.println(connection.getSchema());

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public int create(Ticket ticket) {
        try {
            String sql = "INSERT INTO tickets (ticket_id, amount, description, employee_id) values (default,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, ticket.getAmount());
            preparedStatement.setString(2, ticket.getDescription());
            preparedStatement.setInt(3, ticket.getEmployeeId());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();

            return resultSet.getInt(1);

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return 0;
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<Ticket>();

        try {
            String sql = "SELECT * FROM tickets";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(resultSet.getInt("ticket_id"));
                ticket.setAmount(resultSet.getDouble("amount"));
                ticket.setDescription(resultSet.getString("description"));
                ticket.setTicketStatus(TicketStatus.valueOf(resultSet.getString("status")));
                ticket.setEmployeeId(resultSet.getInt("employee_id"));
                tickets.add(ticket);
            }

            return tickets;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    public List<Ticket> getAllByUser(int employeeID) {
        List<Ticket> tickets = new ArrayList<Ticket>();

        try {
            String sql = "SELECT * FROM tickets WHERE employee_id = ? ORDER BY ticket_id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(resultSet.getInt("ticket_id"));
                ticket.setAmount(resultSet.getDouble("amount"));
                ticket.setDescription(resultSet.getString("description"));
                ticket.setTicketStatus(TicketStatus.valueOf(resultSet.getString("status")));
                ticket.setEmployeeId(resultSet.getInt("employee_id"));
                tickets.add(ticket);
            }

            return tickets;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    public List<Ticket> getAllPending() {
        List<Ticket> tickets = new ArrayList<Ticket>();

        try {
            String sql = "SELECT * FROM tickets WHERE status = 'Pending' ORDER BY ticket_id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(resultSet.getInt("ticket_id"));
                ticket.setAmount(resultSet.getDouble("amount"));
                ticket.setDescription(resultSet.getString("description"));
                ticket.setTicketStatus(TicketStatus.valueOf(resultSet.getString("status")));
                ticket.setEmployeeId(resultSet.getInt("employee_id"));
                tickets.add(ticket);
            }

            return tickets;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public Ticket getById(int ticketId) {
        try {
            String sql = "SELECT * FROM tickets WHERE ticket_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticketId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Ticket ticket = new Ticket();

            while (resultSet.next()) {
                ticket.setTicketId(resultSet.getInt("ticket_id"));
                ticket.setAmount(resultSet.getDouble("amount"));
                ticket.setDescription(resultSet.getString("description"));
                ticket.setTicketStatus(TicketStatus.valueOf(resultSet.getString("status")));
                ticket.setEmployeeId(resultSet.getInt("employee_id"));
            }

            return ticket;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public Ticket update(Ticket ticket) {
        try {
            String sql = "UPDATE tickets SET status = ? WHERE ticket_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ticket.getTicketStatus().toString());
            preparedStatement.setInt(2, ticket.getTicketId());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                ticket.setTicketStatus(TicketStatus.valueOf(resultSet.getString("status")));
            }

            return ticket;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public boolean delete(Ticket ticket) {
        try {
            String sql = "DELETE FROM tickets WHERE ticket_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticket.getTicketId());

            //note that false is returned because a resultSet is NOT returned when this statement is executed
            preparedStatement.execute();

            return true;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return false;
    }

    @Override
    public boolean deleteById(int ticketId) {
        // Delete from table-name where id = ?
        try {
            String sql = "DELETE FROM tickets WHERE ticket_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, ticketId);

            // preparedStatement.execute returns a boolean
            // it returns false if the executed statement returns void
            preparedStatement.execute();
            return true;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return false;
    }
}
