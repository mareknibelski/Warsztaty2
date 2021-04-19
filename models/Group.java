package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {

    private int id;
    private String name;

    public Group() {}

    public Group(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    static public Group[] loadAllGroups(Connection conn) throws SQLException {
        ArrayList<Group> groups = new ArrayList<Group>();
        String sql = "SELECT * FROM user_group";
        PreparedStatement preStat = conn.prepareStatement(sql);
        ResultSet rs = preStat.executeQuery();
        while (rs.next()) {
            Group loadedGroups = new Group();
            loadedGroups.id = rs.getInt("id");
            loadedGroups.name = rs.getString("name");
            groups.add(loadedGroups);
        }
        Group[] uArray = new Group[groups.size()];
        uArray = groups.toArray(uArray);
        return uArray;
    }

    static public Group loadGroupById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM user_group WHERE id = ?";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, id);
        ResultSet rs = preStat.executeQuery();
        if (rs.next()) {
            Group loadedGroup = new Group();
            loadedGroup.id = rs.getInt("id");
            loadedGroup.name = rs.getString("name");
            return loadedGroup;
        }
        return null;
    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM user_group WHERE id = ?";
            PreparedStatement preStat = conn.prepareStatement(sql);
            preStat.setInt(1, this.id);
            preStat.executeUpdate();
            this.id = 0;
        }
    }

    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO user_group(name) VALUES(?)";
            String generatedColumns[] = {"id"};
            PreparedStatement preStat = conn.prepareStatement(sql, generatedColumns);
            preStat.setString(1, this.name);
            preStat.executeUpdate();
            ResultSet rs = preStat.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "UPDATE user_group SET name = ? WHERE id = ?";
            PreparedStatement preStat = conn.prepareStatement(sql);
            preStat.setString(1, this.name);
            preStat.setInt(2, this.id);
            preStat.executeUpdate();
        }
    }

    @Override
    public String toString() {
        return id + " " + name;
    }

}
