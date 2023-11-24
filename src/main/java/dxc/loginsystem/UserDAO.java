package dxc.loginsystem;
import java.sql.*;

public class UserDAO {

    private String dbURL = "jdbc:mysql://localhost:3306/loginSystem";
    private String userLogin = "root";
    private String password = "password";

    public static void main(String[] args) {

        // Test the methods here
        //generate a hashed password
        String password = "password";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        //create a admin user for insertion to database
        User user = new User("admin", hashedPassword, "admin");
        UserDAO userDAO = new UserDAO();
        userDAO.createUser(user);


        //create a user willie for insertion to database
        String password2 = "Willie2Testing";
        String hashedPassword2 = BCrypt.hashpw(password2, BCrypt.gensalt());
        User user2 = new User("willie", hashedPassword2, "user");
        userDAO.createUser(user2);

        //retrieve all users from database
        User retrievedUser = userDAO.getUser("admin");
        System.out.println(retrievedUser.getUserName());
        System.out.println(retrievedUser.getPassword());
        System.out.println(retrievedUser.getRole());

        User retrievedUser2 = userDAO.getUser("willie");
        System.out.println(retrievedUser2.getUserName());
        System.out.println(retrievedUser2.getPassword());
        System.out.println(retrievedUser2.getRole());


    }

    // Method to add a new user
    public void createUser(User user) {
        // Implement JDBC code to insert user into the database
        String sqlStatement = "INSERT INTO users (username, hashedPassword, role_id) VALUES (?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(dbURL, userLogin, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            if( user.getRole().equals("admin") ){
                preparedStatement.setInt(3, 0);
            }else{
                preparedStatement.setInt(3, 1);
            }
            preparedStatement.executeUpdate();
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
                String retrievedRole = "";
                int retrievedRole_id = resultSet.getInt("role_id");
                if( retrievedRole_id == 0 ){
                    retrievedRole = "admin";
                }else{
                    retrievedRole = "user";
                }
                user = new User(retrievedUsername, retrievedPassword, retrievedRole);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    // Method to update a user
    public void updateUser(User user) {
        // Implement JDBC code to update user in the database
    }

    // Method to delete a user
    public void deleteUser(String username) {
        // Implement JDBC code to delete user from the database
    }

    // Additional utility methods as required
    public boolean doesUsernameExist(String username) {
        // Implement JDBC code to check if a username exists
        return false; // Placeholder
    }
}
