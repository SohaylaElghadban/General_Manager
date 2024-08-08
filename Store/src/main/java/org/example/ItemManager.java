package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemManager {
    private Connection connection;

    public ItemManager(Connection connection) {
        this.connection = connection;
    }

    public void insertItem(String itemCode, String name) {
        String query = "INSERT INTO item (ID,code_, name_) VALUES (?,?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            Random stringId = new Random();
            int ID = stringId.nextInt(100000);
            stmt.setInt(1, ID);
            stmt.setString(2, itemCode);
            stmt.setString(3, name);
            stmt.executeUpdate();
            System.out.println("Item inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public item findItem(String itemCode) {
        String query = "SELECT * FROM item WHERE _code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, itemCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new item(rs.getInt("ID"),rs.getString("code_"), rs.getString("name_"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<item> findItemsByNameContains(String name) {
        List<item> items = new ArrayList<>();
        String query = "SELECT * FROM item WHERE _name LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                items.add(new item(rs.getInt("ID"),rs.getString("itemCode"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
