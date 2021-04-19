package models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private int user_group_id;

    public User() {}

    public User(String username, String password, String email, int user_group_id) {
        this.username = username;
        this.setPassword(password);
        this.email = email;
        this.user_group_id = user_group_id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getUser_group_id() {
        return user_group_id;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setPassword(String password) {
        this.password = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setUser_Group_Id(int user_group_id) {
        this.user_group_id = user_group_id;
        return this;
    }

    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0 ) {
            String sql = "INSERT INTO user(username, email, password, user_group_id) VALUES(?, ?, ?, ?)";
            String generatedColumns[] = { "id" };
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.setInt(4, this.user_group_id);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        }   else {
            String sql = "UPDATE user SET username = ?, email = ?, password = ?, user_group_id = ? WHERE id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.setInt(4, this.user_group_id);
            preparedStatement.setInt(5, this.id);
            preparedStatement.executeUpdate();
        }
    }

    static public User loadUserById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM user WHERE id = ?";
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getInt("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            loadedUser.user_group_id = resultSet.getInt("user_group_id");
            return loadedUser;
        }
        return null;
    }

    static public User[] loadAllUsers(Connection conn) throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM user";
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getInt("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            loadedUser.user_group_id = resultSet.getInt("user_group_id");
            users.add(loadedUser);
        }
        User[] uArray = new User[users.size()];
        uArray = users.toArray(uArray);
        return uArray;
    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM user WHERE id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }

    static public User[] loadAllByGroupId(Connection conn, int user_group_id) throws SQLException {
        ArrayList<User> usersByGroupId = new ArrayList<User>();
        String sql = "SELECT * FROM user WHERE user_group_id = ?";
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, user_group_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User loadUserByGroupId = new User();
            loadUserByGroupId.id = resultSet.getInt("id");
            loadUserByGroupId.username = resultSet.getString("username");
            loadUserByGroupId.email = resultSet.getString("email");
            loadUserByGroupId.password = resultSet.getString("password");
            loadUserByGroupId.user_group_id = resultSet.getInt("user_group_id");
            usersByGroupId.add(loadUserByGroupId);
        }
        User[] uArray = new User[usersByGroupId.size()];
        uArray = usersByGroupId.toArray(uArray);
        return uArray;
    }

    @Override
    public String toString() {
        return id + " " + username + " " + password + " " + email;
    }
}
