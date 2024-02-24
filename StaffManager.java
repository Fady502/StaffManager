//Fady Zaki SDEV200 2/23/2024
import java.sql.*;

public class StaffManager {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/LINK";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Create a statement
            Statement statement = connection.createStatement();

            // View button (View a record with specified ID)
            String viewQuery = "SELECT * FROM Staff WHERE id = ?";
            PreparedStatement viewStatement = connection.prepareStatement(viewQuery);

            // Insert button (Insert a new record)
            String insertQuery = "INSERT INTO Staff VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            // Update button (Update record for specified ID)
            String updateQuery = "UPDATE Staff SET lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);

            // Sample ID for testing
            String sampleID = "123";

            // View operation
            viewStatement.setString(1, sampleID);
            ResultSet resultSet = viewStatement.executeQuery();
            if (resultSet.next()) {
                // Display record
                System.out.println("Last Name: " + resultSet.getString("lastName"));
                System.out.println("First Name: " + resultSet.getString("firstName"));
                System.out.println("MI: " + resultSet.getString("mi"));
                System.out.println("Address: " + resultSet.getString("address"));
                System.out.println("City: " + resultSet.getString("city"));
                System.out.println("State: " + resultSet.getString("state"));
                System.out.println("Telephone: " + resultSet.getString("telephone"));
                System.out.println("Email: " + resultSet.getString("email"));
            } else {
                System.out.println("Record not found.");
            }

            // Insert operation
            insertStatement.setString(1, "NewID");
            insertStatement.setString(2, "NewLastName");
            insertStatement.setString(3, "NewFirstName");
            insertStatement.setString(4, "N");
            insertStatement.setString(5, "NewAddress");
            insertStatement.setString(6, "NewCity");
            insertStatement.setString(7, "NY");
            insertStatement.setString(8, "1234567890");
            insertStatement.setString(9, "new@example.com");
            insertStatement.executeUpdate();

            // Update operation
            updateStatement.setString(1, "UpdatedLastName");
            updateStatement.setString(2, "UpdatedFirstName");
            updateStatement.setString(3, "U");
            updateStatement.setString(4, "UpdatedAddress");
            updateStatement.setString(5, "UpdatedCity");
            updateStatement.setString(6, "CA");
            updateStatement.setString(7, "9876543210");
            updateStatement.setString(8, "updated@example.com");
            updateStatement.setString(9, sampleID);
            updateStatement.executeUpdate();

            // Close connections
            resultSet.close();
            viewStatement.close();
            insertStatement.close();
            updateStatement.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
