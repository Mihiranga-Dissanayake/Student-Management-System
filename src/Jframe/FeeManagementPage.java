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
public class FeeManagementPage extends javax.swing.JFrame {

    String status;
    int enrollment_id,course_id,std_id,amount;
    Date enrollment_date;
    DefaultTableModel model;

    /**
     * Creates new form StudentManagementPage
     */
    public FeeManagementPage() {
        initComponents();
        setFeeDetailsToTable();
    }

    public void setFeeDetailsToTable() {
        model = (DefaultTableModel) tbl_feeDetails.getModel();
        model.setRowCount(0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "root"); 
                    Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM enrollments")) {

                while (rs.next()) {
                    int enrollmentId = rs.getInt("enrollment_id");
                    int studentId = rs.getInt("std_id");
                    int courseId = rs.getInt("course_id");
                    Date enrollmentDate = rs.getDate("enrollment_date");
                    int amount = rs.getInt("amount");
                    String status = rs.getString("status");
                    
                    Object[] obj = {enrollmentId, studentId, courseId, enrollmentDate, amount, status,};
                    model.addRow(obj);
                }
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "MySQL Driver not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean addFee() {
        enrollment_id = Integer.parseInt(txt_enrollmentId.getText());
        std_id = Integer.parseInt(txt_studentId.getText());
        course_id = Integer.parseInt(txt_courseId.getText());
        enrollment_date = txt_enrollmentDate.getDatoFecha();
        amount = Integer.parseInt(txt_amount.getText());
        status = txt_status.getText();
        boolean isAdded = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "insert into enrollments (enrollment_id,std_id,course_id,enrollment_date,amount,status) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, enrollment_id);
            pst.setInt(2, std_id);
            pst.setInt(3, course_id);
            pst.setDate(4, new java.sql.Date(enrollment_date.getTime()));
            pst.setInt(5, amount);
            pst.setString(6, status);

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

    public boolean updateFee() {
        enrollment_id = Integer.parseInt(txt_enrollmentId.getText());
        std_id = Integer.parseInt(txt_studentId.getText());
        course_id = Integer.parseInt(txt_courseId.getText());
        enrollment_date = txt_enrollmentDate.getDatoFecha();
        amount = Integer.parseInt(txt_amount.getText());
        status = txt_status.getText();
        boolean isUpdated = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "update enrollments set std_id = ?, course_id = ?, enrollment_date = ?,amount  = ?, status = ? where enrollment_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, std_id);
            pst.setInt(2, course_id);
            pst.setDate(3, new java.sql.Date(enrollment_date.getTime()));
            pst.setInt(4,amount);
            pst.setString(5, status);
            pst.setInt(6, enrollment_id);

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

    public boolean deleteFee() {
        enrollment_id = Integer.parseInt(txt_enrollmentId.getText());
        boolean isDeleted = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "delete from enrollments where enrollment_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, enrollment_id);

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
        DefaultTableModel model = (DefaultTableModel) tbl_feeDetails.getModel();
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
        txt_enrollmentId = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_studentId = new javax.swing.JTextField();
        txt_courseId = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_status = new javax.swing.JTextField();
        txt_enrollmentDate = new rojeru_san.componentes.RSDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txt_amount = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_feeDetails = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Student2.png"))); // NOI18N
        jLabel13.setText("Fee Management");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 270, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Enrollment ID :");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 110, 30));
        jPanel6.add(txt_enrollmentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 290, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Student ID :");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 100, 30));
        jPanel6.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 290, 30));
        jPanel6.add(txt_courseId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 290, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Course ID :");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 90, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Enrollment Date :");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 120, 30));

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Status :");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 120, 30));
        jPanel6.add(txt_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 290, 30));

        txt_enrollmentDate.setColorBackground(new java.awt.Color(51, 51, 51));
        txt_enrollmentDate.setColorButtonHover(new java.awt.Color(51, 51, 51));
        txt_enrollmentDate.setColorForeground(new java.awt.Color(51, 51, 51));
        txt_enrollmentDate.setPlaceholder("Select DOB");
        jPanel6.add(txt_enrollmentDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, -1, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Amount :");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 120, 30));
        jPanel6.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 290, 30));

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

        tbl_feeDetails.setBackground(new java.awt.Color(51, 51, 51));
        tbl_feeDetails.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_feeDetails.setForeground(new java.awt.Color(255, 255, 255));
        tbl_feeDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Enrollment ID", "Student ID", "Course ID", "Enrollment Date", "Amount", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_feeDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_feeDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_feeDetails);
        if (tbl_feeDetails.getColumnModel().getColumnCount() > 0) {
            tbl_feeDetails.getColumnModel().getColumn(0).setResizable(false);
            tbl_feeDetails.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbl_feeDetails.getColumnModel().getColumn(1).setResizable(false);
            tbl_feeDetails.getColumnModel().getColumn(1).setPreferredWidth(10);
            tbl_feeDetails.getColumnModel().getColumn(2).setResizable(false);
            tbl_feeDetails.getColumnModel().getColumn(2).setPreferredWidth(10);
            tbl_feeDetails.getColumnModel().getColumn(3).setResizable(false);
            tbl_feeDetails.getColumnModel().getColumn(4).setResizable(false);
            tbl_feeDetails.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 720, 460));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (deleteFee() == true) {
            JOptionPane.showMessageDialog(this, "Fee Deleted Successfully");
            clearTable();
            setFeeDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Fee Deletion Failed");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (addFee() == true) {
            JOptionPane.showMessageDialog(this, "Fee Added Successfully");
            clearTable();
            setFeeDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Fee Addition Failed");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (updateFee() == true) {
            JOptionPane.showMessageDialog(this, "Fee Updated Successfully");
            clearTable();
            setFeeDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Fee Updatation Failed");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void tbl_feeDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_feeDetailsMouseClicked
        int rowNo = tbl_feeDetails.getSelectedRow();
        TableModel model = tbl_feeDetails.getModel();

        txt_enrollmentId.setText(model.getValueAt(rowNo, 0).toString());
        txt_studentId.setText(model.getValueAt(rowNo, 1).toString());
        txt_courseId.setText(model.getValueAt(rowNo, 2).toString());
        try {
            String dateString = model.getValueAt(rowNo, 3).toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);
            txt_enrollmentDate.setDatoFecha(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        txt_amount.setText(model.getValueAt(rowNo, 4).toString());
        txt_status.setText(model.getValueAt(rowNo, 5).toString());
    }//GEN-LAST:event_tbl_feeDetailsMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        txt_enrollmentId.setText("");
        txt_studentId.setText("");
        txt_courseId.setText("");
        txt_enrollmentDate.setDatoFecha(null);
        txt_status.setText("");
        txt_amount.setText("");
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
            java.util.logging.Logger.getLogger(FeeManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FeeManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FeeManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FeeManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FeeManagementPage().setVisible(true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_feeDetails;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_courseId;
    private rojeru_san.componentes.RSDateChooser txt_enrollmentDate;
    private javax.swing.JTextField txt_enrollmentId;
    private javax.swing.JTextField txt_status;
    private javax.swing.JTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
