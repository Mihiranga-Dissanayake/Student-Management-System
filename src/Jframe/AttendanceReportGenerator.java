/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jframe;

/**
 *
 * @author Mihiranga
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;


public class AttendanceReportGenerator {
    public void generateattendanceReport() {
        try {
            // Step 1: Establish Database Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "root");

            // Step 2: Load the Jasper file from resources
            String reportPath = "src/reports/AttendanceReport.jasper";

            // Step 3: Create parameters (if any)
            HashMap<String, Object> parameters = new HashMap<>();

            // Step 4: Fill the report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, con);

            // Step 5: Show the report
            JasperViewer.viewReport(jasperPrint, false);

            // Close connection
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
