import java.sql.*;
import java.util.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/StudySpaceDatabase"; 
    private static final String USER = "joseg";       
    private static final String PASS = "pass123";         

    // Connection 
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Check login credentials
    public static boolean checkLogin(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve study space names
    public static List<String> getStudySpaces() {
        List<String> list = new ArrayList<>();
        String query = "SELECT spaceName FROM StudySpaces";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(rs.getString("spaceName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Retrieve details for space
    public static Map<String, String> getStudySpaceDetails(String spaceName) {
        Map<String, String> info = new HashMap<>();
        String query = "SELECT * FROM StudySpaces WHERE spaceName = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, spaceName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                info.put("spaceName", rs.getString("spaceName"));
                info.put("location", rs.getString("location"));
                info.put("description", rs.getString("description"));
                info.put("photoPath", rs.getString("photoPath"));
                info.put("indoorsOrOutdoors", rs.getString("indoorsOrOutdoors"));
                info.put("noiseLevel", rs.getString("noiseLevel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    // Register new user
    public static boolean registerUser(String fullName, String email, String username, String password) {
        String query = "INSERT INTO Users (fullName, email, username, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, username);
            stmt.setString(4, password);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
