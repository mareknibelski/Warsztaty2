package models;


import SoP.ConnToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class Solution {

    private int id;
    private Date created;
    private Date updated;
    private String description;
    private int exercise_id;
    private int user_id;

    public Solution() {}

    public Solution(Date created, Date updated, String description, int exercise_id, int user_id) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise_id = exercise_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public Solution setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Date getUpdated() {
        return updated;
    }

    public Solution setUpdated(Date updated) {
        this.updated = updated;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Solution setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public Solution setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
        return this;
    }

    public int getUser_id() {
        return user_id;
    }

    public Solution setUser_id(int user_id) {
        this.user_id = user_id;
        return this;
    }

    static public Solution[] loadAllSolutions(Connection conn) throws SQLException {
        ArrayList<Solution> solutions = new ArrayList<Solution>();
        String sql = "SELECT * FROM solution";
        PreparedStatement preStat = conn.prepareStatement(sql);
        ResultSet rs = preStat.executeQuery();
        while (rs.next()) {
            Solution loadedSolutions = new Solution();
            loadedSolutions.id = rs.getInt("id");
            loadedSolutions.created = rs.getDate("created");
            loadedSolutions.updated = rs.getDate("updated");
            loadedSolutions.description = rs.getString("description");
            loadedSolutions.exercise_id = rs.getInt("exercise_id");
            loadedSolutions.user_id = rs.getByte("user_id");
            solutions.add(loadedSolutions);
        }
        Solution[] uArray = new Solution[solutions.size()];
        uArray = solutions.toArray(uArray);
        return uArray;
    }

    static public Solution loadSolutionById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM solution WHERE id = ?";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, id);
        ResultSet rs = preStat.executeQuery();
        if (rs.next()) {
            Solution solution = new Solution();
            solution.id = rs.getInt("id");
            solution.created = rs.getDate("created");
            solution.updated = rs.getDate("updated");
            solution.description = rs.getString("description");
            solution.exercise_id = rs.getInt("exercise_id");
            solution.user_id = rs.getInt("user_id");
            return solution;
        }
        return null;
    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM solution WHERE id = ?";
            PreparedStatement preStat = conn.prepareStatement(sql);
            preStat.setInt(1, this.id);
            preStat.executeUpdate();
            this.id = 0;
        }
    }

    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO solution(created, updated, description, exercise_id, user_id) " +
                    "VALUES(?, ?, ?, ?, ?)";
            String generatedColumns[] = { "id" };
            PreparedStatement preStat = conn.prepareStatement(sql, generatedColumns);
            preStat.setDate(1, this.created);
            preStat.setDate(2, this.updated);
            preStat.setString(3, this.description);
            preStat.setInt(4, this.exercise_id);
            preStat.setInt(5, this.user_id);
            preStat.executeUpdate();
            ResultSet rs = preStat.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "UPDATE solution SET created = ?, updated = ?, description = ?, exercise_id = ?, user_id = ? " +
                    "WHERE id = ?";
            PreparedStatement preStat = conn.prepareStatement(sql);
            preStat.setDate(1, this.created);
            preStat.setDate(2, updated);
            preStat.setString(3, this.description);
            preStat.setInt(4, this.exercise_id);
            preStat.setInt(5, this.user_id);
            preStat.setInt(6, this.id);
            preStat.executeUpdate();
        }
    }

    static public Solution[] loadAllByUserId(Connection conn, int user_id) throws SQLException {
        ArrayList<Solution> solutionsByUserId = new ArrayList<Solution>();
        String sql = "SELECT * FROM solution WHERE user_id = ?";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, user_id);
        ResultSet rs = preStat.executeQuery();
        while (rs.next()) {
            Solution loadSolutionByUserId = new Solution();
            loadSolutionByUserId.id = rs.getInt("id");
            loadSolutionByUserId.created = rs.getDate("created");
            loadSolutionByUserId.updated = rs.getDate("updated");
            loadSolutionByUserId.description = rs.getString("description");
            loadSolutionByUserId.exercise_id = rs.getInt("exercise_id");
            loadSolutionByUserId.user_id = rs.getInt("user_id");
            solutionsByUserId.add(loadSolutionByUserId);
        }
        Solution[] uArray = new Solution[solutionsByUserId.size()];
        uArray = solutionsByUserId.toArray(uArray);
        return uArray;
    }

    static public Solution[] loadAllByUserIdDescNull(Connection conn, int user_id) throws SQLException {
        ArrayList<Solution> solutionsByUserId = new ArrayList<Solution>();
        String sql = "SELECT * FROM solution WHERE user_id = ? and description IS NULL";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, user_id);
        ResultSet rs = preStat.executeQuery();
        while (rs.next()) {
            Solution loadSolutionByUserId = new Solution();
            loadSolutionByUserId.id = rs.getInt("id");
            loadSolutionByUserId.created = rs.getDate("created");
            loadSolutionByUserId.updated = rs.getDate("updated");
            loadSolutionByUserId.description = rs.getString("description");
            loadSolutionByUserId.exercise_id = rs.getInt("exercise_id");
            loadSolutionByUserId.user_id = rs.getInt("user_id");
            solutionsByUserId.add(loadSolutionByUserId);
        }
        Solution[] uArray = new Solution[solutionsByUserId.size()];
        uArray = solutionsByUserId.toArray(uArray);
        return uArray;
    }

    static public Solution[] loadAllByExerciseId(Connection conn, int exercise_id) throws SQLException {
        ArrayList<Solution> solutionsByExerciseId = new ArrayList<Solution>();
        String sql = "SELECT * FROM solution WHERE exercise_id = ? ORDER BY created DESC";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, exercise_id);
        ResultSet rs = preStat.executeQuery();
        while (rs.next()) {
            Solution loadSolutionByExerciseId = new Solution();
            loadSolutionByExerciseId.id = rs.getInt("id");
            loadSolutionByExerciseId.created = rs.getDate("created");
            loadSolutionByExerciseId.updated = rs.getDate("updated");
            loadSolutionByExerciseId.description = rs.getString("description");
            loadSolutionByExerciseId.exercise_id =  rs.getInt("exercise_id");
            loadSolutionByExerciseId.user_id = rs.getInt("user_id");
            solutionsByExerciseId.add(loadSolutionByExerciseId);
        }
        Solution[] uArray = new Solution[solutionsByExerciseId.size()];
        uArray = solutionsByExerciseId.toArray(uArray);
        return uArray;
    }

    @Override
    public String toString() {
        return id + " " + created + " " + updated + " " + description + " " + exercise_id + " " + user_id;
    }

}
