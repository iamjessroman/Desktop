package desktop;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.remote.JMXConnectorFactory.connect;
import javax.swing.JOptionPane;

public class Conexion {

    ClassMain cm = new ClassMain();
    String[] conexion = null;

    @SuppressWarnings("empty-statement")
    public void getCrediantials() {
        String ruta = "./config/conexion.txt";
        try {
            conexion = cm.ReadArray(ruta);
//            Test
//            for (int i = 0; i < conexion.length; i++) {
//                System.out.println(conexion[i]);
//            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha configurado la conexión \n Ir a Pestaña 'Conexión'", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update(String sql, String msg) {
        testMySQLDriver();
        getCrediantials();
        String passLocal = (conexion[11].equals("NONE")) ? "" : conexion[11];
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"
                    + conexion[7] + ":"
                    + conexion[8] + "/"
                    + conexion[9],
                    conexion[10],
                    passLocal);
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Se ha actualizado " + msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "No se ha actualizado " + msg, "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
    
        public void insert(String sql, String[] columns, String msg) {
        testMySQLDriver();
        getCrediantials();
        String passLocal = (conexion[11].equals("NONE")) ? "" : conexion[11];
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"
                    + conexion[7] + ":"
                    + conexion[8] + "/"
                    + conexion[9],
                    conexion[10],
                    passLocal);
            PreparedStatement ps= conn.prepareStatement(sql);
            
            for (int i = 1; i <= columns.length; i++) {
                ps.setString(i, columns[i-1]);
            }
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha guardado " + msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "No se ha guardado " + msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void insertar() {
        testMySQLDriver();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/parkingdb", "root", "");
            Statement st = conn.createStatement();

//            int i = st.executeUpdate("INSERT INTO `detailparklot` (`id_DetailParklot`, `pathImg_DetailParklot`, `pathSegm_DetailParklot`, `id_Park`, `status_DetailParklot`, `dateCreate_DetailParklot`) VALUES ('1', ' " + path + ".jpg', '" + path + ".json', '" + id + "', '1', CURRENT_TIMESTAMP);");
            out.println("Estado Insertado");
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
        }
    }

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

    public int getParking() {

        testMySQLDriver();
        String nombre = null;
        int id = 0;
        try {

            String url = "jdbc:mysql://localhost/parkingdb";
            String username = "root";
            String password = "";

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `parklot` ORDER by `id_Parklot` DESC");
            while (rs.next()) {

                id = rs.getInt("id_Parklot");
                nombre = rs.getString("name_Parklot");

                System.out.println(String.format("%d, %s", id, nombre));
            }

            rs.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return id;
    }

    public void datos() {
        testMySQLDriver();
        try {
            delete();
            GetNames gn = new GetNames();
            String path = gn.listFolder();
            int id = getParking();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/parkingdb", "root", "");
            Statement st = conn.createStatement();

            int i = st.executeUpdate("INSERT INTO `detailparklot` (`id_DetailParklot`, `pathImg_DetailParklot`, `pathSegm_DetailParklot`, `id_Park`, `status_DetailParklot`, `dateCreate_DetailParklot`) VALUES ('1', ' " + path + ".jpg', '" + path + ".json', '" + id + "', '1', CURRENT_TIMESTAMP);");
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
            String myUrl = "jdbc:mysql://localhost/parkingdb";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            // create the mysql delete statement.
            // i'm deleting the row where the id is "3", which corresponds to my
            // "Barney Rubble" record.
            String query = "delete from detailparklot";
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
