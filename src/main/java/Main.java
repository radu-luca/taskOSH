import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            connection.setAutoCommit(false);
            ImportCSV.importData(connection);
            connection.commit();
        } catch (SQLException | CsvValidationException | IOException throwable) {
            throwable.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
