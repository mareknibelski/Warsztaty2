package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Exercise {

    private int id;
    private String title;
    private String description;

    public Exercise() {}

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Exercise setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Exercise setDescription(String description) {
        this.description = description;
        return this;
    }

    static public Exercise[] loadAllExercises(Connection conn) throws SQLException {
        ArrayList<Exercise> exercises = new ArrayList<Exercise>();
        String sql = "SELECT * FROM exercise";
        PreparedStatement preStat = conn.prepareStatement(sql);
        ResultSet rs = preStat.executeQuery();
        while (rs.next()) {
            Exercise loadedExercises = new Exercise();
            loadedExercises.id = rs.getInt("id");
            loadedExercises.title = rs.getString("title");
            loadedExercises.description = rs.getString("description");
            exercises.add(loadedExercises);
        }
        Exercise[] uArray = new Exercise[exercises.size()];
        uArray = exercises.toArray(uArray);
        return uArray;
    }

    static public Exercise loadExerciseById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM exercise WHERE id = ?";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, id);
        ResultSet rs = preStat.executeQuery();
        if (rs.next()) {
            Exercise exercise = new Exercise();
            exercise.id = rs.getInt("id");
            exercise.title = rs.getString("title");
            exercise.description = rs.getString("description");
            return exercise;
        }
        return null;
    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM exercise WHERE id = ?";
            PreparedStatement preStat = conn.prepareStatement(sql);
            preStat.setInt(1, this.id);
            preStat.executeUpdate();
            this.id = 0;
        }
    }

    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO exercise(title, description) VALUES(?, ?)";
            String generatedColumns[] = { "id" };
            PreparedStatement preStat = conn.prepareStatement(sql, generatedColumns);
            preStat.setString(1, this.title);
            preStat.setString(2, this.description);
            preStat.executeUpdate();
            ResultSet rs = preStat.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "UPDATE exercise SET title = ?, description = ? WHERE id = ?";
            PreparedStatement preStat = conn.prepareStatement(sql);
            preStat.setString(1, this.title);
            preStat.setString(2, this.description);
            preStat.setInt(3,this.id);
            preStat.executeUpdate();
        }
    }

    @Override
    public String toString() {
        return id + " " + title + " " + description;
    }

}
