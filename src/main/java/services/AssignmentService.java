package services;

import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

public class AssignmentService {
    public void createAssignment(Assignment assignment) {
        String query = "INSERT INTO Assignment (ticket_id, representative_id, assignment_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, assignment.getTicketId());
            stmt.setInt(2, assignment.getRepresentativeId());
            stmt.setTimestamp(3, new Timestamp(assignment.getAssignmentDate().getTime()));
            stmt.setString(4, assignment.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Assignment getAssignment(int assignmentId) {
        String query = "SELECT * FROM Assignment WHERE assignment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, assignmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignmentId(rs.getInt("assignment_id"));
                assignment.setTicketId(rs.getInt("ticket_id"));
                assignment.setRepresentativeId(rs.getInt("representative_id"));
                assignment.setAssignmentDate(rs.getTimestamp("assignment_date"));
                assignment.setStatus(rs.getString("status"));
                return assignment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAssignment(Assignment assignment) {
        String query = "UPDATE Assignment SET ticket_id = ?, representative_id = ?, assignment_date = ?, status = ? WHERE assignment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, assignment.getTicketId());
            stmt.setInt(2, assignment.getRepresentativeId());
            stmt.setTimestamp(3, new Timestamp(assignment.getAssignmentDate().getTime()));
            stmt.setString(4, assignment.getStatus());
            stmt.setInt(5, assignment.getAssignmentId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAssignment(int assignmentId) {
        String query = "DELETE FROM Assignment WHERE assignment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, assignmentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Assignment> getAllAssignments() {
        List<Assignment> assignments = new ArrayList<>();
        String query = "SELECT * FROM Assignment";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignmentId(rs.getInt("assignment_id"));
                assignment.setTicketId(rs.getInt("ticket_id"));
                assignment.setRepresentativeId(rs.getInt("representative_id"));
                assignment.setAssignmentDate(rs.getTimestamp("assignment_date"));
                assignment.setStatus(rs.getString("status"));
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }
}

