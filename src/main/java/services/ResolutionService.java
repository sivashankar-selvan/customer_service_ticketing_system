package services;

import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

public class ResolutionService {
    public void createResolution(Resolution resolution) {
        String query = "INSERT INTO Resolution (ticket_id, resolution_date, resolution_details, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, resolution.getTicketId());
            stmt.setTimestamp(2, new Timestamp(resolution.getResolutionDate().getTime()));
            stmt.setString(3, resolution.getResolutionDetails());
            stmt.setString(4, resolution.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Resolution getResolution(int resolutionId) {
        String query = "SELECT * FROM Resolution WHERE resolution_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, resolutionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Resolution resolution = new Resolution();
                resolution.setResolutionId(rs.getInt("resolution_id"));
                resolution.setTicketId(rs.getInt("ticket_id"));
                resolution.setResolutionDate(rs.getTimestamp("resolution_date"));
                resolution.setResolutionDetails(rs.getString("resolution_details"));
                resolution.setStatus(rs.getString("status"));
                return resolution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateResolution(Resolution resolution) {
        String query = "UPDATE Resolution SET ticket_id = ?, resolution_date = ?, resolution_details = ?, status = ? WHERE resolution_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, resolution.getTicketId());
            stmt.setTimestamp(2, new Timestamp(resolution.getResolutionDate().getTime()));
            stmt.setString(3, resolution.getResolutionDetails());
            stmt.setString(4, resolution.getStatus());
            stmt.setInt(5, resolution.getResolutionId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteResolution(int resolutionId) {
        String query = "DELETE FROM Resolution WHERE resolution_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, resolutionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Resolution> getAllResolutions() {
        List<Resolution> resolutions = new ArrayList<>();
        String query = "SELECT * FROM Resolution";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Resolution resolution = new Resolution();
                resolution.setResolutionId(rs.getInt("resolution_id"));
                resolution.setTicketId(rs.getInt("ticket_id"));
                resolution.setResolutionDate(rs.getTimestamp("resolution_date"));
                resolution.setResolutionDetails(rs.getString("resolution_details"));
                resolution.setStatus(rs.getString("status"));
                resolutions.add(resolution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resolutions;
    }
}
