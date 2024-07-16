package services;

import models.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

public class TicketService {
    public void createTicket(Ticket ticket) {
        String query = "INSERT INTO Ticket (customer_id, creation_date, issue_description, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ticket.getCustomerId());
            stmt.setTimestamp(2, new Timestamp(ticket.getCreationDate().getTime()));
            stmt.setString(3, ticket.getIssueDescription());
            stmt.setString(4, ticket.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ticket getTicket(int ticketId) {
        String query = "SELECT * FROM Ticket WHERE ticket_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ticketId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(rs.getInt("ticket_id"));
                ticket.setCustomerId(rs.getInt("customer_id"));
                ticket.setCreationDate(rs.getTimestamp("creation_date"));
                ticket.setIssueDescription(rs.getString("issue_description"));
                ticket.setStatus(rs.getString("status"));
                return ticket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateTicket(Ticket ticket) {
        String query = "UPDATE Ticket SET customer_id = ?, creation_date = ?, issue_description = ?, status = ? WHERE ticket_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ticket.getCustomerId());
            stmt.setTimestamp(2, new Timestamp(ticket.getCreationDate().getTime()));
            stmt.setString(3, ticket.getIssueDescription());
            stmt.setString(4, ticket.getStatus());
            stmt.setInt(5, ticket.getTicketId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTicket(int ticketId) {
        String query = "DELETE FROM Ticket WHERE ticket_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ticketId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM Ticket";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(rs.getInt("ticket_id"));
                ticket.setCustomerId(rs.getInt("customer_id"));
                ticket.setCreationDate(rs.getTimestamp("creation_date"));
                ticket.setIssueDescription(rs.getString("issue_description"));
                ticket.setStatus(rs.getString("status"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
