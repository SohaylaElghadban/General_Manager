package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class StoreManager {
    private Connection connection;

    public StoreManager(Connection connection) {
        this.connection = connection;
    }

    public void insertStore(String storeCode, String name) {
        String query = "INSERT INTO store (code_, name_) VALUES ( ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            Random stringId = new Random();
            int ID = stringId.nextInt(100000);
            stmt.setInt(1, ID);
            stmt.setString(2, storeCode);
            stmt.setString(3, name);

            stmt.executeUpdate();
            System.out.println("Store inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Store findStore(String storeCode) {
        String query = "SELECT * FROM store WHERE Code_ = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, storeCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Store(rs.getInt("ID"),rs.getString("code_"), rs.getString("name_"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
