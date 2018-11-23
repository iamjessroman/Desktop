package desktop;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    public String data() {

        testMySQLDriver();
        String nombre = null;
        try {

            String url = "jdbc:mysql://localhost/data";
            String username = "jessica";
            String password = "1234";

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `images`");
            while (rs.next()) {

                int id = rs.getInt("ID");
                nombre = rs.getString("dataimage");

                System.out.println(String.format("%d, %s", id, nombre));
            }

            rs.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return nombre;
    }

    public void datos(int id, String estado) {
        testMySQLDriver();
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/parqueos", "jessica", "1234");
            Statement st = conn.createStatement();

            int i = st.executeUpdate("insert into `estados`(`number`, `estado`) values(" + id + ",'" + estado + "')");
            out.println("Estado Insertado");
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            // create the mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/parqueos";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "jessica", "1234");

            // create the mysql delete statement.
            // i'm deleting the row where the id is "3", which corresponds to my
            // "Barney Rubble" record.
            String query = "delete from estados";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    private static void testMySQLDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Error, no se ha podido cargar MySQL JDBC Driver");
        }
    }

}
