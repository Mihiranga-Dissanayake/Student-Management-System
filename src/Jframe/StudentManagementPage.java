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
public class StudentManagementPage extends javax.swing.JFrame {

    String std_name, std_email, std_address;
    int std_id, std_contact;
    Date std_bod;
    DefaultTableModel model;

    /**
     * Creates new form StudentManagementPage
     */
    public StudentManagementPage() {
        initComponents();
        setStudentDetailsToTable();
    }

    public void setStudentDetailsToTable() {
        // Initialize the DefaultTableModel from the table
        model = (DefaultTableModel) tbl_studentDetails.getModel();
        model.setRowCount(0); // Clear existing rows

        // Use try-with-resources for connection, statement, and result set
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "root"); Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM student_details")) {

                // Loop through the result set and add rows to the table
                while (rs.next()) {
                    int studentId = rs.getInt("std_id");
                    String studentName = rs.getString("std_name");
                    String studentEmail = rs.getString("std_email");
                    int studentContact = rs.getInt("std_contact");
                    String studentAddress = rs.getString("std_address");
                    Date studentDOB = rs.getDate("std_bod");

                    // Add the row to the table model
                    Object[] obj = {studentId, studentName, studentEmail, studentContact, studentAddress, studentDOB};
                    model.addRow(obj);
                }
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "MySQL Driver not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean addStudent() {
        std_id = Integer.parseInt(txt_studentId.getText());
        std_name = txt_name.getText();
        std_email = txt_email.getText();
        std_contact = Integer.parseInt(txt_contact.getText());
        std_address = txt_address.getText();
        std_bod = txt_bod.getDatoFecha();
        boolean isAdded = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "insert into student_details values(?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, std_id);
            pst.setString(2, std_name);
            pst.setString(3, std_email);
            pst.setInt(4, std_contact);
            pst.setString(5, std_address);
            pst.setDate(6, new java.sql.Date(std_bod.getTime()));

            if (std_name.equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Valid Name");
            }

            if (std_email.equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Valid email");
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

    public boolean updateStudent() {
        std_id = Integer.parseInt(txt_studentId.getText());
        std_name = txt_name.getText();
        std_email = txt_email.getText();
        std_contact = Integer.parseInt(txt_contact.getText());
        std_address = txt_address.getText();
        std_bod = txt_bod.getDatoFecha();
        boolean isUpdated = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "update student_details set std_name = ?, std_email = ?,std_contact = ?,std_address = ?,std_bod = ? where std_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, std_name);
            pst.setString(2, std_email);
            pst.setInt(3, std_contact);
            pst.setString(4, std_address);
            pst.setDate(5, new java.sql.Date(std_bod.getTime()));
            pst.setInt(6, std_id);

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

    public boolean deleteStudent() {
        std_id = Integer.parseInt(txt_studentId.getText());
        boolean isDeleted = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "delete from student_details where std_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, std_id);

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
        DefaultTableModel model = (DefaultTableModel) tbl_studentDetails.getModel();
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
        txt_studentId = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_contact = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_address = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_bod = new rojeru_san.componentes.RSDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
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
        jLabel13.setText("Student Management");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Student ID :");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 90, 30));
        jPanel6.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 310, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("DOB :");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 90, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Name :");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 90, 30));
        jPanel6.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 310, 30));
        jPanel6.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 310, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Email :");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 90, 30));
        jPanel6.add(txt_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 310, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contact :");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 90, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Address :");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 90, 30));
        jPanel6.add(txt_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 310, 30));

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("DELETE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 560, 100, 40));

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("ADD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, 100, 40));

        jButton3.setBackground(new java.awt.Color(0, 102, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 560, 110, 40));

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

        txt_bod.setColorBackground(new java.awt.Color(51, 51, 51));
        txt_bod.setColorButtonHover(new java.awt.Color(51, 51, 51));
        txt_bod.setColorForeground(new java.awt.Color(51, 51, 51));
        txt_bod.setPlaceholder("Select DOB");
        jPanel6.add(txt_bod, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, -1, 30));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 700));

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));

        tbl_studentDetails.setBackground(new java.awt.Color(51, 51, 51));
        tbl_studentDetails.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_studentDetails.setForeground(new java.awt.Color(255, 255, 255));
        tbl_studentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Contact", "Address", "DOB"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        jScrollPane1.setViewportView(tbl_studentDetails);
        if (tbl_studentDetails.getColumnModel().getColumnCount() > 0) {
            tbl_studentDetails.getColumnModel().getColumn(0).setResizable(false);
            tbl_studentDetails.getColumnModel().getColumn(0).setPreferredWidth(20);
            tbl_studentDetails.getColumnModel().getColumn(2).setResizable(false);
            tbl_studentDetails.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 720, 460));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (deleteStudent() == true) {
            JOptionPane.showMessageDialog(this, "Student Deleted Successfully");
            clearTable();
            setStudentDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Student Deletion Failed");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (addStudent() == true) {
            JOptionPane.showMessageDialog(this, "Student Added Successfully");
            clearTable();
            setStudentDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Student Addition Failed");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (updateStudent() == true) {
            JOptionPane.showMessageDialog(this, "Student Updated Successfully");
            clearTable();
            setStudentDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Student Updatation Failed");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void tbl_studentDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_studentDetailsMouseClicked
        int rowNo = tbl_studentDetails.getSelectedRow();
        TableModel model = tbl_studentDetails.getModel();

        txt_studentId.setText(model.getValueAt(rowNo, 0).toString());
        txt_name.setText(model.getValueAt(rowNo, 1).toString());
        txt_email.setText(model.getValueAt(rowNo, 2).toString());
        txt_contact.setText(model.getValueAt(rowNo, 3).toString());
        txt_address.setText(model.getValueAt(rowNo, 4).toString());
        try {
            String dateString = model.getValueAt(rowNo, 5).toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);
            txt_bod.setDatoFecha(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            java.util.logging.Logger.getLogger(StudentManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_studentDetails;
    private javax.swing.JTextField txt_address;
    private rojeru_san.componentes.RSDateChooser txt_bod;
    private javax.swing.JTextField txt_contact;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
