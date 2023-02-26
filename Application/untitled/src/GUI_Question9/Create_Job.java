package DSA.Assignment.Application.untitled.src.GUI_Question9;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

public class Create_Job extends JFrame {

    Create_Job() {

        setTitle("Create Job");
        setSize(450, 320);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel jobLabel = new JLabel("Job Id :- ");
        jobLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(jobLabel, constraints);

        JTextField jobField = new JTextField(10);
        jobField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        constraints.gridx = 1;
        constraints.gridy = 0;
        mainPanel.add(jobField, constraints);

        JLabel jobNameLabel = new JLabel("Job name :- ");
        jobNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(jobNameLabel, constraints);

        JTextField jobNameField = new JTextField(10);
        jobNameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPanel.add(jobNameField, constraints);

        JButton okButton = new JButton("OK");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(okButton, constraints);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String job = jobField.getText();
                String jobNAME = jobNameField.getText();
                boolean validationSuccessful = false;

                if (job.trim().isEmpty() && jobNAME.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both Job and Job Name", "Job and Job Name not filled", JOptionPane.ERROR_MESSAGE);
                } else if (job.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter Job", "Job Not filled", JOptionPane.ERROR_MESSAGE);
                } else if (jobNAME.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter Job Name", "Job name not filled", JOptionPane.ERROR_MESSAGE);
                } else {
                    validationSuccessful = true;
                }

                Connection connection = null;

                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dsa-assignment","root","roshan09876");
                    System.out.println("Connection Successful");

                }catch (SQLException sqlException){
                    sqlException.printStackTrace();
                    System.out.println("Error Connection Failed");

                }
//                End Connection in database


//                fetching data from textField of Create_Job

                if(validationSuccessful){

                    String insertSQL = "INSERT INTO create_job (job, jobNAME) VALUES (?, ?)";

                    try{
                        assert  connection != null;

                        try(PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)){
                            preparedStatement.setString(1, job);
                            preparedStatement.setString(2,jobNAME);

                            int rowCount = preparedStatement.executeUpdate();
                            System.out.println(rowCount + "Row Inserted Successfully....");
                            JOptionPane.showMessageDialog(null, "Successfully Data Inserted...");
                        }

                    }catch (SQLException sqlException){
                        System.out.println("Insertion Failed " + sqlException.getMessage());
                        JOptionPane.showMessageDialog(null, "Data Already exist(Insertion Failed).." , "Error Failed", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });


        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Create_Job();
    }
}
