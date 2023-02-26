package DSA.Assignment.Application.untitled.src.GUI_Question9;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Project extends JFrame{

    JFrame jFrame;
    JButton jButton;
    JButton jButton1;
    JButton jButton2;
    JButton jButton3;
    Project(){

        jButton = new JButton();
        jButton.setBounds(30,70,110,40);
        jButton.setText("Add Task");
        jButton.setFocusable(false);
//        jButton.addActionListener(e -> System.out.println("wow"));
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Add_Task();
//                dispose();
            }
        });

         jButton1 = new JButton();
        jButton1.setText("Add_Task_to_Job");
        jButton1.setBounds(30,150, 160,40);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Add_task_to_Job();
            }
        });

        jButton2 = new JButton();
        jButton2.setText("Create Job");
        jButton2.setBounds(320,70,130,40);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Create_Job();
            }
        });

        jButton3 = new JButton();
        jButton3.setText("Scheduling Job");
        jButton3.setBounds(320,150,130,40);
        jButton3.setFocusable(false);
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Scheduling_Job();
            }
        });


        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setSize(550,380);
        jFrame.setVisible(true);
        jFrame.add(jButton);
        jFrame.add(jButton1);
        jFrame.add(jButton2);
        jFrame.add(jButton3);
        jFrame.setTitle("DSA ASSIGNMENT");
    }


    public static void main(String[] args) {
        new Project();

    }

}
