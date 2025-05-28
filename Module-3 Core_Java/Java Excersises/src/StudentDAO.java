import java.sql.*;

public class StudentDAO {
    private static final String URL = "jdbc:sqlite:students.db";

    public void insertStudent(int id, String name) {
        String sql = "INSERT INTO students(id, name) VALUES(?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();

            System.out.println("Student inserted.");

        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }

    public void updateStudent(int id, String newName) {
        String sql = "UPDATE students SET name = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

            System.out.println("Student updated.");

        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        dao.insertStudent(1, "Alice");
        dao.updateStudent(1, "Alicia");
    }
}
