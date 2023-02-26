package DSA.Assignment.Application.untitled.src.GUI_Question9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Add_task_to_Job extends JFrame {

    Add_task_to_Job(){

        JLabel jLabel1 = new JLabel();
        jLabel1.setText("Job id :- ");
        jLabel1.setFont(new Font("Arial", Font.PLAIN,20));
        jLabel1.setBounds(80,30,100,40);

        JTextField jTextField1 = new JTextField();
        jTextField1.setBounds(180,37,80,28);
        jTextField1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        jTextField1.setColumns(10);


        JLabel jLabel2 = new JLabel();
        jLabel2.setText("Task :- ");
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 20));
        jLabel2.setBounds(85,70,100,40);

        JTextField jTextField2 = new JTextField();
        jTextField2.setBounds(180,77,120,28);
        jTextField2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        jTextField2.setColumns(10);



        JLabel jLabel3 = new JLabel();
        jLabel3.setText("Depends on :- ");
        jLabel3.setBounds(40,110,150,40);
        jLabel3.setFont(new Font("Arial", Font.PLAIN, 20));

        JTextField jTextField3 = new JTextField();
        jTextField3.setBounds(180,117,130,28);
        jTextField3.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        jTextField3.setColumns(10);


        JButton jButton = new JButton();
        jButton.setText("Ok");
        jButton.setBounds(140,165,80,30);
        jButton.setFocusable(false);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Start Database Connection
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dsa-assignment","root","roshan09876");
                    System.out.println("Connection Successful");

                    String jobId = jTextField1.getText();
                    String task = jTextField2.getText();
                    String dependsOn = jTextField3.getText();

                    // Check if the data exists in both tables
                    String checkJobSql = "SELECT * FROM create_job WHERE job = ?";
                    String checkTaskSql = "SELECT * FROM addtask WHERE Task = ?";
                    String checkJobNameSql = "SELECT * FROM create_job WHERE jobNAME = ?";

                    PreparedStatement jobCheckStmt = connection.prepareStatement(checkJobSql);
                    jobCheckStmt.setString(1, jobId);

                    PreparedStatement taskCheckStmt = connection.prepareStatement(checkTaskSql);
                    taskCheckStmt.setString(1, task);

                    PreparedStatement jobNameStmt = connection.prepareStatement(checkJobNameSql);
                    jobNameStmt.setString(1,dependsOn);

                    ResultSet jobResult = jobCheckStmt.executeQuery();
                    ResultSet taskResult = taskCheckStmt.executeQuery();
                    ResultSet jobName = jobNameStmt.executeQuery();

                    if (jobResult.next() && taskResult.next() && jobName.next()) {
                        JOptionPane.showMessageDialog(null, "Access granted!");

                        String insertSql = "INSERT INTO add_task_to_job (jobID, Task, Depends_On) VALUES (?, ?, ?)";
                        PreparedStatement insertStmt = connection.prepareStatement(insertSql);
                        insertStmt.setString(1, jobId);
                        insertStmt.setString(2, task);
                        insertStmt.setString(3, dependsOn);
                        insertStmt.executeUpdate();
                        System.out.println(insertStmt + " Row Inserted ");
                        JOptionPane.showMessageDialog(null, "Task added successfully");


                    } else {
                        JOptionPane.showMessageDialog(null, "Access denied!(Data did not Match)","Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException sqlException) {
                    System.out.println("Error Failed Connection " + sqlException.getMessage());
                }
                // End Database Connection
            }
        });


//        End Database Connection



        this.setTitle("Add Task To Job");
        this.setLayout(null);
        this.setSize(450,320);
        this.add(jLabel1);
        this.add(jLabel2);
        this.add(jLabel3);
        this.add(jTextField1);
        this.add(jTextField2);
        this.add(jTextField3);
        this.add(jButton);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Add_task_to_Job();
    }
}