/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Repairs extends JFrame implements ActionListener {
    JMenuItem processReport, clear, save, exit;

    // Labels
    JLabel technicianLocationLbl = new JLabel("TECHNICIAN LOCATION: ");
    JLabel technicianNameLbl = new JLabel("TECHNICIAN NAME: ");
    JLabel repairCostLbl = new JLabel("REPAIR COST: ");
    JLabel technicianRateLbl = new JLabel("TECHNICIAN RATE: ");
    JLabel technicianReportLbl = new JLabel("TECHNICIAN REPORT: ");

    // Combo box
    String[] city = {"Cape Town", "Durban", "Pretoria"};
    JComboBox<String> cb = new JComboBox<>(city);

    // Text fields
    JTextField technicianNameTf = new JTextField();
    JTextField technicianRateTf = new JTextField();
    JTextField repairCostTf = new JTextField();

    // Text area
    JTextArea technicianReportTa = new JTextArea();

    Repairs() {
        super("TECHNICIAN");
        setLayout(null);

        // Set bounds for labels
        technicianLocationLbl.setBounds(20, 20, 150, 25);
        technicianNameLbl.setBounds(20, 60, 150, 25);
        repairCostLbl.setBounds(20, 100, 150, 25);
        technicianRateLbl.setBounds(20, 140, 150, 25);
        technicianReportLbl.setBounds(20, 180, 150, 25);

        // Set bounds for combo box and text fields
        cb.setBounds(180, 20, 150, 25);
        technicianNameTf.setBounds(180, 60, 150, 25);
        repairCostTf.setBounds(180, 100, 150, 25);
        technicianRateTf.setBounds(180, 140, 150, 25);

        // Set bounds for text area
        technicianReportTa.setBounds(20, 220, 350, 150);

        // Add components to frame
        add(technicianLocationLbl);
        add(cb);
        add(technicianNameLbl);
        add(technicianNameTf);
        add(repairCostLbl);
        add(repairCostTf);
        add(technicianRateLbl);
        add(technicianRateTf);
        add(technicianReportLbl);
        add(technicianReportTa);

        // Set up menu
        JMenu file = new JMenu("File");
        JMenu tools = new JMenu("Tools");
        processReport = new JMenuItem("Process Report");
        clear = new JMenuItem("Clear");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");

        processReport.addActionListener(this);
        clear.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);

        JMenuBar mb = new JMenuBar();
        file.add(exit);
        tools.add(processReport);
        tools.add(clear);
        tools.add(save);
        mb.add(file);
        mb.add(tools);
        setJMenuBar(mb);

        // Frame settings
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == clear) {
            technicianNameTf.setText("");
            repairCostTf.setText("");
            technicianRateTf.setText("");
            technicianReportTa.setText("");
        }
        if (e.getSource() == processReport) {
            try {
                String technicianName = technicianNameTf.getText();
                String location = (String) cb.getSelectedItem();
                double rate = Double.parseDouble(technicianRateTf.getText());
                double cost = Double.parseDouble(repairCostTf.getText());
                double calculatedPay = cost * (rate / 100);

                String report = "TECHNICIAN LOCATION: " + location + "\n****************************\n" +
                                "TECHNICIAN NAME: " + technicianName + "\n" +
                                "REPAIR COST: R " + cost + "\n" +
                                "TECHNICIAN RATE: " + rate + "%\n" +
                                "CALCULATED PAY: R " + calculatedPay + "\n****************************";
                technicianReportTa.setText(report);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for rate and cost.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == save) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("technician_report.txt"))) {
                writer.write(technicianReportTa.getText());
                JOptionPane.showMessageDialog(this, "Report saved successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving report.", "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new Repairs();
    }
}
