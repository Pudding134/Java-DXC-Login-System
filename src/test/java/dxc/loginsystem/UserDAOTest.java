package dxc.loginsystem;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        // Initialize UserDAO
        userDAO = new UserDAO();
        // You might also want to set up a test database connection here
    }


    @Test
    void createUser() {
        User newUser = new User("testUser", "testPassword", UserRole.USER);
        userDAO.createUser(newUser);
        User retrievedUser = userDAO.getUser("testUser");

        assertNotNull(retrievedUser);
        assertEquals("testUser", retrievedUser.getUserName());
        assertTrue(BCrypt.checkpw("testPassword", retrievedUser.getPassword()));
        assertEquals(UserRole.USER, retrievedUser.getRole());
    }

    @Test
    void getUser_ExistingUser() {
        // Create a test user
        String username = "existingUser";
        String password = "password123";
        User newUser = new User(username, password, UserRole.USER);
        userDAO.createUser(newUser);

        // Retrieve the created user
        User retrievedUser = userDAO.getUser(username);
        assertNotNull(retrievedUser, "Retrieved user should not be null");
        assertEquals(username, retrievedUser.getUserName(), "Username should match");
        assertTrue(BCrypt.checkpw(password, retrievedUser.getPassword()), "Passwords should match");
        assertEquals(UserRole.USER, retrievedUser.getRole(), "Roles should match");

        // Cleanup
        userDAO.deleteUser(username);
    }

    @Test
    void getUser_NonExistingUser() {
        // Attempt to retrieve a non-existing user
        User retrievedUser = userDAO.getUser("nonExistingUser");
        assertNull(retrievedUser, "Retrieved user should be null for non-existing user");
    }

    @Test
    void updateUser() {
        // Create a user for this test
        String username = "testUserUpdate";
        String originalPassword = "testPassword";
        User userToUpdate = new User(username, originalPassword, UserRole.USER);
        userDAO.createUser(userToUpdate);

        // Retrieve and update the user
        userToUpdate = userDAO.getUser(username);
        assertNotNull(userToUpdate); // Make sure the user was retrieved
        userToUpdate.setPassword("newPassword");
        userToUpdate.setRole(UserRole.ADMIN);
        userDAO.updateUser(userToUpdate);

        // Retrieve and assert changes
        User updatedUser = userDAO.getUser(username);
        assertNotNull(updatedUser);
        assertTrue(BCrypt.checkpw("newPassword", updatedUser.getPassword()));
        assertEquals(UserRole.ADMIN, updatedUser.getRole());

        // Cleanup
        userDAO.deleteUser(username);
    }


    @Test
    void deleteUser() {
        // Assuming "testUser" is already created
        userDAO.deleteUser("testUser");
        User deletedUser = userDAO.getUser("testUser");
        assertNull(deletedUser);
    }
}
