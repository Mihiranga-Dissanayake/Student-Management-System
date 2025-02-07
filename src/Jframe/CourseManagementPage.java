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
public class CourseManagementPage extends javax.swing.JFrame {

    String course_name, course_director, course_duration, course_discription;
    int course_id;
    DefaultTableModel model;

    /**
     * Creates new form StudentManagementPage
     */
    public CourseManagementPage() {
        initComponents();
        setCourseDetailsToTable();
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
                    String courseDiscription = rs.getString("course_discription");
                    String courseDuration = rs.getString("course_duration");
                    String CourseDirector = rs.getString("course_director");
                    
                    Object[] obj = {courseId, courseName, courseDiscription, courseDuration, CourseDirector};
                    model.addRow(obj);
                }
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "MySQL Driver not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean addCourse() {
        course_id = Integer.parseInt(txt_courseId.getText());
        course_name = txt_courseName.getText();
        course_discription = txt_courseDiscription.getText();
        course_duration = txt_courseDuration.getText();
        course_director = txt_courseDirector.getText();
        boolean isAdded = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "insert into course_details values(?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, course_id);
            pst.setString(2, course_name);
            pst.setString(3, course_discription);
            pst.setString(4, course_duration);
            pst.setString(5, course_director);

            if (course_name.equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Valid Course Name");
            }

            if (course_duration.equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Valid duration");
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

    public boolean updateCourse() {
        course_id = Integer.parseInt(txt_courseId.getText());
        course_name = txt_courseName.getText();
        course_discription = txt_courseDiscription.getText();
        course_duration = txt_courseDuration.getText();
        course_director = txt_courseDirector.getText();
        boolean isUpdated = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "update course_details set course_name = ?, course_discription = ?, course_duration = ?, course_director = ? where course_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, course_name);
            pst.setString(2, course_discription);
            pst.setString(3, course_duration);
            pst.setString(4, course_director);
            pst.setInt(5, course_id);

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

    public boolean deleteCourse() {
        course_id = Integer.parseInt(txt_courseId.getText());
        boolean isDeleted = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "delete from course_details where course_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, course_id);

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
        DefaultTableModel model = (DefaultTableModel) tbl_courseDetails.getModel();
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
        txt_courseId = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_courseName = new javax.swing.JTextField();
        txt_courseDiscription = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_courseDuration = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_courseDirector = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
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
        jLabel14.setText("Course ID :");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 90, 30));
        jPanel6.add(txt_courseId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 290, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Course Name :");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 100, 30));
        jPanel6.add(txt_courseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 290, 30));
        jPanel6.add(txt_courseDiscription, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 290, 60));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Discription :");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 90, 30));
        jPanel6.add(txt_courseDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 290, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Duration :");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 90, 30));

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
        jLabel3.setText("Course Director :");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 120, 30));
        jPanel6.add(txt_courseDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 290, 30));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 700));

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));

        tbl_courseDetails.setBackground(new java.awt.Color(51, 51, 51));
        tbl_courseDetails.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_courseDetails.setForeground(new java.awt.Color(255, 255, 255));
        tbl_courseDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Discription", "Duration", "Course Director"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        jScrollPane1.setViewportView(tbl_courseDetails);
        if (tbl_courseDetails.getColumnModel().getColumnCount() > 0) {
            tbl_courseDetails.getColumnModel().getColumn(0).setResizable(false);
            tbl_courseDetails.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 720, 460));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (deleteCourse() == true) {
            JOptionPane.showMessageDialog(this, "Course Deleted Successfully");
            clearTable();
            setCourseDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Course Deletion Failed");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (addCourse() == true) {
            JOptionPane.showMessageDialog(this, "Course Added Successfully");
            clearTable();
            setCourseDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Course Addition Failed");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (updateCourse() == true) {
            JOptionPane.showMessageDialog(this, "Course Updated Successfully");
            clearTable();
            setCourseDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Course Updatation Failed");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void tbl_courseDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_courseDetailsMouseClicked
        int rowNo = tbl_courseDetails.getSelectedRow();
        TableModel model = tbl_courseDetails.getModel();

        txt_courseId.setText(model.getValueAt(rowNo, 0).toString());
        txt_courseName.setText(model.getValueAt(rowNo, 1).toString());
        txt_courseDiscription.setText(model.getValueAt(rowNo, 2).toString());
        txt_courseDuration.setText(model.getValueAt(rowNo, 3).toString());
        txt_courseDirector.setText(model.getValueAt(rowNo, 4).toString());
    }//GEN-LAST:event_tbl_courseDetailsMouseClicked

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
            java.util.logging.Logger.getLogger(CourseManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CourseManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CourseManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CourseManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CourseManagementPage().setVisible(true);
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
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_courseDetails;
    private javax.swing.JTextField txt_courseDirector;
    private javax.swing.JTextField txt_courseDiscription;
    private javax.swing.JTextField txt_courseDuration;
    private javax.swing.JTextField txt_courseId;
    private javax.swing.JTextField txt_courseName;
    // End of variables declaration//GEN-END:variables
}
