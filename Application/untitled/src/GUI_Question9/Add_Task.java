package DSA.Assignment.Application.untitled.src.GUI_Question9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Add_Task extends JFrame {
    JFrame jFrame;
    JLabel jLabel1, jLabel2;
    Add_Task(){

        jLabel1 = new JLabel();
        jLabel1.setText("Task id :- ");
        jLabel1.setBounds(40,40,100,40);
        jLabel1.setFont(new Font("Arial", Font.PLAIN, 16));

        JTextField jTextField1 = new JTextField();
        jTextField1.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        jTextField1.setBounds(140,50,100,25);
        jTextField1.setColumns(10);


        jLabel2 = new JLabel();
        jLabel2.setText("Task :- ");
        jLabel2.setBounds(40,90,100,40);
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 16));

        JTextField jTextField2 = new JTextField();
        jTextField2.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        jTextField2.setBounds(140, 100,150,25);
        jTextField2.setColumns(10);


        JButton jButton = new JButton();
        jButton.setFocusable(false);
        jButton.setText("Ok");
        jButton.setBounds(160,150,80,25);
        jButton.setFont(new Font("Arial", Font.BOLD, 16));
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String TaskID = jTextField1.getText();
                String Task = jTextField2.getText();
                boolean validationSuccessful = false;

                if(TaskID.trim().isEmpty() && Task.trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter both TaskID and Task", "Task Id and Task not filled", JOptionPane.ERROR_MESSAGE);
                }
                else if(Task.trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter Task", "Task not filled", JOptionPane.ERROR_MESSAGE);
                }
                else if(TaskID.trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter TaskID", "Task Id not filled", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    validationSuccessful = true;
                }

                // Start Connection in Sql Database
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dsa-assignment", "root", "roshan09876");

                    System.out.println("Connection Successful");

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    System.out.println("Failed to connect");

                }
                // End Connection in SQl Database

                // Insert data into the database only if the validation is successful
                if (validationSuccessful) {
                    String insertSQL = "INSERT INTO addtask (TaskID, Task) VALUES (?, ?)";

                    try {
                        assert connection != null;
                        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                            preparedStatement.setString(1, TaskID);
                            preparedStatement.setString(2, Task);

                            int rowCount = preparedStatement.executeUpdate();
                            System.out.println(rowCount + " Row Inserted Successfully..");
                            JOptionPane.showMessageDialog(null, "Successfully Data Inserted...");
                        }
                    } catch (SQLException sqlException) {
                        System.out.println("Insertion failed: " + sqlException.getMessage());
                        JOptionPane.showMessageDialog(null, "Data already exist (Insertion Failed)...", "Error Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });





        jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setTitle("Add task JFrame");
        jFrame.setSize(450, 320);
        jFrame.add(jLabel1);
        jFrame.add(jLabel2);
        jFrame.add(jTextField1);
        jFrame.add(jTextField2);
        jFrame.add(jButton);
        jFrame.setVisible(true);

    }

    public static void main(String[] args) {
        new Add_Task();
    }
}
