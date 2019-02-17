package desktop;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.json.JSONException;

public class Conexion {

    ClassMain cm = new ClassMain();
    String[] conexion = {"1", "2", "3", "4", "5"};

    @SuppressWarnings("empty-statement")
    public void getCrediantials(int n) {
        String ruta = "./config/conexion.txt";
        String[] con = null;
        try {
            con = cm.ReadArray(ruta);

            //Test
//            for (int i = 0; i < con.length; i++) {
//                System.out.println(con[i]);
//            }
            if (n != 2) {
                conexion[0] = con[1];
                conexion[1] = con[2];
                conexion[2] = con[3];
                conexion[3] = con[4];
                conexion[4] = con[5];
            } else {
                conexion[0] = con[7];
                conexion[1] = con[8];
                conexion[2] = con[9];
                conexion[3] = con[10];
                conexion[4] = con[11];
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha configurado la conexión \n Ir a Pestaña 'Conexión'", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update(String sql, String msg, int n) {
        testMySQLDriver();
        getCrediantials(n);
        String passLocal = (conexion[4].equals("NONE")) ? "" : conexion[4];
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"
                    + conexion[0] + ":"
                    + conexion[1] + "/"
                    + conexion[2],
                    conexion[3],
                    passLocal);
            Statement st = conn.createStatement();
            st.executeUpdate(sql);

            if (!" ".equals(msg)) {
                 JOptionPane.showMessageDialog(null, "Se ha actualizado " + msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }  
        } catch (HeadlessException | SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "No se ha actualizado " + msg, "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void insert(String sql, String[] columns, String msg, int n) {
        testMySQLDriver();
        getCrediantials(n);
        String passLocal = (conexion[4].equals("NONE")) ? "" : conexion[4];
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"
                    + conexion[0] + ":"
                    + conexion[1] + "/"
                    + conexion[2],
                    conexion[3],
                    passLocal);
            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 1; i <= columns.length; i++) {
                ps.setString(i, columns[i - 1]);
            }
            ps.executeUpdate();
            if (!" ".equals(msg)) {
                JOptionPane.showMessageDialog(null, "Se ha guardado " + msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (HeadlessException | SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "No se ha guardado " + msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String[] select(String sql, int columns, int n) throws SQLException {

        testMySQLDriver();
        getCrediantials(n);
        String result = "";
        String passLocal = (conexion[4].equals("NONE")) ? "" : conexion[4];
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"
                    + conexion[0] + ":"
                    + conexion[1] + "/"
                    + conexion[2],
                    conexion[3],
                    passLocal);

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    result += rs.getString(i) + " columns ";
                }
                result += " rows ";
            }
            rs.close();
            statement.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        String[] select = result.split(" rows ");
        return select;
    }

    public void delete(String sql, String msg, int n) {
        testMySQLDriver();
        getCrediantials(n);
        String passLocal = (conexion[4].equals("NONE")) ? "" : conexion[4];
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"
                    + conexion[0] + ":"
                    + conexion[1] + "/"
                    + conexion[2],
                    conexion[3],
                    passLocal);
            Statement st = conn.createStatement();
            st.executeUpdate(sql);

            if (!" ".equals(msg)) {
                JOptionPane.showMessageDialog(null, "Se ha borrado " + msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (HeadlessException | SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "No se ha borrado " + msg, "Error", JOptionPane.ERROR_MESSAGE);

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


    private static void testMySQLDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Error, no se ha podido cargar MySQL JDBC Driver");
        }
    }

}
