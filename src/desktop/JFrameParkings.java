/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONException;

/**
 *
 * @author Tesis
 */
public class JFrameParkings extends javax.swing.JFrame {

    Conexion cx = new Conexion();
    private List<JLabel> imagenes;
    private List<JLabel> titulos;
    private List<JComboBox> estados;
    private List<ImageIcon> images;
    private int indice;
    String[] mix = null;
    String[] namemixs = null;
    String[] id_parklots = null;
    String[] id = null;
    ClassMain cm = new ClassMain();

    /**
     * Creates new form Parqueos
     */
    public JFrameParkings() {
        initComponents();
        this.setDefaultCloseOperation(JFrameTest.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        imagenes = new ArrayList<>();
        estados = new ArrayList<>();
        images = new ArrayList<>();
        titulos = new ArrayList<>();
        indice = 0;

    }

    public void images() throws MalformedURLException, IOException, SQLException {

        String sql = "SELECT `name` FROM `mix`";
        int n = 1;
        String[] temp = cx.select(sql, n, 2);
        namemixs = new String[(temp.length) + 1];
        //this.Parkings_MixFilters.removeAll();

        for (int i = 0; i < temp.length; i++) {
            String[] substring = temp[i].split(" columns ");
            namemixs[i] = substring[0];
            this.Parkings_MixFilters.addItem(namemixs[i]);
        }

        sql = "SELECT `name_parking`, `path_parking`, `id_parklot`, `data_url` ,`id` FROM `parklots`";
        n = 5;
        temp = cx.select(sql, n, 2);
        String[] data_url = new String[(temp.length)];
        String name_parking = "", path_parking = "";
        id_parklots = new String[(temp.length)];
        id = new String[(temp.length)];

        for (int i = 0; i < temp.length; i++) {
            String[] substring = temp[i].split(" columns ");
            name_parking = substring[0];
            path_parking = substring[1];
            id_parklots[i] = substring[2];
            String[] data_image = substring[3].split("data:image/jpeg;base64,");
            data_url[i] = data_image[1];
            //System.out.println(data_url[i]);
            id[i] = substring[4];
        }

        this.Parkings_Tittle.setText(name_parking + " (" + path_parking + ")");

        sql = "SELECT `path` FROM `settings`";
        n = 1;
        temp = cx.select(sql, n, 2);
        String[] substring = temp[0].split(" columns ");
        String res = substring[0];
        String dir = res.replace("/", "\\");

        String path_folder = dir + "\\" + name_parking + "\\" + path_parking+ "\\" ;

        File folder = new File(path_folder);
        folder.mkdirs();

        //System.out.println("tamaño "+data_url.length);
        for (int j = 0; j < data_url.length; j++) {
            //convert base64 string to binary data
            byte[] data = DatatypeConverter.parseBase64Binary(data_url[j]);
            String path = path_folder + "image" + id_parklots[j] + ".png";
            File file = new File(path);
            try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                outputStream.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }

            JLabel titulo = new JLabel("Parqueo: " + id_parklots[j]);
            titulo.setFont(this.getFont());
            this.jPanel1.add(titulo);
            ImageIcon icon = new ImageIcon(path_folder + "image" + id_parklots[j] + ".png");
            icon.setDescription(path_folder + "image" + id_parklots[j] + ".png");
            this.images.add(icon);
            JLabel etiqueta = new JLabel("Etiqueta " + indice);
            etiqueta.setText(null);
            etiqueta.setSize(150, 150);
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(etiqueta.getWidth(), etiqueta.getHeight(), Image.SCALE_DEFAULT));
            etiqueta.setIcon(icono);
            this.jPanel1.add(etiqueta);
            imagenes.add(etiqueta);
            JComboBox combo = new JComboBox();
            combo.setFont(this.getFont());
            combo.addItem("Ocupado");
            combo.addItem("Libre");
            estados.add(combo);
            this.jPanel1.add(combo);
            indice++;
            this.jPanel1.updateUI();

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Parkings_MixFilters = new javax.swing.JComboBox<>();
        Parkings_Tittle = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/play_icon.png"))); // NOI18N
        jButton1.setText("Continuar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(0, 3, 10, 10));
        jScrollPane1.setViewportView(jPanel1);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(38, 91, 145));
        jLabel1.setText("Combinación de Filtro");

        Parkings_MixFilters.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        Parkings_Tittle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Parkings_Tittle.setForeground(new java.awt.Color(74, 173, 82));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/car_icon.png"))); // NOI18N

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setForeground(new java.awt.Color(38, 91, 145));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/upload_icon.png"))); // NOI18N
        jMenu1.setText("Cargar");
        jMenu1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenu1.setHideActionText(true);
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(Parkings_MixFilters, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(806, 806, 806)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Parkings_Tittle, javax.swing.GroupLayout.PREFERRED_SIZE, 1155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Parkings_Tittle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Parkings_MixFilters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked

        try {
            this.images();
        } catch (IOException ex) {
            Logger.getLogger(JFrameParkings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JFrameParkings.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenu1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


            String Text = "";
            for (int i = 0; i < estados.size(); i++) {
                JComboBox est = estados.get(i);
                Text = (String) est.getSelectedItem();
                String sql = "UPDATE `parklots` SET `type`= '" + Text + "'WHERE `id`='" + id[i] + "'";
                cx.update(sql, " ", 2);
            }
            
            ClassExecutors ce = new ClassExecutors();
            ce.RUN(2,images);

//            Images img = new Images();
//            Images.getImage(null);

//            JFrameMain s = new JFrameMain();
//            this.setVisible(false);
//            s.setVisible(true);




    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameParkings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameParkings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameParkings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameParkings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameParkings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Parkings_MixFilters;
    private javax.swing.JLabel Parkings_Tittle;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
