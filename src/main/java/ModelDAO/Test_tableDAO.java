package ModelDAO;

import Model.Test_table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test_tableDAO {
    PreparedStatement st;

    public Test_tableDAO(Connection connection) throws SQLException {
        String sql = "INSERT INTO test_table VALUES (?,?,?,?,?,?,?,?,?,?);";
        st = connection.prepareStatement(sql);
    }

    public void insert(Test_table test_tabel) throws SQLException {
        st.setString(1, test_tabel.getA());
        st.setString(2, test_tabel.getB());
        st.setString(3, test_tabel.getC());
        st.setString(4, test_tabel.getD());
        st.setString(5, test_tabel.getE());
        st.setString(6, test_tabel.getF());
        st.setDouble(7, test_tabel.getG());
        st.setString(8, test_tabel.getH());
        st.setString(9, test_tabel.getI());
        st.setString(10, test_tabel.getF());
        st.executeUpdate();
    }
}
