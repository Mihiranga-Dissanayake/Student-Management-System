/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import Jframe.DBConnection;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.TableModel;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Kavindu
 */
public class ModuleManagementPage extends javax.swing.JFrame {

    String module_name;
    int module_id,course_id;
    DefaultTableModel model;

    /**
     * Creates new form StudentManagementPage
     */
    public ModuleManagementPage() {
        initComponents();
        setModuleDetailsToTable();
        setCourseDetailsToTable(); 
    }

    public void setModuleDetailsToTable() {
        model = (DefaultTableModel) tbl_moduleDetails.getModel();
        model.setRowCount(0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "root"); 
                    Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM modules")) {

                while (rs.next()) {
                    int moduleId = rs.getInt("module_id");
                    String moduleName = rs.getString("module_name");
                    int courseId = rs.getInt("course_id");
                    
                    Object[] obj = {moduleId, moduleName, courseId};
                    model.addRow(obj);
                }
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "MySQL Driver not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setCourseDetailsToTable() {
        model = (DefaultTableModel) tbl_courseDetails.getModel();
        model.setRowCount(0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "root"); 
                    Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM course_details")) {

                while (rs.next()) {
                    int courseId = rs.getInt("course_id");
                    String courseName = rs.getString("course_name");
                    
                    Object[] obj = {courseId, courseName};
                    model.addRow(obj);
                }
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "MySQL Driver not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean addModule() {
        module_id = Integer.parseInt(txt_moduleId.getText());
        module_name = txt_moduleName.getText();
        course_id = Integer.parseInt(txt_courseId.getText());
        boolean isAdded = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "insert into modules values(?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, module_id);
            pst.setString(2, module_name);
            pst.setInt(3, course_id);

            
            if (module_id <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Module ID");
                return false;
            }
            
            if (module_name.equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Valid Course Name");
            }

            if (course_id <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Course ID");
                return false;
            }

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isAdded = true;
            } else {
                isAdded = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
        return isAdded;
    }

    public boolean updateModule() {
        module_id = Integer.parseInt(txt_moduleId.getText());
        module_name = txt_moduleName.getText();
        course_id = Integer.parseInt(txt_courseId.getText());
        boolean isUpdated = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "update modules set module_name = ?, course_id = ? where module_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, module_name);
            pst.setInt(2, course_id);
            pst.setInt(3, module_id);

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isUpdated = true;
            } else {
                isUpdated = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
        return isUpdated;
    }

    public boolean deleteModule() {
        module_id = Integer.parseInt(txt_moduleId.getText());
        boolean isDeleted = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "delete from modules where module_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, module_id);

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isDeleted = true;
            } else {
                isDeleted = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
        return isDeleted;
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_moduleDetails.getModel();
        model.setRowCount(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_moduleId = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_moduleName = new javax.swing.JTextField();
        txt_courseId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_moduleDetails = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_courseDetails = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Student2.png"))); // NOI18N
        jLabel13.setText("Course Management");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Module ID :");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 90, 30));
        jPanel6.add(txt_moduleId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 290, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Module Name :");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 110, 30));
        jPanel6.add(txt_moduleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 290, 30));
        jPanel6.add(txt_courseId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 290, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Course id :");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 90, 30));

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("DELETE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 530, 100, 40));

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("ADD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 100, 40));

        jButton3.setBackground(new java.awt.Color(0, 102, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 530, 110, 40));

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Back.png"))); // NOI18N
        jLabel1.setText("Back");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        jPanel6.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 40));

        jButton4.setBackground(new java.awt.Color(255, 102, 102));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 610, 120, 40));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 700));

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));

        tbl_moduleDetails.setBackground(new java.awt.Color(51, 51, 51));
        tbl_moduleDetails.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_moduleDetails.setForeground(new java.awt.Color(255, 255, 255));
        tbl_moduleDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module ID", "Module Name", "Course ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_moduleDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_moduleDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_moduleDetails);
        if (tbl_moduleDetails.getColumnModel().getColumnCount() > 0) {
            tbl_moduleDetails.getColumnModel().getColumn(0).setResizable(false);
            tbl_moduleDetails.getColumnModel().getColumn(0).setPreferredWidth(20);
            tbl_moduleDetails.getColumnModel().getColumn(1).setResizable(false);
            tbl_moduleDetails.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 720, 390));

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));

        tbl_courseDetails.setBackground(new java.awt.Color(51, 51, 51));
        tbl_courseDetails.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_courseDetails.setForeground(new java.awt.Color(255, 255, 255));
        tbl_courseDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course ID", "Course Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_courseDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_courseDetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_courseDetails);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 420, 290, 260));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (deleteModule() == true) {
            JOptionPane.showMessageDialog(this, "Module Deleted Successfully");
            clearTable();
            setModuleDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Module Deletion Failed");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (addModule() == true) {
            JOptionPane.showMessageDialog(this, "Module Added Successfully");
            clearTable();
            setModuleDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Module Addition Failed");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (updateModule() == true) {
            JOptionPane.showMessageDialog(this, "Module Updated Successfully");
            clearTable();
            setModuleDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Module Updatation Failed");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void tbl_moduleDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_moduleDetailsMouseClicked
        int rowNo = tbl_moduleDetails.getSelectedRow();
        TableModel model = tbl_moduleDetails.getModel();

        txt_moduleId.setText(model.getValueAt(rowNo, 0).toString());
        txt_moduleName.setText(model.getValueAt(rowNo, 1).toString());
        txt_courseId.setText(model.getValueAt(rowNo, 2).toString());
    }//GEN-LAST:event_tbl_moduleDetailsMouseClicked

    private void tbl_courseDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_courseDetailsMouseClicked
    
    }//GEN-LAST:event_tbl_courseDetailsMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        txt_moduleId.setText("");
        txt_moduleName.setText("");
        txt_courseId.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(ModuleManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuleManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuleManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuleManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuleManagementPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_courseDetails;
    private javax.swing.JTable tbl_moduleDetails;
    private javax.swing.JTextField txt_courseId;
    private javax.swing.JTextField txt_moduleId;
    private javax.swing.JTextField txt_moduleName;
    // End of variables declaration//GEN-END:variables
}
