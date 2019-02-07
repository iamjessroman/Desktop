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
    private int indice;
    String[] mix = null;
    String[] namemixs = null;
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

        sql = "SELECT `name_parking`, `path_parking`, `id_parklot`, `data_url` FROM `parklots`";
        n = 4;
        temp = cx.select(sql, n, 2);
        String[] data_url = new String[(temp.length)];
        String name_parking="", path_parking = "";
        String[] id_parklots = new String[(temp.length)];
        for (int i = 0; i < temp.length; i++) {
            String[] substring = temp[i].split(" columns ");
            name_parking=substring[0];
            path_parking=substring[1];
            id_parklots[i]=substring[2];
            String[] data_image = substring[3].split("data:image/jpeg;base64,");
            data_url[i] = data_image[1];
            //System.out.println(data_url[i]);
        }
        
        this.Parkings_Tittle.setText(name_parking+" ("+path_parking+")");

        //System.out.println("tamaño "+data_url.length);
        for (int j = 0; j < data_url.length; j++) {
            //convert base64 string to binary data
            byte[] data = DatatypeConverter.parseBase64Binary(data_url[j]);
            String path = "./data/image" + id_parklots[j] + ".png";
            File file = new File(path);
            try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                outputStream.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }

            sql = "SELECT `path` FROM `settings`";
            n = 1;
            temp = cx.select(sql, n, 2);
            String[] substring = temp[0].split(" columns ");
            String res = substring[0];
            String dir = res.replace("/", "\\");
            JLabel titulo = new JLabel("Parqueo: " + id_parklots[j]);
            titulo.setFont(this.getFont());
            this.jPanel1.add(titulo);
            ImageIcon icon = new ImageIcon(dir + "\\image" + id_parklots[j] + ".png");
            JLabel etiqueta = new JLabel("Etiqueta " + indice);
            etiqueta.setText(null);
            etiqueta.setSize(150, 150);
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(etiqueta.getWidth(), etiqueta.getHeight(), Image.SCALE_DEFAULT));
            etiqueta.setIcon(icono);
            this.jPanel1.add(etiqueta);
            imagenes.add(etiqueta);
//            JLabel text = new JLabel();
//            etiqueta.setText("ocupado");
//            this.jPanel1.add(text);
//            JCheckBox checkBox = new JCheckBox("Check me!");
//            checkBox.setBounds(etiqueta.getX()-100, etiqueta.getY()-100,100,100);
//            this.jPanel1.add(checkBox);

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
//        Conexion c = new Conexion();
////        c.delete();
//        try {
//            String ruta = "./data/estados.txt";
//            String Text = "";
//            Binary bn = new Binary();
//            for (int i = 0; i < estados.size(); i++) {
//                try {
//                    JComboBox est = estados.get(i);
//                    Text += est.getSelectedItem() + ",";
//
//                } catch (Exception ex) {
//                    Logger.getLogger(JFrameParkings.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            cm.WriteEstados(Text, ruta);
//            Conexion cn = new Conexion();
//            cn.datos();
//            ReadJson rj = new ReadJson();
//            Images img = new Images();
//            Images.getImage(rj.jsonFileRead());
//
//            JFrameMain s = new JFrameMain();
//            this.setVisible(false);
//            s.setVisible(true);
//        } catch (JSONException ex) {
//            Logger.getLogger(JFrameParkings.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(JFrameParkings.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

//        String base64String = "data:image/png;base64,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";
//        String[] strings = base64String.split(",");
//        String extension;
//        switch (strings[0]) {//check image's extension
//            case "data:image/jpeg;base64":
//                extension = "jpeg";
//                break;
//            case "data:image/png;base64":
//                extension = "png";
//                break;
//            default://should write cases for more images types
//                extension = "jpg";
//                break;
//        }
//        //convert base64 string to binary data
//        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
//        String path = "./data/image." + extension;
//        File file = new File(path);
//        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
//            outputStream.write(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
