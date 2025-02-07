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
public class AttendenceManagementPage extends javax.swing.JFrame {

    int attendance_id,module_id,std_id,attendance_per;
    DefaultTableModel model;

    /**
     * Creates new form StudentManagementPage
     */
    public AttendenceManagementPage() {
        initComponents();
        setModuleDetailsToTable();
        setStudentDetailsToTable();
        setAttendanceDetailsToTable();
    }
    
    
    public void setModuleDetailsToTable() {
        model = (DefaultTableModel) tbl_MSDetails.getModel();
        model.setRowCount(0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "root"); 
                    Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM modules")) {

                while (rs.next()) {
                    int moduleId = rs.getInt("module_id");
                    String moduleName = rs.getString("module_name");
                    
                    Object[] obj = {moduleId, moduleName};
                    model.addRow(obj);
                }
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "MySQL Driver not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setStudentDetailsToTable() {
        model = (DefaultTableModel) tbl_studentDetails.getModel();
        model.setRowCount(0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "root"); 
                    Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM student_details")) {

                while (rs.next()) {
                    int studentId = rs.getInt("std_id");
                    String studentName = rs.getString("std_name");
                    
                    Object[] obj = {studentId, studentName};
                    model.addRow(obj);
                }
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "MySQL Driver not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setAttendanceDetailsToTable() {
        model = (DefaultTableModel) tbl_attendanceDetails.getModel();
        model.setRowCount(0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "root"); 
                    Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM attendance")) {

                while (rs.next()) {
                    int attendanceId = rs.getInt("attendance_id");
                    int studentId = rs.getInt("std_id");
                    int moduleId = rs.getInt("module_id");
                    int attndancePer = rs.getInt("attendance_per");
                    
                    Object[] obj = {attendanceId, studentId, moduleId, attndancePer};
                    model.addRow(obj);
                }
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "MySQL Driver not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean addAttendance() {
        attendance_id = Integer.parseInt(txt_attendanceId.getText());
        std_id = Integer.parseInt(txt_studentId.getText());
        module_id = Integer.parseInt(txt_moduleId.getText());
        attendance_per = Integer.parseInt(txt_attendance.getText());
        boolean isAdded = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "insert into attendance (attendance_id,std_id,module_id,attendance_per) values (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, attendance_id);
            pst.setInt(2, std_id);
            pst.setInt(3, module_id);
            pst.setInt(4, attendance_per);

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

    public boolean updateAttendance() {
        attendance_id = Integer.parseInt(txt_attendanceId.getText());
        std_id = Integer.parseInt(txt_studentId.getText());
        module_id = Integer.parseInt(txt_moduleId.getText());
        attendance_per = Integer.parseInt(txt_attendance.getText());
        boolean isUpdated = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "update attendance set std_id = ?, module_id = ?, attendance_per = ? where attendance_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, std_id);
            pst.setInt(2, module_id);
            pst.setInt(3,attendance_per);
            pst.setInt(4, attendance_id);

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

    public boolean deleteAttendance() {
        attendance_id = Integer.parseInt(txt_attendanceId.getText());
        boolean isDeleted = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "delete from attendance where attendance_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, attendance_id);

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
        DefaultTableModel model = (DefaultTableModel) tbl_attendanceDetails.getModel();
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
        txt_attendanceId = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_studentId = new javax.swing.JTextField();
        txt_moduleId = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_attendance = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_attendanceDetails = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_MSDetails = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_studentDetails = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Student2.png"))); // NOI18N
        jLabel13.setText("Attendence Management");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 350, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Attendence ID :");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 110, 30));
        jPanel6.add(txt_attendanceId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 290, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Student ID :");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 100, 30));
        jPanel6.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 290, 30));
        jPanel6.add(txt_moduleId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 290, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Module ID :");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 90, 30));

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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Attendence(%) :");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 120, 30));
        jPanel6.add(txt_attendance, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 290, 30));

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

        tbl_attendanceDetails.setBackground(new java.awt.Color(51, 51, 51));
        tbl_attendanceDetails.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_attendanceDetails.setForeground(new java.awt.Color(255, 255, 255));
        tbl_attendanceDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Attendence ID", "Student ID", "Module ID", "Attendence(%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_attendanceDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_attendanceDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_attendanceDetails);
        if (tbl_attendanceDetails.getColumnModel().getColumnCount() > 0) {
            tbl_attendanceDetails.getColumnModel().getColumn(0).setResizable(false);
            tbl_attendanceDetails.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbl_attendanceDetails.getColumnModel().getColumn(1).setResizable(false);
            tbl_attendanceDetails.getColumnModel().getColumn(1).setPreferredWidth(10);
            tbl_attendanceDetails.getColumnModel().getColumn(2).setResizable(false);
            tbl_attendanceDetails.getColumnModel().getColumn(2).setPreferredWidth(10);
            tbl_attendanceDetails.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 720, 430));

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));

        tbl_MSDetails.setBackground(new java.awt.Color(51, 51, 51));
        tbl_MSDetails.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_MSDetails.setForeground(new java.awt.Color(255, 255, 255));
        tbl_MSDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module ID", "Module Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_MSDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_MSDetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_MSDetails);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 460, 350, 230));

        jScrollPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));

        tbl_studentDetails.setBackground(new java.awt.Color(51, 51, 51));
        tbl_studentDetails.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_studentDetails.setForeground(new java.awt.Color(255, 255, 255));
        tbl_studentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_studentDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_studentDetailsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_studentDetails);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, 350, 230));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (deleteAttendance() == true) {
            JOptionPane.showMessageDialog(this, "Attendance Deleted Successfully");
            clearTable();
            setAttendanceDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Attendance Deletion Failed");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (addAttendance() == true) {
            JOptionPane.showMessageDialog(this, "Attendance Added Successfully");
            clearTable();
            setAttendanceDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Attendance Addition Failed");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (updateAttendance() == true) {
            JOptionPane.showMessageDialog(this, "Attendance Updated Successfully");
            clearTable();
            setAttendanceDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Attendance Updatation Failed");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void tbl_attendanceDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_attendanceDetailsMouseClicked
        int rowNo = tbl_attendanceDetails.getSelectedRow();
        TableModel model = tbl_attendanceDetails.getModel();

        txt_attendanceId.setText(model.getValueAt(rowNo, 0).toString());
        txt_studentId.setText(model.getValueAt(rowNo, 1).toString());
        txt_moduleId.setText(model.getValueAt(rowNo, 2).toString());
        txt_attendance.setText(model.getValueAt(rowNo, 3).toString());
    }//GEN-LAST:event_tbl_attendanceDetailsMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        txt_attendanceId.setText("");
        txt_studentId.setText("");
        txt_moduleId.setText("");
        txt_attendance.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tbl_MSDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_MSDetailsMouseClicked

    }//GEN-LAST:event_tbl_MSDetailsMouseClicked

    private void tbl_studentDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_studentDetailsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_studentDetailsMouseClicked

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
            java.util.logging.Logger.getLogger(AttendenceManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AttendenceManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AttendenceManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AttendenceManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AttendenceManagementPage().setVisible(true);
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
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbl_MSDetails;
    private javax.swing.JTable tbl_attendanceDetails;
    private javax.swing.JTable tbl_studentDetails;
    private javax.swing.JTextField txt_attendance;
    private javax.swing.JTextField txt_attendanceId;
    private javax.swing.JTextField txt_moduleId;
    private javax.swing.JTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
