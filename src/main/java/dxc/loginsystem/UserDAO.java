package dxc.loginsystem;
import java.sql.*;

public class UserDAO {

    private String dbURL = "jdbc:mysql://localhost:3306/loginSystem";
    private String userLogin = "root";
    private String password = "password";

    // Method to add a new user
    public void createUser(User user) {
        // Implement JDBC code to insert user into the database
        String sqlStatement = "INSERT INTO users (username, hashedPassword, role_id) VALUES (?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(dbURL, userLogin, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, user.getUserName());
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            preparedStatement.setString(2, hashedPassword);
            if( user.getRole().equals("admin") ){
                preparedStatement.setInt(3, 0);
            }else{
                preparedStatement.setInt(3, 1);
            }
            if( preparedStatement.executeUpdate() == 0 ){
                System.out.println("User " + user.getUserName() + " already exists");
            }
            else{
                System.out.println("User " + user.getUserName() + " created successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    // Method to retrieve a user
    public User getUser(String username) {
        User user = null;

        String sqlStatement = "SELECT * FROM users WHERE username = ?";
        try {
            Connection connection = DriverManager.getConnection(dbURL, userLogin, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String retrievedUsername = resultSet.getString("username");
                String retrievedPassword = resultSet.getString("hashedPassword");
                int retrievedRole_id = resultSet.getInt("role_id");
                UserRole role = UserRole.fromRoleId(retrievedRole_id);
                user = new User(retrievedUsername, retrievedPassword, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    // Method to update a user
    public void updateUser(User user) {
        String sqlStatement = "UPDATE users SET hashedPassword = ?, role_id = ? WHERE username = ?";
        try {
            Connection connection = DriverManager.getConnection(dbURL, userLogin, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            preparedStatement.setString(1, hashedPassword);
            preparedStatement.setInt(2, user.getRole().getRoleId());
            preparedStatement.setString(3, user.getUserName());

            if( preparedStatement.executeUpdate() == 0 ){
                System.out.println("User " + user.getUserName() + " does not exist");
                return;
            }
            else{
                System.out.println("User " + user.getUserName() + " update successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to delete a user
    public void deleteUser(String username) {
        String sqlStatement = "DELETE FROM users WHERE username = ?";
        try {
            Connection connection = DriverManager.getConnection(dbURL, userLogin, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, username);
            if( preparedStatement.executeUpdate() == 0 ){
                System.out.println("User " + username + " does not exist");
            }
            else{
                System.out.println("User " + username + " deleted successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
