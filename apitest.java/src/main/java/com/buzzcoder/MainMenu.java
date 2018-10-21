package com.buzzcoder;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainMenu {
    JFrame f;
    JLabel welcomeBar;
    JButton chatBot, pointsButton, entertainmentButton, flyerButton, moviesButton, bac;
    JButton b;
    //JPanel panel1, panel2, panelMain;

    public JFrame returnF() {
        return f;
    }

    public MainMenu() {
        f = new JFrame("Main Menu");
        welcomeBar = new JLabel("Welcome to your personal in-flight Menu, BuzzCoder!");
        chatBot = new JButton("Chatty Boi");
        pointsButton = new JButton("View Points");
        //entertainmentButton = new JButton("Blogs, Articles, and More");
        flyerButton = new JButton("Frequent Flyer");
        moviesButton = new JButton("Movies");
        bac = new JButton("hhhh");

        welcomeBar.setBounds(0,0,600,75);
        chatBot.setBounds(200,100,200,150);
        flyerButton.setBounds(50,100,125,40);
        moviesButton.setBounds(425, 100,100,100);
        //moviesButton.setBounds(50, 325,125,40);

        //entertainmentButton.setBounds(200, 275, 200,100);
        pointsButton.setBounds(50, 150, 125, 40);
        bac.setBounds(75,500,100,100);
        //pointsButton.setBounds(50,150,125,40);

        welcomeBar.setFont(new Font("Helvetica", Font.BOLD, 18));
        welcomeBar.setHorizontalAlignment(SwingConstants.CENTER);

        f.add(chatBot);
        f.add(welcomeBar);
        f.add(pointsButton);
        //f.add(entertainmentButton);
        f.add(flyerButton);
        f.add(moviesButton);
        f.add(welcomeBar);
        //f.add(b);
        f.add(bac);

        f.setVisible(true);
        f.setSize(600,450);
        f.setLayout(null);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pointsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrequentView();
            }
        });

        moviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                (new Movies()).connectDatabase();
                //f.setVisible(true);
            }
        });

        flyerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new FrequentFlyer();
            }
        });

        chatBot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new Chat();
                //f.setVisible(true);
            }
        });

    }
    public static void main(String[] args) {
        new MainMenu();
    }

}
